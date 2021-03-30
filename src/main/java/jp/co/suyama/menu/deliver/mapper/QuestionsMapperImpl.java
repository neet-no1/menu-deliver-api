package jp.co.suyama.menu.deliver.mapper;

import org.apache.ibatis.annotations.Select;

import jp.co.suyama.menu.deliver.mapper.auto.QuestionsMapper;
import jp.co.suyama.menu.deliver.model.db.Questions;

public interface QuestionsMapperImpl extends QuestionsMapper {

    /**
     * 質問を登録する
     */
    // @formatter:off
    @Select({
        "insert into questions"
      , "(user_id, contents, created_at, updated_at)"
      , "values"
      , "("
      , "  #{userId,jdbcType=INTEGER},"
      , "  #{contents,jdbcType=VARCHAR},"
      , "  current_timestamp,"
      , "  current_timestamp"
      , ")"
      , "returning id"
  })
  // @formatter:on
    public int registQuestion(Questions record);
}
