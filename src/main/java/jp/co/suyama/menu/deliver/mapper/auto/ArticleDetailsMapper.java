package jp.co.suyama.menu.deliver.mapper.auto;

import java.util.List;
import jp.co.suyama.menu.deliver.model.db.ArticleDetails;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface ArticleDetailsMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article_details
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	@Delete({ "delete from article_details", "where id = #{id,jdbcType=INTEGER}" })
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article_details
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	@Insert({ "insert into article_details (id, article_id, ", "path, created_at, ", "updated_at)",
			"values (#{id,jdbcType=INTEGER}, #{articleId,jdbcType=INTEGER}, ",
			"#{path,jdbcType=VARCHAR}, #{createdAt,jdbcType=TIMESTAMP}, ", "#{updatedAt,jdbcType=TIMESTAMP})" })
	int insert(ArticleDetails record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article_details
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	@Select({ "select", "id, article_id, path, created_at, updated_at", "from article_details",
			"where id = #{id,jdbcType=INTEGER}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "article_id", property = "articleId", jdbcType = JdbcType.INTEGER),
			@Result(column = "path", property = "path", jdbcType = JdbcType.VARCHAR),
			@Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP) })
	ArticleDetails selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article_details
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	@Select({ "select", "id, article_id, path, created_at, updated_at", "from article_details" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "article_id", property = "articleId", jdbcType = JdbcType.INTEGER),
			@Result(column = "path", property = "path", jdbcType = JdbcType.VARCHAR),
			@Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP) })
	List<ArticleDetails> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article_details
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	@Update({ "update article_details", "set article_id = #{articleId,jdbcType=INTEGER},",
			"path = #{path,jdbcType=VARCHAR},", "created_at = #{createdAt,jdbcType=TIMESTAMP},",
			"updated_at = #{updatedAt,jdbcType=TIMESTAMP}", "where id = #{id,jdbcType=INTEGER}" })
	int updateByPrimaryKey(ArticleDetails record);
}