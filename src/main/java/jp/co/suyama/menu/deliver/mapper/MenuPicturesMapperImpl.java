package jp.co.suyama.menu.deliver.mapper;

import org.apache.ibatis.annotations.Insert;

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
}
