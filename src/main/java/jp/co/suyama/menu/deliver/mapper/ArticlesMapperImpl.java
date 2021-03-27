package jp.co.suyama.menu.deliver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.suyama.menu.deliver.mapper.auto.ArticlesMapper;
import jp.co.suyama.menu.deliver.model.db.Articles;

public interface ArticlesMapperImpl extends ArticlesMapper {

    /**
     * <pre>
     * メールアドレスを元にお気に入り記事一覧を取得する
     * ユーザが削除されていない
     * 記事が公開されている
     * ものを取得
     * </pre>
     */
    // @formatter:off
    @Select({
          "select"
        , "  a.id,"
        , "  a.user_id,"
        , "  a.title,"
        , "  a.start_sentence,"
        , "  a.path,"
        , "  a.opened,"
        , "  a.created_at,"
        , "  a.updated_at"
        , "from articles a"
        , "inner join favorite_articles f"
        , "  on f.article_id = a.id"
        , "inner join users u"
        , "  on f.user_id = u.id"
        , "where"
        , "  u.deleted = FALSE"
        , "  and a.opened = TRUE"
        , "  and u.email = #{email,jdbcType=VARCHAR}"
        , "order by f.id asc"
        , "limit #{limit,jdbcType=INTEGER}"
        , "offset #{offset,jdbcType=INTEGER}"
    })
    // @formatter:on
    public List<Articles> selectAllFavoriteArticlesByEmail(@Param("email") String email, @Param("limit") int limit,
            @Param("offset") int offset);

    /**
     * <pre>
     * メールアドレスを元にお気に入り記事一覧を取得するときの件数
     * ユーザが削除されていない
     * 記事が公開されている
     * ものの件数を取得
     * </pre>
     */
    // @formatter:off
    @Select({
          "select"
        , "  count(a.id)"
        , "from articles a"
        , "inner join favorite_articles f"
        , "  on f.article_id = a.id"
        , "inner join users u"
        , "  on f.user_id = u.id"
        , "where"
        , "  u.deleted = FALSE"
        , "  and a.opened = TRUE"
        , "  and u.email = #{email,jdbcType=VARCHAR}"
    })
    // @formatter:on
    public int countAllFavoriteArticlesByEmail(@Param("email") String email);
}
