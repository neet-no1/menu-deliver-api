package jp.co.suyama.menu.deliver.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.suyama.menu.deliver.mapper.auto.AnswerImagesMapper;
import jp.co.suyama.menu.deliver.model.db.AnswerImages;

public interface AnswerImagesMapperImpl extends AnswerImagesMapper {

    /**
     * 回答画像を登録する
     */
    // @formatter:off
    @Insert({
        "insert into answer_images"
      , "(answer_id, path, created_at, updated_at)"
      , "values"
      , "("
      , "  #{answerId,jdbcType=INTEGER},"
      , "  #{path,jdbcType=VARCHAR},"
      , "  current_timestamp,"
      , "  current_timestamp"
      , ")"
    })
    // @formatter:on
    public int registAnswerImage(AnswerImages record);

    /**
     * 質問IDを元に質問画像を取得する
     */
    // @formatter:off
    @Select({
        "select"
      , "  *"
      , "from answer_images"
      , "where answer_id = #{answerId,jdbcType=INTEGER}"
    })
    // @formatter:on
    public AnswerImages selectByAnswerId(@Param("answerId") int answerId);
}
