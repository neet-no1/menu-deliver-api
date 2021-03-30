package jp.co.suyama.menu.deliver.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.suyama.menu.deliver.mapper.auto.QuestionImagesMapper;
import jp.co.suyama.menu.deliver.model.db.QuestionImages;

public interface QuestionImagesMapperImpl extends QuestionImagesMapper {

    /**
     * 質問画像を登録する
     */
    // @formatter:off
    @Insert({
        "insert into question_images"
      , "(question_id, path, created_at, updated_at)"
      , "values"
      , "("
      , "  #{questionId,jdbcType=INTEGER},"
      , "  #{path,jdbcType=VARCHAR},"
      , "  current_timestamp,"
      , "  current_timestamp"
      , ")"
    })
    // @formatter:on
    public int registQuestionImage(QuestionImages record);

    /**
     * 質問IDを元に質問画像を取得する
     */
    // @formatter:off
    @Select({
        "select"
      , "  *"
      , "from question_images"
      , "where question_id = #{questionId,jdbcType=INTEGER}"
    })
    // @formatter:on
    public QuestionImages selectByQuestionId(@Param("questionId") int questionId);
}
