package jp.co.suyama.menu.deliver.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.suyama.menu.deliver.mapper.ArticleDetailsMapperImpl;
import jp.co.suyama.menu.deliver.mapper.ArticlesMapperImpl;
import jp.co.suyama.menu.deliver.mapper.UsersMapperImpl;
import jp.co.suyama.menu.deliver.model.ArticlesAndPage;
import jp.co.suyama.menu.deliver.model.auto.ArticleData;
import jp.co.suyama.menu.deliver.model.auto.PageNation;
import jp.co.suyama.menu.deliver.model.db.ArticleDetails;
import jp.co.suyama.menu.deliver.model.db.Articles;
import jp.co.suyama.menu.deliver.model.db.Users;
import jp.co.suyama.menu.deliver.utils.PageNationUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleService {

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
     * お気に入り記事一覧を取得する
     *
     * @param email メールアドレス
     * @param page  ページ番号
     * @return お気に入り記事一覧
     */
    public ArticlesAndPage getFavoriteArticles(String email, int page) {

        // レスポンス
        ArticlesAndPage result = new ArticlesAndPage();

        // レスポンス記事データ
        ArticleData data = null;
        List<ArticleData> articleDataList = new ArrayList<>();

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

        // ユーザ情報を取得する
        Users user = usersMapper.selectEmail(email);

        // 内容を取得する
        for (Articles articles : articlesList) {

            // 記事内容を取得する
            ArticleDetails contents = articleDetailsMapper.selectByArticlesId(articles.getId());

            // データを詰め替える
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            data = new ArticleData();
            data.setId(articles.getId());
            data.setTitle(articles.getTitle());
            data.setDetail(articles.getStartSentence());
            data.setImgPath(articles.getPath());
            data.setDate(df.format(articles.getUpdatedAt()));
            data.setUserId(user.getId());
            data.setUserName(user.getName());
            data.setUserIconPath(user.getIcon());
            data.setContents(contents.getPath());
            data.setOpened(articles.getOpened());

            // 記事データを追加
            articleDataList.add(data);
        }

        // レスポンスに値を設定する
        result.setArticleDataList(articleDataList);
        result.setPage(pageNation);

        return result;
    }
}