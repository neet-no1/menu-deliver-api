package jp.co.suyama.menu.deliver.mapper;

import java.util.List;

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

    /**
     * <pre>
     * メールアドレスを元にお気に入り献立一覧を取得する
     * ユーザが削除されていない
     * 献立が公開されている
     * ものを取得
     * </pre>
     */
    // @formatter:off
    @Select({
          "select"
        , "  m.id,"
        , "  m.user_id,"
        , "  m.title,"
        , "  m.sub_title,"
        , "  m.path,"
        , "  m.category_id,"
        , "  m.opened,"
        , "  m.created_at,"
        , "  m.updated_at"
        , "from menus m"
        , "inner join favorite_menus f"
        , "  on f.menu_id = m.id"
        , "inner join users u"
        , "  on f.user_id = u.id"
        , "where"
        , "  u.deleted = FALSE"
        , "  and m.opened = TRUE"
        , "  and u.email = #{email,jdbcType=VARCHAR}"
        , "order by f.id asc"
        , "limit #{limit,jdbcType=INTEGER}"
        , "offset #{offset,jdbcType=INTEGER}"
    })
    // @formatter:on
    public List<Menus> selectAllFavoriteMenusByEmail(@Param("email") String email, @Param("limit") int limit,
            @Param("offset") int offset);

    /**
     * <pre>
     * メールアドレスを元にお気に入り献立一覧を取得するときの件数
     * ユーザが削除されていない
     * 献立が公開されている
     * ものの件数を取得
     * </pre>
     */
    // @formatter:off
    @Select({
          "select"
        , "  count(m.id)"
        , "from menus m"
        , "inner join favorite_menus f"
        , "  on f.menu_id = m.id"
        , "inner join users u"
        , "  on f.user_id = u.id"
        , "where"
        , "  u.deleted = FALSE"
        , "  and m.opened = TRUE"
        , "  and u.email = #{email,jdbcType=VARCHAR}"
    })
    // @formatter:on
    public int countAllFavoriteMenusByEmail(@Param("email") String email);

    /**
     * <pre>
     * メールアドレスを元に投稿献立一覧を取得する
     * ユーザが削除されていない
     * ものを取得
     * </pre>
     */
    // @formatter:off
    @Select({
          "select"
        , "  m.id,"
        , "  m.user_id,"
        , "  m.title,"
        , "  m.sub_title,"
        , "  m.path,"
        , "  m.category_id,"
        , "  m.opened,"
        , "  m.created_at,"
        , "  m.updated_at"
        , "from menus m"
        , "inner join users u"
        , "  on m.user_id = u.id"
        , "where"
        , "  u.deleted = FALSE"
        , "  and u.email = #{email,jdbcType=VARCHAR}"
        , "order by m.id desc"
        , "limit #{limit,jdbcType=INTEGER}"
        , "offset #{offset,jdbcType=INTEGER}"
    })
    // @formatter:on
    public List<Menus> selectAllByEmail(@Param("email") String email, @Param("limit") int limit,
            @Param("offset") int offset);

    /**
     * <pre>
     * メールアドレスを元に投稿献立一覧を取得するときの件数
     * ユーザが削除されていない
     * ものの件数を取得
     * </pre>
     */
    // @formatter:off
    @Select({
          "select"
        , "  count(m.id)"
        , "from menus m"
        , "inner join users u"
        , "  on m.user_id = u.id"
        , "where"
        , "  u.deleted = FALSE"
        , "  and u.email = #{email,jdbcType=VARCHAR}"
    })
    // @formatter:on
    public int countAllByEmail(@Param("email") String email);
}
