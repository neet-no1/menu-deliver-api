package jp.co.suyama.menu.deliver.mapper.auto;

import java.util.List;
import jp.co.suyama.menu.deliver.model.db.Questions;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface QuestionsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table questions
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    @Delete({
        "delete from questions",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table questions
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    @Insert({
        "insert into questions (id, user_id, ",
        "contents, answer_id, ",
        "created_at, updated_at)",
        "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{contents,jdbcType=VARCHAR}, #{answerId,jdbcType=INTEGER}, ",
        "#{createdAt,jdbcType=TIMESTAMP}, #{updatedAt,jdbcType=TIMESTAMP})"
    })
    int insert(Questions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table questions
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    @Select({
        "select",
        "id, user_id, contents, answer_id, created_at, updated_at",
        "from questions",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="contents", property="contents", jdbcType=JdbcType.VARCHAR),
        @Result(column="answer_id", property="answerId", jdbcType=JdbcType.INTEGER),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    Questions selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table questions
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    @Select({
        "select",
        "id, user_id, contents, answer_id, created_at, updated_at",
        "from questions"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="contents", property="contents", jdbcType=JdbcType.VARCHAR),
        @Result(column="answer_id", property="answerId", jdbcType=JdbcType.INTEGER),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Questions> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table questions
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    @Update({
        "update questions",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "contents = #{contents,jdbcType=VARCHAR},",
          "answer_id = #{answerId,jdbcType=INTEGER},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP},",
          "updated_at = #{updatedAt,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Questions record);
}