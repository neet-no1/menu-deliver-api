package jp.co.suyama.menu.deliver.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import jp.co.suyama.menu.deliver.common.S3Access;
import jp.co.suyama.menu.deliver.exception.MenuDeliverException;
import jp.co.suyama.menu.deliver.mapper.ArticleDetailsMapperImpl;
import jp.co.suyama.menu.deliver.mapper.ArticlesMapperImpl;
import jp.co.suyama.menu.deliver.mapper.UsersMapperImpl;
import jp.co.suyama.menu.deliver.model.ArticlesAndPage;
import jp.co.suyama.menu.deliver.model.auto.ArticleData;
import jp.co.suyama.menu.deliver.model.auto.PageNation;
import jp.co.suyama.menu.deliver.model.db.ArticleDetails;
import jp.co.suyama.menu.deliver.model.db.Articles;
import jp.co.suyama.menu.deliver.model.db.Users;
import jp.co.suyama.menu.deliver.utils.ConvertUtils;
import jp.co.suyama.menu.deliver.utils.PageNationUtils;
import jp.co.suyama.menu.deliver.utils.PathUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleService {

    // S3アクセス
    @Autowired
    private S3Access s3Access;

    // 記事テーブル
    @Autowired
    private ArticlesMapperImpl articlesMapper;

    // 記事詳細テーブル
    @Autowired
    private ArticleDetailsMapperImpl articleDetailsMapper;

    // ユーザテーブル
    @Autowired
    private UsersMapperImpl usersMapper;

    /**
     * 記事を投稿する
     *
     * @param email    メールアドレス
     * @param id       記事ID 新規追加時は0を指定
     * @param title    タイトル
     * @param contents 記事内容
     * @param thumb    サムネイル画像
     * @param opened   公開フラグ
     */
    public void postArticle(String email, int id, String title, String contents, MultipartFile thumb, boolean opened) {

        // ユーザ除法取得
        Users users = usersMapper.selectEmail(email);
        if (users == null) {
            // ユーザが存在しない場合はエラー
            throw new MenuDeliverException("ユーザが存在しません。");
        }

        // 記事ID
        int articlesId = id;

        // サムネイル画像パス
        String thumbPath = null;

        // 記事内容パス
        String contentsPath = null;

        // 削除画像パス
        List<String> deletePath = new ArrayList<>();

        // 記事テーブルを登録・更新する
        Articles articles = new Articles();
        articles.setUserId(users.getId());
        articles.setTitle(title);
        articles.setOpened(opened);
        articles.setStartSentence("");

        if (id == 0) {
            // IDが0の場合は登録
            articlesId = articlesMapper.registArticle(articles);

            // サムネイルがある場合
            if (thumb != null) {
                // サムネイル画像のパスを更新する
                String fileName = thumb.getOriginalFilename();
                thumbPath = PathUtils.createArticleImagePath(articlesId, fileName);
                articlesMapper.updateArticlesPath(articlesId, thumbPath);
            }

            // 記事詳細テーブルに登録
            contentsPath = PathUtils.createArticleDetailsPath(email, articlesId);
            ArticleDetails articleDetails = new ArticleDetails();
            articleDetails.setArticleId(articlesId);
            articleDetails.setPath(contentsPath);

            articleDetailsMapper.registArticleDetails(articleDetails);
        } else {
            // IDが0以外の場合は更新
            Articles existArticles = articlesMapper.selectByPrimaryKey(id);
            if (existArticles == null) {
                // DBに対象が存在しない場合はエラー
                throw new MenuDeliverException("更新対象が存在しません。");
            }

            // 更新対象が自分のものか確認
            if (existArticles.getUserId() != users.getId()) {
                throw new MenuDeliverException("更新対象が自身のものではありません。");
            }

            // サムネイルがある場合
            if (thumb != null) {
                // サムネイル画像のパスを更新する
                String fileName = thumb.getOriginalFilename();
                thumbPath = PathUtils.createArticleImagePath(articlesId, fileName);
                articlesMapper.updateArticlesPath(articlesId, thumbPath);
                deletePath.add(PathUtils.getArticleImagePath(existArticles.getPath()));
            }

            // 更新
            articles.setId(id);
            articlesMapper.updateArticle(articles);

            // 記事詳細テーブルを更新
            ArticleDetails existArticleDetails = articleDetailsMapper.selectByArticlesId(articlesId);
            if (existArticleDetails == null) {
                // DBに対象が存在しない場合はエラー
                throw new MenuDeliverException("更新対象が存在しません。");
            }

            contentsPath = PathUtils.createArticleDetailsPath(email, articlesId);
            ArticleDetails articleDetails = new ArticleDetails();
            articleDetails.setArticleId(articlesId);
            articleDetails.setPath(contentsPath);
            deletePath.add(PathUtils.getArticleDetailsPath(existArticleDetails.getPath()));

            // 更新
            articleDetails.setId(existArticleDetails.getId());
            articleDetailsMapper.updateArticleDetails(articleDetails);
        }

        // S3関連の処理を実装する

        // 献立画像を削除
        if (!deletePath.isEmpty()) {
            s3Access.deleteMenuImages(deletePath);
        }

        // サムネイルファイルをS3にアップロードする
        if (thumb != null) {
            File thumbFile = ConvertUtils.convertFile(thumb);
            s3Access.uploadArticleImage(thumbPath, thumbFile);
        }

        // 記事内容をS3にアップロードする
        if (contentsPath != null) {
            s3Access.uploadArticleDetail(contentsPath, contents);
        }
    }

    /**
     * お気に入り記事一覧を取得する
     *
     * @param email メールアドレス
     * @param page  ページ番号
     * @return お気に入り記事一覧
     */
    public ArticlesAndPage getFavoriteArticles(String email, int page) {

        // レスポンス
        ArticlesAndPage result = new ArticlesAndPage();

        // 取得件数
        int limit = 4;

        // 全体の件数を取得する
        int count = articlesMapper.countAllFavoriteArticlesByEmail(email);

        // ページネーションを取得
        PageNation pageNation = PageNationUtils.createPageNation(page, count, limit);

        // 取得ページからoffsetを計算する
        int offset = (pageNation.getCurrentPage() - 1) * limit;

        // メールアドレスから記事内容以外を取得する
        List<Articles> articlesList = articlesMapper.selectAllFavoriteArticlesByEmail(email, limit, offset);

        List<ArticleData> articleDataList = convertArticleData(articlesList);

        // レスポンスに値を設定する
        result.setArticleDataList(articleDataList);
        result.setPage(pageNation);

        return result;
    }

    /**
     * 投稿記事一覧を取得する
     *
     * @param email メールアドレス
     * @param page  ページ番号
     * @return 投稿記事一覧
     */
    public ArticlesAndPage getPostedArticle(String email, int page) {

        // レスポンス
        ArticlesAndPage result = new ArticlesAndPage();

        // 取得件数
        int limit = 4;

        // 全体の件数を取得する
        int count = articlesMapper.countAllByEmail(email);

        // ページネーションを取得
        PageNation pageNation = PageNationUtils.createPageNation(page, count, limit);

        // 取得ページからoffsetを計算する
        int offset = (pageNation.getCurrentPage() - 1) * limit;

        // メールアドレスから記事内容以外を取得する
        List<Articles> articlesList = articlesMapper.selectAllByEmail(email, limit, offset);

        List<ArticleData> articleDataList = convertArticleData(articlesList);

        // レスポンスに値を設定する
        result.setArticleDataList(articleDataList);
        result.setPage(pageNation);

        return result;
    }

    /**
     * 記事を検索する
     *
     * @param keywordList 検索キーワード
     * @param page        取得ページ
     * @return 記事一覧
     */
    public ArticlesAndPage searchArticles(List<String> keywordList, int page) {

        // レスポンス
        ArticlesAndPage result = new ArticlesAndPage();

        // 取得件数
        int limit = 4;

        // 全体の件数を取得する
        int count = articlesMapper.countSearchArticles(keywordList);

        // ページネーションを取得
        PageNation pageNation = PageNationUtils.createPageNation(page, count, limit);

        // 取得ページからoffsetを計算する
        int offset = (pageNation.getCurrentPage() - 1) * limit;

        // メールアドレスから記事内容以外を取得する
        List<Articles> articlesList = articlesMapper.searchArticles(keywordList, limit, offset);

        List<ArticleData> articleDataList = convertArticleData(articlesList);

        // レスポンスに値を設定する
        result.setArticleDataList(articleDataList);
        result.setPage(pageNation);

        return result;
    }

    /**
     * 新着記事を取得する
     *
     * @return 新着記事一覧
     */
    public ArticlesAndPage getArticleNewArrival() {

        // レスポンス
        ArticlesAndPage result = new ArticlesAndPage();

        // 取得件数
        int limit = 8;

        // 記事内容以外を取得する
        List<Articles> articlesList = articlesMapper.selectAllNewArrival(limit);

        List<ArticleData> articleDataList = convertArticleData(articlesList);

        // レスポンスに値を設定する
        result.setArticleDataList(articleDataList);

        return result;
    }

    /**
     * 記事情報リストから関連情報を取得し、記事データリストに変換する
     *
     * @param articlesList 記事情報リスト
     * @return 記事データリスト
     */
    private List<ArticleData> convertArticleData(List<Articles> articlesList) {

        // レスポンス記事データ
        ArticleData data = null;
        List<ArticleData> articleDataList = new ArrayList<>();

        // 内容を取得する
        for (Articles articles : articlesList) {

            // ユーザ情報を取得する
            Users user = usersMapper.selectByPrimaryKey(articles.getUserId());

            // 記事内容を取得する
            ArticleDetails contents = articleDetailsMapper.selectByArticlesId(articles.getId());

            // データを詰め替える
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            data = new ArticleData();
            data.setId(articles.getId());
            data.setTitle(articles.getTitle());
            data.setDetail(articles.getStartSentence());
            data.setImgPath(PathUtils.getArticleImagePath(articles.getPath()));
            data.setDate(df.format(articles.getUpdatedAt()));
            data.setUserId(user.getId());
            data.setUserName(user.getName());
            data.setUserIconPath(PathUtils.getUserIconPath(user.getIcon()));
            data.setContents(PathUtils.getArticleDetailsPath(contents.getPath()));
            data.setOpened(articles.getOpened());

            // 記事データを追加
            articleDataList.add(data);
        }

        return articleDataList;
    }
}
