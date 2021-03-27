package jp.co.suyama.menu.deliver.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.suyama.menu.deliver.mapper.auto.MenuDetailsMapper;
import jp.co.suyama.menu.deliver.model.db.MenuDetails;

public interface MenuDetailsMapperImpl extends MenuDetailsMapper {

    /**
     * 献立詳細を登録する
     */
    // @formatter:off
    @Insert({
          "insert info menu_details"
        , "(menu_id, path, description, created_at, updated_at)"
        , "values"
        , "("
        , "  #{menuId,jdbcType=INTEGER},"
        , "  #{path,jdbcType=VARCHAR},"
        , "  #{description,jdbcType=VARCHAR},"
        , "  current_timestamp,"
        , "  current_timestamp"
        , ")"
    })
    // @formatter:on
    public void registMenuDetails(MenuDetails record);

    /**
     * <pre>
     * 献立IDを元に献立詳細を取得する
     * 献立詳細IDと献立IDは 1:1 であるため、必ず1つだけ取得される
     * </pre>
     */
    // @formatter:off
    @Select({
          "select"
        , "  *"
        , "from menu_details"
        , "where"
        , "  menu_id = #{menuId,jdbcType=INTEGER},"
    })
    // @formatter:on
    public MenuDetails selectByMenusId(@Param("menuId") int menuId);

    /**
     * 献立詳細を更新する
     */
    // @formatter:off
    @Update({
          "update menu_details"
        , "set"
        , "menu_id = #{menuId,jdbcType=INTEGER},"
        , "path = #{path,jdbcType=VARCHAR},"
        , "updated_at = current_timestamp"
        , "where id = #{id,jdbcType=INTEGER}"
    })
    // @formatter:on
    public void updateMenuDetails(MenuDetails record);
}
