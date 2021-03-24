package jp.co.suyama.menu.deliver.mapper.auto;

import java.util.List;
import jp.co.suyama.menu.deliver.model.db.Compositions;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface CompositionsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table compositions
     *
     * @mbg.generated Wed Mar 24 23:59:44 JST 2021
     */
    @Delete({
        "delete from compositions",
        "where item_no = #{itemNo,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer itemNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table compositions
     *
     * @mbg.generated Wed Mar 24 23:59:44 JST 2021
     */
    @Insert({
        "insert into compositions (item_no, item_group, ",
        "item_index, name, ",
        "energy, protein, lipid, ",
        "carbohydrate, calcium, ",
        "iron, cholesterol, ",
        "dietary_fibers, salt_equivalents, ",
        "refuse)",
        "values (#{itemNo,jdbcType=INTEGER}, #{itemGroup,jdbcType=INTEGER}, ",
        "#{itemIndex,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{energy,jdbcType=DOUBLE}, #{protein,jdbcType=DOUBLE}, #{lipid,jdbcType=DOUBLE}, ",
        "#{carbohydrate,jdbcType=DOUBLE}, #{calcium,jdbcType=DOUBLE}, ",
        "#{iron,jdbcType=DOUBLE}, #{cholesterol,jdbcType=DOUBLE}, ",
        "#{dietaryFibers,jdbcType=DOUBLE}, #{saltEquivalents,jdbcType=DOUBLE}, ",
        "#{refuse,jdbcType=DOUBLE})"
    })
    int insert(Compositions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table compositions
     *
     * @mbg.generated Wed Mar 24 23:59:44 JST 2021
     */
    @Select({
        "select",
        "item_no, item_group, item_index, name, energy, protein, lipid, carbohydrate, ",
        "calcium, iron, cholesterol, dietary_fibers, salt_equivalents, refuse",
        "from compositions",
        "where item_no = #{itemNo,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="item_no", property="itemNo", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="item_group", property="itemGroup", jdbcType=JdbcType.INTEGER),
        @Result(column="item_index", property="itemIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="energy", property="energy", jdbcType=JdbcType.DOUBLE),
        @Result(column="protein", property="protein", jdbcType=JdbcType.DOUBLE),
        @Result(column="lipid", property="lipid", jdbcType=JdbcType.DOUBLE),
        @Result(column="carbohydrate", property="carbohydrate", jdbcType=JdbcType.DOUBLE),
        @Result(column="calcium", property="calcium", jdbcType=JdbcType.DOUBLE),
        @Result(column="iron", property="iron", jdbcType=JdbcType.DOUBLE),
        @Result(column="cholesterol", property="cholesterol", jdbcType=JdbcType.DOUBLE),
        @Result(column="dietary_fibers", property="dietaryFibers", jdbcType=JdbcType.DOUBLE),
        @Result(column="salt_equivalents", property="saltEquivalents", jdbcType=JdbcType.DOUBLE),
        @Result(column="refuse", property="refuse", jdbcType=JdbcType.DOUBLE)
    })
    Compositions selectByPrimaryKey(Integer itemNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table compositions
     *
     * @mbg.generated Wed Mar 24 23:59:44 JST 2021
     */
    @Select({
        "select",
        "item_no, item_group, item_index, name, energy, protein, lipid, carbohydrate, ",
        "calcium, iron, cholesterol, dietary_fibers, salt_equivalents, refuse",
        "from compositions"
    })
    @Results({
        @Result(column="item_no", property="itemNo", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="item_group", property="itemGroup", jdbcType=JdbcType.INTEGER),
        @Result(column="item_index", property="itemIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="energy", property="energy", jdbcType=JdbcType.DOUBLE),
        @Result(column="protein", property="protein", jdbcType=JdbcType.DOUBLE),
        @Result(column="lipid", property="lipid", jdbcType=JdbcType.DOUBLE),
        @Result(column="carbohydrate", property="carbohydrate", jdbcType=JdbcType.DOUBLE),
        @Result(column="calcium", property="calcium", jdbcType=JdbcType.DOUBLE),
        @Result(column="iron", property="iron", jdbcType=JdbcType.DOUBLE),
        @Result(column="cholesterol", property="cholesterol", jdbcType=JdbcType.DOUBLE),
        @Result(column="dietary_fibers", property="dietaryFibers", jdbcType=JdbcType.DOUBLE),
        @Result(column="salt_equivalents", property="saltEquivalents", jdbcType=JdbcType.DOUBLE),
        @Result(column="refuse", property="refuse", jdbcType=JdbcType.DOUBLE)
    })
    List<Compositions> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table compositions
     *
     * @mbg.generated Wed Mar 24 23:59:44 JST 2021
     */
    @Update({
        "update compositions",
        "set item_group = #{itemGroup,jdbcType=INTEGER},",
          "item_index = #{itemIndex,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "energy = #{energy,jdbcType=DOUBLE},",
          "protein = #{protein,jdbcType=DOUBLE},",
          "lipid = #{lipid,jdbcType=DOUBLE},",
          "carbohydrate = #{carbohydrate,jdbcType=DOUBLE},",
          "calcium = #{calcium,jdbcType=DOUBLE},",
          "iron = #{iron,jdbcType=DOUBLE},",
          "cholesterol = #{cholesterol,jdbcType=DOUBLE},",
          "dietary_fibers = #{dietaryFibers,jdbcType=DOUBLE},",
          "salt_equivalents = #{saltEquivalents,jdbcType=DOUBLE},",
          "refuse = #{refuse,jdbcType=DOUBLE}",
        "where item_no = #{itemNo,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Compositions record);
}