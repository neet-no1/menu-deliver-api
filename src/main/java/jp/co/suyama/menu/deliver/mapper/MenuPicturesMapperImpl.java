package jp.co.suyama.menu.deliver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.suyama.menu.deliver.mapper.auto.MenuPicturesMapper;
import jp.co.suyama.menu.deliver.model.db.MenuPictures;

public interface MenuPicturesMapperImpl extends MenuPicturesMapper {

    /**
     * 献立画像を登録する
     */
    // @formatter:off
    @Insert({
          "insert into menu_pictures"
        , "(menu_id, path, order_of, created_at, updated_at)"
        , "values"
        , "("
        , "  #{menuId,jdbcType=INTEGER},"
        , "  #{path,jdbcType=VARCHAR},"
        , "  #{orderOf,jdbcType=INTEGER},"
        , "  current_timestamp,"
        , "  current_timestamp"
        , ")"
    })
    // @formatter:on
    public void registMenuPicture(MenuPictures record);

    /**
     * 献立IDを元に全ての献立画像を削除する
     */
    // @formatter:off
    @Delete({
          "delete from menu_pictures"
        , "where menu_id = #{menuId,jdbcType=INTEGER}"
    })
    // @formatter:on
    public void deleteAllByMenuId(@Param("menuId") int menuId);

    /**
     * 献立IDを元に全ての献立画像を取得する
     */
    // @formatter:off
    @Select({
          "select"
        , "  *"
        , "from menu_pictures"
        , "where menu_id = #{menuId,jdbcType=INTEGER}"
    })
    // @formatter:on
    public List<MenuPictures> selectAllByMenuId(@Param("menuId") int menuId);

}
