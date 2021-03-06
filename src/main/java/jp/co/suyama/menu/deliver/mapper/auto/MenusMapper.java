package jp.co.suyama.menu.deliver.mapper.auto;

import java.util.List;
import jp.co.suyama.menu.deliver.model.db.Menus;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface MenusMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table menus
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	@Delete({ "delete from menus", "where id = #{id,jdbcType=INTEGER}" })
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table menus
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	@Insert({ "insert into menus (id, user_id, ", "title, sub_title, ", "path, category_id, ", "opened, created_at, ",
			"updated_at)", "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
			"#{title,jdbcType=VARCHAR}, #{subTitle,jdbcType=VARCHAR}, ",
			"#{path,jdbcType=VARCHAR}, #{categoryId,jdbcType=INTEGER}, ",
			"#{opened,jdbcType=BIT}, #{createdAt,jdbcType=TIMESTAMP}, ", "#{updatedAt,jdbcType=TIMESTAMP})" })
	int insert(Menus record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table menus
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	@Select({ "select", "id, user_id, title, sub_title, path, category_id, opened, created_at, updated_at",
			"from menus", "where id = #{id,jdbcType=INTEGER}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
			@Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
			@Result(column = "sub_title", property = "subTitle", jdbcType = JdbcType.VARCHAR),
			@Result(column = "path", property = "path", jdbcType = JdbcType.VARCHAR),
			@Result(column = "category_id", property = "categoryId", jdbcType = JdbcType.INTEGER),
			@Result(column = "opened", property = "opened", jdbcType = JdbcType.BIT),
			@Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP) })
	Menus selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table menus
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	@Select({ "select", "id, user_id, title, sub_title, path, category_id, opened, created_at, updated_at",
			"from menus" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
			@Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
			@Result(column = "sub_title", property = "subTitle", jdbcType = JdbcType.VARCHAR),
			@Result(column = "path", property = "path", jdbcType = JdbcType.VARCHAR),
			@Result(column = "category_id", property = "categoryId", jdbcType = JdbcType.INTEGER),
			@Result(column = "opened", property = "opened", jdbcType = JdbcType.BIT),
			@Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP) })
	List<Menus> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table menus
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	@Update({ "update menus", "set user_id = #{userId,jdbcType=INTEGER},", "title = #{title,jdbcType=VARCHAR},",
			"sub_title = #{subTitle,jdbcType=VARCHAR},", "path = #{path,jdbcType=VARCHAR},",
			"category_id = #{categoryId,jdbcType=INTEGER},", "opened = #{opened,jdbcType=BIT},",
			"created_at = #{createdAt,jdbcType=TIMESTAMP},", "updated_at = #{updatedAt,jdbcType=TIMESTAMP}",
			"where id = #{id,jdbcType=INTEGER}" })
	int updateByPrimaryKey(Menus record);
}