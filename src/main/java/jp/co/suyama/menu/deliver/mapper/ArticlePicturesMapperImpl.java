package jp.co.suyama.menu.deliver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.suyama.menu.deliver.mapper.auto.ArticlePicturesMapper;
import jp.co.suyama.menu.deliver.model.db.ArticlePictures;

public interface ArticlePicturesMapperImpl extends ArticlePicturesMapper {

    /**
     * 記事IDを元に全ての記事画像を取得する
     */
    // @formatter:off
    @Select({
          "select"
        , "  *"
        , "from article_pictures"
        , "where article_id = #{articleId,jdbcType=INTEGER}"
        , "order by order_of"
    })
    // @formatter:on
    public List<ArticlePictures> selectAllByMenuId(@Param("articleId") int articleId);
}
