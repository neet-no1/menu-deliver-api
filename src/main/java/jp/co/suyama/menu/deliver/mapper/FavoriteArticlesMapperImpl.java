package jp.co.suyama.menu.deliver.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import jp.co.suyama.menu.deliver.mapper.auto.FavoriteArticlesMapper;

public interface FavoriteArticlesMapperImpl extends FavoriteArticlesMapper {

    // @formatter:off
    @Delete({
        "delete from favorite_articles"
      , "where article_id = #{articleId,jdbcType=INTEGER}"
    })
    // @formatter:on
    int deleteAllByArticleId(@Param("articleId") int articleId);
}
