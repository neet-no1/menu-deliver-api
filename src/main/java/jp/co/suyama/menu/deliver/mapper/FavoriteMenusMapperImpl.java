package jp.co.suyama.menu.deliver.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.suyama.menu.deliver.mapper.auto.FavoriteMenusMapper;
import jp.co.suyama.menu.deliver.model.db.FavoriteMenus;

public interface FavoriteMenusMapperImpl extends FavoriteMenusMapper {

    // @formatter:off
    @Select({
        "delete from favorite_menus"
      , "where menu_id = #{menuId,jdbcType=INTEGER}"
    })
    // @formatter:on
    FavoriteMenus deleteAllByMenuId(@Param("menuId") int menuId);
}
