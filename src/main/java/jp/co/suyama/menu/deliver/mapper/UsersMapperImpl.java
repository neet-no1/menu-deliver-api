package jp.co.suyama.menu.deliver.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.suyama.menu.deliver.mapper.auto.UsersMapper;
import jp.co.suyama.menu.deliver.model.db.Users;

public interface UsersMapperImpl extends UsersMapper {

    // @formatter:off
    @Select({
        "select",
        "*",
        "from users",
        "where email = #{email}" })
    // @formatter:on
    Users selectEmail(@Param("email") String email);
}
