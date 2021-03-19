package jp.co.suyama.menu.deliver.mapper.auto;

import java.util.List;
import jp.co.suyama.menu.deliver.model.db.ArticlePictures;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface ArticlePicturesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_pictures
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    @Delete({
        "delete from article_pictures",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_pictures
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    @Insert({
        "insert into article_pictures (id, article_id, ",
        "path, order_of, created_at, ",
        "updated_at)",
        "values (#{id,jdbcType=INTEGER}, #{articleId,jdbcType=INTEGER}, ",
        "#{path,jdbcType=VARCHAR}, #{orderOf,jdbcType=INTEGER}, #{createdAt,jdbcType=TIMESTAMP}, ",
        "#{updatedAt,jdbcType=TIMESTAMP})"
    })
    int insert(ArticlePictures record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_pictures
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    @Select({
        "select",
        "id, article_id, path, order_of, created_at, updated_at",
        "from article_pictures",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="article_id", property="articleId", jdbcType=JdbcType.INTEGER),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_of", property="orderOf", jdbcType=JdbcType.INTEGER),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    ArticlePictures selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_pictures
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    @Select({
        "select",
        "id, article_id, path, order_of, created_at, updated_at",
        "from article_pictures"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="article_id", property="articleId", jdbcType=JdbcType.INTEGER),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_of", property="orderOf", jdbcType=JdbcType.INTEGER),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ArticlePictures> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_pictures
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    @Update({
        "update article_pictures",
        "set article_id = #{articleId,jdbcType=INTEGER},",
          "path = #{path,jdbcType=VARCHAR},",
          "order_of = #{orderOf,jdbcType=INTEGER},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP},",
          "updated_at = #{updatedAt,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ArticlePictures record);
}