package jp.co.suyama.menu.deliver.mapper.auto;

import java.util.List;
import jp.co.suyama.menu.deliver.model.db.MenuDetails;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface MenuDetailsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_details
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    @Delete({
        "delete from menu_details",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_details
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    @Insert({
        "insert into menu_details (id, menu_id, ",
        "path, created_at, ",
        "updated_at)",
        "values (#{id,jdbcType=INTEGER}, #{menuId,jdbcType=INTEGER}, ",
        "#{path,jdbcType=VARCHAR}, #{createdAt,jdbcType=TIMESTAMP}, ",
        "#{updatedAt,jdbcType=TIMESTAMP})"
    })
    int insert(MenuDetails record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_details
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    @Select({
        "select",
        "id, menu_id, path, created_at, updated_at",
        "from menu_details",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="menu_id", property="menuId", jdbcType=JdbcType.INTEGER),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    MenuDetails selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_details
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    @Select({
        "select",
        "id, menu_id, path, created_at, updated_at",
        "from menu_details"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="menu_id", property="menuId", jdbcType=JdbcType.INTEGER),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<MenuDetails> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_details
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    @Update({
        "update menu_details",
        "set menu_id = #{menuId,jdbcType=INTEGER},",
          "path = #{path,jdbcType=VARCHAR},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP},",
          "updated_at = #{updatedAt,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MenuDetails record);
}