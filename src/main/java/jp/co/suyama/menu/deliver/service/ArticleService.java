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
import jp.co.suyama.menu.deliver.mapper.ArticleViewsMapperImpl;
import jp.co.suyama.menu.deliver.mapper.ArticlesMapperImpl;
import jp.co.suyama.menu.deliver.mapper.FavoriteArticlesMapperImpl;
import jp.co.suyama.menu.deliver.mapper.UsersMapperImpl;
import jp.co.suyama.menu.deliver.model.ArticlesAndPage;
import jp.co.suyama.menu.deliver.model.auto.ArticleData;
import jp.co.suyama.menu.deliver.model.auto.PageNation;
import jp.co.suyama.menu.deliver.model.db.ArticleDetails;
import jp.co.suyama.menu.deliver.model.db.Articles;
import jp.co.suyama.menu.deliver.model.db.FavoriteArticles;
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

    // 記事閲覧数テーブル
    @Autowired
    private ArticleViewsMapperImpl articleViewsMapper;

    // お気に入り記事テーブル
    @Autowired
    private FavoriteArticlesMapperImpl favoriteArticlesMapper;

    // ユーザテーブル
    @Autowired
    private UsersMapperImpl usersMapper;

    /**
     * 記事を削除する
     *
     * @param email メールアドレス
     * @param id    記事ID
     */
    public void deleteArticle(String email, int id) {

        // ユーザ情報取得
        Users user = usersMapper.selectEmail(email);

        // 存在していない場合エラー
        if (user == null) {
            throw new MenuDeliverException("ユーザが存在しません。");
        }

        // 記事を取得
        Articles article = articlesMapper.selectByPrimaryKey(id);

        if (article == null) {
            // 記事が存在しない場合、その場で終了
            return;
        }

        // 自分が投稿したものでなければ削除不可
        if (article.getUserId() != user.getId()) {
            throw new MenuDeliverException("削除対象が自身のものではありません。");
        }

        // 記事を削除
        articlesMapper.deleteByPrimaryKey(id);

        // お気に入り記事を削除
        favoriteArticlesMapper.deleteAllByArticleId(id);

        // 記事内容を取得
        ArticleDetails details = articleDetailsMapper.selectByArticlesId(id);

        // 記事内容を削除
        articleDetailsMapper.deleteByPrimaryKey(details.getId());

        // 記事閲覧数を削除
        articleViewsMapper.deleteAllByArticleId(id);

        // S3のものをすべて削除する
        List<String> deleteKeys = new ArrayList<>();
        deleteKeys.add(PathUtils.getArticleImagePath(article.getPath()));
        deleteKeys.add(PathUtils.getArticleDetailsPath(details.getPath()));

        s3Access.deleteItems(deleteKeys);
    }

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
        articles.setPath("no_image");

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
                articles.setPath(thumbPath);
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
            s3Access.deleteItems(deletePath);
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
     * 記事をお気に入りに登録する
     *
     * @param email メールアドレス
     * @param id    記事ID
     * @param added 追加・解除フラグ
     */
    public void favoriteArticle(String email, int id, boolean added) {

        // ユーザ情報取得
        Users user = usersMapper.selectEmail(email);

        // 存在していない場合エラー
        if (user == null) {
            throw new MenuDeliverException("ユーザが存在しません。");
        }

        // 記事情報取得
        Articles article = articlesMapper.selectByPrimaryKey(id);

        // 存在していない場合エラー
        if (article == null) {
            throw new MenuDeliverException("記事が存在しません。");
        }

        if (added) {
            // 追加処理
            // 存在するか確認
            FavoriteArticles favoriteArticle = favoriteArticlesMapper.selectByUserIdArticleId(user.getId(),
                    article.getId());

            if (favoriteArticle != null) {
                // 存在する場合、何もせず終了
                return;
            }

            // 追加
            FavoriteArticles registFavoriteArticle = new FavoriteArticles();
            registFavoriteArticle.setUserId(user.getId());
            registFavoriteArticle.setArticleId(article.getId());

            favoriteArticlesMapper.registFavoriteArticles(registFavoriteArticle);

        } else {
            // 解除処理
            favoriteArticlesMapper.deleteByUserIdArticleId(user.getId(), article.getId());
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

        List<ArticleData> articleDataList = convertArticleDataList(null, articlesList);

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

        List<ArticleData> articleDataList = convertArticleDataList(null, articlesList);

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

        List<ArticleData> articleDataList = convertArticleDataList(null, articlesList);

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

        List<ArticleData> articleDataList = convertArticleDataList(null, articlesList);

        // レスポンスに値を設定する
        result.setArticleDataList(articleDataList);

        return result;
    }

    /**
     * 記事内容を取得する
     *
     * @param email メールアドレス
     * @param id    記事ID
     * @return 記事内容
     */
    public ArticleData getArticle(String email, int id) {

        // ユーザ情報取得
        Integer userId = null;
        Users user = usersMapper.selectEmail(email);

        // 存在する場合、ユーザIDを設定
        if (user != null) {
            userId = user.getId();
        }

        // 記事情報を取得する
        Articles article = articlesMapper.selectByPrimaryKey(id);

        // 存在しない場合エラー
        if (article == null) {
            throw new MenuDeliverException("記事が存在しません。");
        }

        // 詰め替えする
        ArticleData result = convertArticleData(userId, article);

        // 記事閲覧数を追加
        if (!result.isMine()) {
            // 自分のもの以外の場合追加
            articleViewsMapper.registArticleViews(result.getId());
        }

        return result;
    }

    /**
     * 記事情報リストから関連情報を取得し、記事データリストに変換する
     *
     * @param userId       ユーザID(自分の投稿かを判断する)
     * @param articlesList 記事情報リスト
     * @return 記事データリスト
     */
    private List<ArticleData> convertArticleDataList(Integer userId, List<Articles> articlesList) {

        // レスポンス記事データ
        List<ArticleData> articleDataList = new ArrayList<>();

        // 内容を取得する
        for (Articles articles : articlesList) {
            // 記事データを追加
            articleDataList.add(convertArticleData(userId, articles));
        }

        return articleDataList;
    }

    /**
     * 記事情報リストから関連情報を取得し、記事データリストに変換する
     *
     * @param userId   ユーザID(自分の投稿かを判断する)
     * @param articles 記事情報
     * @return 記事データ
     */
    private ArticleData convertArticleData(Integer userId, Articles articles) {

        // ユーザ情報を取得する
        Users user = usersMapper.selectByPrimaryKey(articles.getUserId());

        // 記事内容を取得する
        ArticleDetails contents = articleDetailsMapper.selectByArticlesId(articles.getId());

        // データを詰め替える
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        ArticleData data = new ArticleData();
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
        data.setMine(userId == null ? false : userId == articles.getUserId());

        return data;
    }
}
