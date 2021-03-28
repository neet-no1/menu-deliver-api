package jp.co.suyama.menu.deliver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import jp.co.suyama.menu.deliver.mapper.auto.MenuCompositionsMapper;
import jp.co.suyama.menu.deliver.model.db.MenuCompositions;

public interface MenuCompositionsMapperImpl extends MenuCompositionsMapper {

    /**
     * 献立IDを元に全ての献立素材を削除する
     */
    // @formatter:off
    @Delete({
          "delete from menu_compositions"
        , "where menu_id = #{menuId,jdbcType=INTEGER}"
    })
    // @formatter:on
    public void deleteAllByMenuId(@Param("menuId") int menuId);

    /**
     * 献立素材を複数登録する
     */
    // @formatter:off
    @Insert({
        "<script>"
        , "insert into menu_compositions"
        , "(menu_id, composition_item_no)"
        , "values"
        , "<foreach item='item' collection='menuCompositionsList' separator=','>"
        , "("
        , "  #{item.menuId,jdbcType=INTEGER},"
        , "  #{item.compositionItemNo,jdbcType=INTEGER}"
        , ")"
        , "</foreach>"
        , "</script>"
    })
    // @formatter:on
    public void registMenuCompositions(@Param("menuCompositionsList") List<MenuCompositions> menuCompositionsList);
}
