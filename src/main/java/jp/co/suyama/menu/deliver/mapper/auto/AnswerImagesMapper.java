package jp.co.suyama.menu.deliver.mapper.auto;

import java.util.List;
import jp.co.suyama.menu.deliver.model.db.AnswerImages;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface AnswerImagesMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table answer_images
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	@Delete({ "delete from answer_images", "where id = #{id,jdbcType=INTEGER}" })
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table answer_images
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	@Insert({ "insert into answer_images (id, answer_id, ", "path, created_at, ", "updated_at)",
			"values (#{id,jdbcType=INTEGER}, #{answerId,jdbcType=INTEGER}, ",
			"#{path,jdbcType=VARCHAR}, #{createdAt,jdbcType=TIMESTAMP}, ", "#{updatedAt,jdbcType=TIMESTAMP})" })
	int insert(AnswerImages record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table answer_images
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	@Select({ "select", "id, answer_id, path, created_at, updated_at", "from answer_images",
			"where id = #{id,jdbcType=INTEGER}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "answer_id", property = "answerId", jdbcType = JdbcType.INTEGER),
			@Result(column = "path", property = "path", jdbcType = JdbcType.VARCHAR),
			@Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP) })
	AnswerImages selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table answer_images
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	@Select({ "select", "id, answer_id, path, created_at, updated_at", "from answer_images" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "answer_id", property = "answerId", jdbcType = JdbcType.INTEGER),
			@Result(column = "path", property = "path", jdbcType = JdbcType.VARCHAR),
			@Result(column = "created_at", property = "createdAt", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "updated_at", property = "updatedAt", jdbcType = JdbcType.TIMESTAMP) })
	List<AnswerImages> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table answer_images
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	@Update({ "update answer_images", "set answer_id = #{answerId,jdbcType=INTEGER},",
			"path = #{path,jdbcType=VARCHAR},", "created_at = #{createdAt,jdbcType=TIMESTAMP},",
			"updated_at = #{updatedAt,jdbcType=TIMESTAMP}", "where id = #{id,jdbcType=INTEGER}" })
	int updateByPrimaryKey(AnswerImages record);
}