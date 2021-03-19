package jp.co.suyama.menu.deliver.mapper.auto;

import java.util.Date;
import java.util.List;
import jp.co.suyama.menu.deliver.model.db.ArticleViews;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface ArticleViewsMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article_views
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	@Delete({ "delete from article_views", "where article_id = #{articleId,jdbcType=INTEGER}",
			"and date = #{date,jdbcType=TIMESTAMP}" })
	int deleteByPrimaryKey(@Param("articleId") Integer articleId, @Param("date") Date date);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article_views
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	@Insert({ "insert into article_views (article_id, date, ", "count)",
			"values (#{articleId,jdbcType=INTEGER}, #{date,jdbcType=TIMESTAMP}, ", "#{count,jdbcType=INTEGER})" })
	int insert(ArticleViews record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article_views
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	@Select({ "select", "article_id, date, count", "from article_views",
			"where article_id = #{articleId,jdbcType=INTEGER}", "and date = #{date,jdbcType=TIMESTAMP}" })
	@Results({ @Result(column = "article_id", property = "articleId", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "date", property = "date", jdbcType = JdbcType.TIMESTAMP, id = true),
			@Result(column = "count", property = "count", jdbcType = JdbcType.INTEGER) })
	ArticleViews selectByPrimaryKey(@Param("articleId") Integer articleId, @Param("date") Date date);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article_views
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	@Select({ "select", "article_id, date, count", "from article_views" })
	@Results({ @Result(column = "article_id", property = "articleId", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "date", property = "date", jdbcType = JdbcType.TIMESTAMP, id = true),
			@Result(column = "count", property = "count", jdbcType = JdbcType.INTEGER) })
	List<ArticleViews> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article_views
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	@Update({ "update article_views", "set count = #{count,jdbcType=INTEGER}",
			"where article_id = #{articleId,jdbcType=INTEGER}", "and date = #{date,jdbcType=TIMESTAMP}" })
	int updateByPrimaryKey(ArticleViews record);
}