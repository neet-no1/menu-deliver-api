package jp.co.suyama.menu.deliver.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.suyama.menu.deliver.mapper.auto.ArticleDetailsMapper;
import jp.co.suyama.menu.deliver.model.db.ArticleDetails;

public interface ArticleDetailsMapperImpl extends ArticleDetailsMapper {

    /**
     * 記事詳細を登録する
     */
    // @formatter:off
    @Insert({
          "insert into article_details"
        , "(article_id, path, created_at, updated_at)"
        , "values"
        , "("
        , "  #{articleId,jdbcType=INTEGER},"
        , "  #{path,jdbcType=VARCHAR},"
        , "  current_timestamp,"
        , "  current_timestamp"
        , ")"
    })
    // @formatter:on
    public void registArticleDetails(ArticleDetails record);

    /**
     * <pre>
     * 記事IDを元に記事詳細を取得する
     * 記事詳細IDと記事IDは 1:1 であるため、必ず1つだけ取得される
     * </pre>
     */
    // @formatter:off
    @Select({
          "select"
        , "  *"
        , "from article_details"
        , "where"
        , "  article_id = #{articleId,jdbcType=INTEGER}"
    })
    // @formatter:on
    public ArticleDetails selectByArticlesId(@Param("articleId") int articleId);

    /**
     * 記事詳細を更新する
     */
    // @formatter:off
    @Update({
          "update article_details"
        , "set"
        , "  article_id = #{articleId,jdbcType=INTEGER},"
        , "  path = #{path,jdbcType=VARCHAR},"
        , "  updated_at = current_timestamp"
        , "where id = #{id,jdbcType=INTEGER}"
    })
    // @formatter:on
    public void updateArticleDetails(ArticleDetails record);
}
