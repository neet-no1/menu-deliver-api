package jp.co.suyama.menu.deliver.mapper.auto;

import java.util.List;
import jp.co.suyama.menu.deliver.model.db.QuestionImages;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface QuestionImagesMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table question_images
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	@Delete({ "delete from question_images", "where id = #{id,jdbcType=INTEGER}" })
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table question_images
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	@Insert({ "insert into question_images (id, question_id, ", "path, created_at, ", "updated_at)",
			"values (#{id,jdbcType=INTEGER}, #{questionId,jdbcType=INTEGER}, ",
			"#{path,jdbcType=VARCHAR}, #{createdAt,jdbcType=TIMESTAMP}, ", "#{updatedAt,jdbcType=TIMESTAMP})" })
	int insert(QuestionImages record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table question_images
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	@Select({ "select", "id, question_id, path, created_at, updated_at", "from question_images",
			"where id = #{id,jdbcType=INTEGER}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "question_id", property = "questionId", jdbcType = JdbcType.INTEGER),
			@Result(column = "path", property = "path", jdbcType = JdbcType.VARCHAR),
			@Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP) })
	QuestionImages selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table question_images
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	@Select({ "select", "id, question_id, path, created_at, updated_at", "from question_images" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "question_id", property = "questionId", jdbcType = JdbcType.INTEGER),
			@Result(column = "path", property = "path", jdbcType = JdbcType.VARCHAR),
			@Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP) })
	List<QuestionImages> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table question_images
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	@Update({ "update question_images", "set question_id = #{questionId,jdbcType=INTEGER},",
			"path = #{path,jdbcType=VARCHAR},", "created_at = #{createdAt,jdbcType=TIMESTAMP},",
			"updated_at = #{updatedAt,jdbcType=TIMESTAMP}", "where id = #{id,jdbcType=INTEGER}" })
	int updateByPrimaryKey(QuestionImages record);
}