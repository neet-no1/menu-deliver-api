package jp.co.suyama.menu.deliver.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.suyama.menu.deliver.mapper.auto.MenusMapper;
import jp.co.suyama.menu.deliver.model.db.Menus;

public interface MenusMapperImpl extends MenusMapper {

    /**
     * 献立を登録し、IDを取得する
     */
    // @formatter:off
    @Select({
          "insert into menus"
        , "(user_id, title, sub_title, category_id, opened, created_at, updated_at)"
        , "values"
        , "("
        , "  #{userId,jdbcType=INTEGER},"
        , "  #{title,jdbcType=VARCHAR},"
        , "  #{subTitle,jdbcType=VARCHAR},"
        , "  #{categoryId,jdbcType=INTEGER},"
        , "  #{opened,jdbcType=BIT},"
        , "  current_timestamp,"
        , "  current_timestamp"
        , ")"
        , "returning id"
    })
    // @formatter:on
    public int registMenu(Menus record);

    /**
     * 献立を更新する
     */
    // @formatter:off
    @Update({
          "update menus"
        , "set"
        , "user_id = #{userId,jdbcType=INTEGER},"
        , "title = #{title,jdbcType=VARCHAR},"
        , "sub_title = #{subTitle,jdbcType=VARCHAR},"
        , "category_id = #{categoryId,jdbcType=INTEGER},"
        , "path = #{path,jdbcType=VARCHAR},"
        , "opened = #{opened,jdbcType=BIT},"
        , "updated_at = current_timestamp"
        , "where id = #{id,jdbcType=INTEGER}"
    })
    // @formatter:on
    public void updateMenu(Menus record);

    /**
     * 献立のサムネイル画像のパスを更新する
     */
    // @formatter:off
    @Update({
          "update menus"
        , "set"
        , "path = #{path,jdbcType=VARCHAR}"
        , "where id = #{id,jdbcType=INTEGER}"
    })
    // @formatter:on
    public void updateMenusPath(@Param("id") int id, @Param("path") String path);
}
