package jp.co.suyama.menu.deliver.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import jp.co.suyama.menu.deliver.mapper.auto.ArticleViewsMapper;

public interface ArticleViewsMapperImpl extends ArticleViewsMapper {

    /**
     * 記事閲覧数を追加する
     */
    // @formatter:off
    @Insert({
          "insert into article_views"
        , "(article_id, date, count)"
        , "values"
        , "("
        , "  #{articleId,jdbcType=INTEGER},"
        , "  current_timestamp,"
        , "  1"
        , ")"
    })
    // @formatter:on
    int registArticleViews(@Param("articleId") int articleId);

    /**
     * 記事IDを元に全ての記事閲覧数を削除する
     */
    // @formatter:off
    @Delete({
          "delete from article_views"
        , "where article_id = #{articleId,jdbcType=INTEGER}"
    })
    // @formatter:on
    public void deleteAllByArticleId(@Param("articleId") int articleId);
}
