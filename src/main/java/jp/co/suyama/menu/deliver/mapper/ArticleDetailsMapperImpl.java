package jp.co.suyama.menu.deliver.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.suyama.menu.deliver.mapper.auto.ArticleDetailsMapper;
import jp.co.suyama.menu.deliver.model.db.ArticleDetails;

public interface ArticleDetailsMapperImpl extends ArticleDetailsMapper {

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
}
