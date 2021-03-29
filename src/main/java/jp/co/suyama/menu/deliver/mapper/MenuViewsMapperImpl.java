package jp.co.suyama.menu.deliver.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import jp.co.suyama.menu.deliver.mapper.auto.MenuViewsMapper;

public interface MenuViewsMapperImpl extends MenuViewsMapper {

    /**
     * 献立閲覧数を追加する
     */
    // @formatter:off
    @Insert({
          "insert into menu_views"
        , "(menu_id, date, count)"
        , "values"
        , "("
        , "  #{menuId,jdbcType=INTEGER},"
        , "  current_timestamp,"
        , "  1"
        , ")"
    })
    // @formatter:on
    int registMenuViews(@Param("menuId") int menuId);

    /**
     * 献立IDを元に全ての献立閲覧数を削除する
     */
    // @formatter:off
    @Delete({
          "delete from menu_views"
        , "where menu_id = #{menuId,jdbcType=INTEGER}"
    })
    // @formatter:on
    public void deleteAllByMenuId(@Param("menuId") int menuId);
}
