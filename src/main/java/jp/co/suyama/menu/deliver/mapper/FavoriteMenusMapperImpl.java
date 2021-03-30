package jp.co.suyama.menu.deliver.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.suyama.menu.deliver.mapper.auto.FavoriteMenusMapper;
import jp.co.suyama.menu.deliver.model.db.FavoriteMenus;

public interface FavoriteMenusMapperImpl extends FavoriteMenusMapper {

    // @formatter:off
    @Delete({
        "delete from favorite_menus"
      , "where menu_id = #{menuId,jdbcType=INTEGER}"
    })
    // @formatter:on
    int deleteAllByMenuId(@Param("menuId") int menuId);

    // @formatter:off
    @Select({
          "select"
        , "  *"
        , "from favorite_menus"
        , "where"
        , "  user_id = #{userId,jdbcType=INTEGER}"
        , "  and menu_id = #{menuId,jdbcType=INTEGER}"
    })
    // @formatter:on
    FavoriteMenus selectByUserIdMenuId(@Param("userId") int user_id, @Param("menuId") int menuId);

    // @formatter:off
    @Insert({
          "insert into favorite_menus"
        , "(user_id, menu_id, created_at, updated_at)"
        , "values"
        , "("
        , "  #{userId,jdbcType=INTEGER},"
        , "  #{menuId,jdbcType=INTEGER},"
        , "  current_timestamp,"
        , "  current_timestamp"
        , ")"
    })
    // @formatter:on
    int registFavoriteMenus(FavoriteMenus record);

    // @formatter:off
    @Delete({
        "delete from favorite_menus"
      , "where"
      , "  user_id = #{userId,jdbcType=INTEGER}"
      , "  and menu_id = #{menuId,jdbcType=INTEGER}"
    })
    // @formatter:on
    int deleteByUserIdMenuId(@Param("userId") int user_id, @Param("menuId") int menuId);
}
