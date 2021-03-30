package jp.co.suyama.menu.deliver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
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

    /**
     * <pre>
     * 新着質問検索をする
     * キーワードは部分一致でAND
     * </pre>
     */
    // @formatter:off
    @Select({
      "<script>"
      , "select"
      , "  q.id,"
      , "  q.user_id,"
      , "  q.contents,"
      , "  q.answer_id,"
      , "  q.created_at,"
      , "  q.updated_at"
      , "from questions q"
      , "inner join users u"
      , "  on q.user_id = u.id"
      , "where"
      , "  u.deleted = FALSE"
      , "<if test='keywordList != null and keywordList.size() > 0'>"
      , "  <foreach item='item' collection='keywordList'>"
      , "    and q.contents LIKE CONCAT('%', #{item}, '%')"
      , "  </foreach>"
      , "</if>"
      , "order by updated_at desc"
      , "limit #{limit,jdbcType=INTEGER}"
      , "offset #{offset,jdbcType=INTEGER}"
      , "</script>"
    })
    // @formatter:on
    public List<Questions> searchQuestionNewArrival(@Param("keywordList") List<String> keywordList,
            @Param("limit") int limit, @Param("offset") int offset);

    /**
     * <pre>
     * 新着質問検索時の件数を取得する
     * キーワードは部分一致でAND
     * </pre>
     */
    // @formatter:off
    @Select({
      "<script>"
      , "select"
      , "  count(q.id)"
      , "from questions q"
      , "inner join users u"
      , "  on q.user_id = u.id"
      , "where"
      , "  u.deleted = FALSE"
      , "<if test='keywordList != null and keywordList.size() > 0'>"
      , "  <foreach item='item' collection='keywordList'>"
      , "    and q.contents LIKE CONCAT('%', #{item}, '%')"
      , "  </foreach>"
      , "</if>"
      , "</script>"
    })
    // @formatter:on
    public int countQuestionNewArrival(@Param("keywordList") List<String> keywordList);

    /**
     * <pre>
     * 新着質問検索をする
     * キーワードは部分一致でAND
     * </pre>
     */
    // @formatter:off
    @Select({
      "<script>"
      , "select"
      , "  q.id,"
      , "  q.user_id,"
      , "  q.contents,"
      , "  q.answer_id,"
      , "  q.created_at,"
      , "  q.updated_at"
      , "from questions q"
      , "inner join users u"
      , "  on q.user_id = u.id"
      , "where"
      , "  u.deleted = FALSE"
      , "<if test='keywordList != null and keywordList.size() > 0'>"
      , "  <foreach item='item' collection='keywordList'>"
      , "    and q.contents LIKE CONCAT('%', #{item}, '%')"
      , "  </foreach>"
      , "</if>"
      , "<if test='!solved'>"
      , "  and q.answer_id = 0"
      , "</if>"
      , "<if test='solved'>"
      , "  and q.answer_id != 0"
      , "</if>"
      , "order by updated_at desc"
      , "limit #{limit,jdbcType=INTEGER}"
      , "offset #{offset,jdbcType=INTEGER}"
      , "</script>"
    })
    // @formatter:on
    public List<Questions> searchQuestion(@Param("keywordList") List<String> keywordList,
            @Param("solved") boolean solved, @Param("limit") int limit, @Param("offset") int offset);

    /**
     * <pre>
     * 新着質問検索時の件数を取得する
     * キーワードは部分一致でAND
     * </pre>
     */
    // @formatter:off
    @Select({
      "<script>"
      , "select"
      , "  count(q.id)"
      , "from questions q"
      , "inner join users u"
      , "  on q.user_id = u.id"
      , "where"
      , "  u.deleted = FALSE"
      , "<if test='keywordList != null and keywordList.size() > 0'>"
      , "  <foreach item='item' collection='keywordList'>"
      , "    and q.contents LIKE CONCAT('%', #{item}, '%')"
      , "  </foreach>"
      , "</if>"
      , "<if test='!solved'>"
      , "  and q.answer_id = 0"
      , "</if>"
      , "<if test='solved'>"
      , "  and q.answer_id != 0"
      , "</if>"
      , "</script>"
    })
    // @formatter:on
    public int countQuestion(@Param("keywordList") List<String> keywordList, @Param("solved") boolean solved);
}
