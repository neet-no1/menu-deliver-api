package jp.co.suyama.menu.deliver.mapper.auto;

import java.util.Date;
import java.util.List;
import jp.co.suyama.menu.deliver.model.db.MenuViews;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface MenuViewsMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table menu_views
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	@Delete({ "delete from menu_views", "where menu_id = #{menuId,jdbcType=INTEGER}",
			"and date = #{date,jdbcType=TIMESTAMP}" })
	int deleteByPrimaryKey(@Param("menuId") Integer menuId, @Param("date") Date date);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table menu_views
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	@Insert({ "insert into menu_views (menu_id, date, ", "count)",
			"values (#{menuId,jdbcType=INTEGER}, #{date,jdbcType=TIMESTAMP}, ", "#{count,jdbcType=INTEGER})" })
	int insert(MenuViews record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table menu_views
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	@Select({ "select", "menu_id, date, count", "from menu_views", "where menu_id = #{menuId,jdbcType=INTEGER}",
			"and date = #{date,jdbcType=TIMESTAMP}" })
	@Results({ @Result(column = "menu_id", property = "menuId", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "date", property = "date", jdbcType = JdbcType.TIMESTAMP, id = true),
			@Result(column = "count", property = "count", jdbcType = JdbcType.INTEGER) })
	MenuViews selectByPrimaryKey(@Param("menuId") Integer menuId, @Param("date") Date date);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table menu_views
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	@Select({ "select", "menu_id, date, count", "from menu_views" })
	@Results({ @Result(column = "menu_id", property = "menuId", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "date", property = "date", jdbcType = JdbcType.TIMESTAMP, id = true),
			@Result(column = "count", property = "count", jdbcType = JdbcType.INTEGER) })
	List<MenuViews> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table menu_views
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	@Update({ "update menu_views", "set count = #{count,jdbcType=INTEGER}",
			"where menu_id = #{menuId,jdbcType=INTEGER}", "and date = #{date,jdbcType=TIMESTAMP}" })
	int updateByPrimaryKey(MenuViews record);
}