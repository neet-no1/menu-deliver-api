package jp.co.suyama.menu.deliver.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.suyama.menu.deliver.mapper.auto.FavoriteArticlesMapper;
import jp.co.suyama.menu.deliver.model.db.FavoriteArticles;

public interface FavoriteArticlesMapperImpl extends FavoriteArticlesMapper {

    // @formatter:off
    @Delete({
        "delete from favorite_articles"
      , "where article_id = #{articleId,jdbcType=INTEGER}"
    })
    // @formatter:on
    int deleteAllByArticleId(@Param("articleId") int articleId);

    // @formatter:off
    @Select({
          "select"
        , "  *"
        , "from favorite_articles"
        , "where"
        , "  user_id = #{userId,jdbcType=INTEGER}"
        , "  and article_id = #{articleId,jdbcType=INTEGER}"
    })
    // @formatter:on
    FavoriteArticles selectByUserIdArticleId(@Param("userId") int user_id, @Param("articleId") int articleId);

    // @formatter:off
    @Insert({
          "insert into favorite_articles"
        , "(user_id, article_id, created_at, updated_at)"
        , "values"
        , "("
        , "  #{userId,jdbcType=INTEGER},"
        , "  #{articleId,jdbcType=INTEGER},"
        , "  current_timestamp,"
        , "  current_timestamp"
        , ")"
    })
    // @formatter:on
    int registFavoriteArticles(FavoriteArticles record);

    // @formatter:off
    @Delete({
        "delete from favorite_articles"
      , "where"
      , "  user_id = #{userId,jdbcType=INTEGER}"
      , "  and article_id = #{articleId,jdbcType=INTEGER}"
    })
    // @formatter:on
    int deleteByUserIdArticleId(@Param("userId") int user_id, @Param("articleId") int articleId);
}
