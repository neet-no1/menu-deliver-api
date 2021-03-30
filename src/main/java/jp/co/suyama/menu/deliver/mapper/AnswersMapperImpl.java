package jp.co.suyama.menu.deliver.mapper;

import org.apache.ibatis.annotations.Select;

import jp.co.suyama.menu.deliver.mapper.auto.AnswersMapper;
import jp.co.suyama.menu.deliver.model.db.Answers;

public interface AnswersMapperImpl extends AnswersMapper {

    /**
     * 回答を登録する
     */
    // @formatter:off
    @Select({
        "insert into answers"
      , "(question_id, user_id, contents, created_at, updated_at)"
      , "values"
      , "("
      , "  #{questionId,jdbcType=INTEGER},"
      , "  #{userId,jdbcType=INTEGER},"
      , "  #{contents,jdbcType=VARCHAR},"
      , "  current_timestamp,"
      , "  current_timestamp"
      , ")"
      , "returning id"
    })
    // @formatter:on
    public int registAnswer(Answers record);
}
