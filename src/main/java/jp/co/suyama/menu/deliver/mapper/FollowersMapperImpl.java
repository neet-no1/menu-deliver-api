package jp.co.suyama.menu.deliver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.suyama.menu.deliver.mapper.auto.FollowersMapper;
import jp.co.suyama.menu.deliver.model.db.Followers;

public interface FollowersMapperImpl extends FollowersMapper {

    // @formatter:off
    @Insert({
          "insert into followers"
        , "(user_id, follower_user_id, created_at, updated_at)"
        , "values"
        , "("
        , "  #{userId,jdbcType=INTEGER},"
        , "  #{followerUserId,jdbcType=INTEGER},"
        , "  current_timestamp,"
        , "  current_timestamp"
        , ")"
    })
    // @formatter:on
    int registFollower(Followers record);

    /**
     * フォロー一覧を取得する
     */
    // @formatter:off
    @Select({
          "select"
        , "  f.id,"
        , "  f.user_id,"
        , "  f.follower_user_id,"
        , "  f.created_at,"
        , "  f.updated_at"
        , "from followers f"
        , "inner join users me"
        , "  on me.id = f.user_id"
        , "inner join users u"
        , "  on u.id = f.follower_user_id"
        , "where"
        , "  me.email = #{email,jdbcType=VARCHAR}"
        , "  and u.deleted = FALSE"
        , "order by f.updated_at desc"
        , "limit #{limit,jdbcType=INTEGER}"
        , "offset #{offset,jdbcType=INTEGER}"
    })
    // @formatter:on
    List<Followers> selectAllFollowsByEmail(@Param("email") String email, @Param("limit") int limit,
            @Param("offset") int offset);

    /**
     * フォロー一覧の件数を取得する
     */
    // @formatter:off
    @Select({
          "select"
        , "  count(f.id)"
        , "from followers f"
        , "inner join users me"
        , "  on me.id = f.user_id"
        , "inner join users u"
        , "  on u.id = f.follower_user_id"
        , "where"
        , "  me.email = #{email,jdbcType=VARCHAR}"
        , "  and u.deleted = FALSE"
    })
    // @formatter:on
    int countAllFollowsByEmail(@Param("email") String email);

    /**
     * フォロワー一覧を取得する
     */
    // @formatter:off
    @Select({
          "select"
        , "  f.id,"
        , "  f.user_id,"
        , "  f.follower_user_id,"
        , "  f.created_at,"
        , "  f.updated_at"
        , "from followers f"
        , "inner join users me"
        , "  on me.id = f.follower_user_id"
        , "inner join users u"
        , "  on u.id = f.user_id"
        , "where"
        , "  me.email = #{email,jdbcType=VARCHAR}"
        , "  and u.deleted = FALSE"
        , "order by f.updated_at desc"
        , "limit #{limit,jdbcType=INTEGER}"
        , "offset #{offset,jdbcType=INTEGER}"
    })
    // @formatter:on
    List<Followers> selectAllFollowersByEmail(@Param("email") String email, @Param("limit") int limit,
            @Param("offset") int offset);

    /**
     * フォロワー一覧の件数を取得する
     */
    // @formatter:off
    @Select({
          "select"
        , "  count(f.id)"
        , "from followers f"
        , "inner join users me"
        , "  on me.id = f.follower_user_id"
        , "inner join users u"
        , "  on u.id = f.user_id"
        , "where"
        , "  me.email = #{email,jdbcType=VARCHAR}"
        , "  and u.deleted = FALSE"
    })
    // @formatter:on
    int countAllFollowersByEmail(@Param("email") String email);
}
