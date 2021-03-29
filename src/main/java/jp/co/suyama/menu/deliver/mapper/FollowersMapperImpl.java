package jp.co.suyama.menu.deliver.mapper;

import org.apache.ibatis.annotations.Insert;

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
}
