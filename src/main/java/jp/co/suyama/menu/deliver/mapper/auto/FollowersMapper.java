package jp.co.suyama.menu.deliver.mapper.auto;

import java.util.List;
import jp.co.suyama.menu.deliver.model.db.Followers;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface FollowersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table followers
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    @Delete({
        "delete from followers",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table followers
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    @Insert({
        "insert into followers (id, user_id, ",
        "follower_user_id, created_at, ",
        "updated_at)",
        "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{followerUserId,jdbcType=INTEGER}, #{createdAt,jdbcType=TIMESTAMP}, ",
        "#{updatedAt,jdbcType=TIMESTAMP})"
    })
    int insert(Followers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table followers
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    @Select({
        "select",
        "id, user_id, follower_user_id, created_at, updated_at",
        "from followers",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="follower_user_id", property="followerUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    Followers selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table followers
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    @Select({
        "select",
        "id, user_id, follower_user_id, created_at, updated_at",
        "from followers"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="follower_user_id", property="followerUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Followers> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table followers
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    @Update({
        "update followers",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "follower_user_id = #{followerUserId,jdbcType=INTEGER},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP},",
          "updated_at = #{updatedAt,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Followers record);
}