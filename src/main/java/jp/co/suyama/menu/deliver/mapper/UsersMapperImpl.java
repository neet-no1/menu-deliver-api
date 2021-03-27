package jp.co.suyama.menu.deliver.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.suyama.menu.deliver.mapper.auto.UsersMapper;
import jp.co.suyama.menu.deliver.model.db.Users;

public interface UsersMapperImpl extends UsersMapper {

    // @formatter:off
    @Select({
          "select"
        , "  *"
        , "from users"
        , "where email = #{email}"
    })
    // @formatter:on
    Users selectEmail(@Param("email") String email);

    // @formatter:off
    @Select({
          "insert into users"
        , "(email, password, role, available, once_password, expires, deleted, created_at, updated_at)"
        , "values"
        , "("
        , "  #{email,jdbcType=VARCHAR},"
        , "  #{password,jdbcType=VARCHAR},"
        , "  #{role,jdbcType=VARCHAR},"
        , "  #{available,jdbcType=BIT},"
        , "  #{oncePassword,jdbcType=VARCHAR},"
        , "  #{expires,jdbcType=TIMESTAMP},"
        , "  #{deleted,jdbcType=BIT},"
        , "  current_timestamp,"
        , "  current_timestamp"
        , ")"
        , "returning id"
    })
    // @formatter:on
    int registUser(Users record);

    // @formatter:off
    @Select({
        "select"
      , "  *"
      , "from users"
      , "where once_password = #{otp}"
    })
    // @formatter:on
    Users selectByOncePassword(@Param("otp") String otp);

    // @formatter:off
    @Update({
        "update users"
      , "set"
      , "  email = #{email,jdbcType=VARCHAR},"
      , "  password = #{password,jdbcType=VARCHAR},"
      , "  available = #{available,jdbcType=BIT},"
      , "  once_password = #{oncePassword,jdbcType=VARCHAR},"
      , "  expires = #{expires,jdbcType=TIMESTAMP},"
      , "  deleted = #{deleted,jdbcType=BIT},"
      , "  updated_at = current_timestamp"
      , "where id = #{id,jdbcType=INTEGER}"
    })
    // @formatter:on
    int updateUser(Users record);

}
