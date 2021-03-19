package jp.co.suyama.menu.deliver.mapper.auto;

import java.util.List;
import jp.co.suyama.menu.deliver.model.db.MenuCategories;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface MenuCategoriesMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table menu_categories
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	@Delete({ "delete from menu_categories", "where id = #{id,jdbcType=INTEGER}" })
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table menu_categories
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	@Insert({ "insert into menu_categories (id, name, ", "created_at, updated_at)",
			"values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
			"#{createdAt,jdbcType=TIMESTAMP}, #{updatedAt,jdbcType=TIMESTAMP})" })
	int insert(MenuCategories record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table menu_categories
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	@Select({ "select", "id, name, created_at, updated_at", "from menu_categories",
			"where id = #{id,jdbcType=INTEGER}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
			@Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP) })
	MenuCategories selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table menu_categories
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	@Select({ "select", "id, name, created_at, updated_at", "from menu_categories" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
			@Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP) })
	List<MenuCategories> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table menu_categories
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	@Update({ "update menu_categories", "set name = #{name,jdbcType=VARCHAR},",
			"created_at = #{createdAt,jdbcType=TIMESTAMP},", "updated_at = #{updatedAt,jdbcType=TIMESTAMP}",
			"where id = #{id,jdbcType=INTEGER}" })
	int updateByPrimaryKey(MenuCategories record);
}