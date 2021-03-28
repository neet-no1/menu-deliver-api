package jp.co.suyama.menu.deliver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import jp.co.suyama.menu.deliver.mapper.auto.NoticesMapper;
import jp.co.suyama.menu.deliver.model.db.Notices;

public interface NoticesMapperImpl extends NoticesMapper {

    /**
     * 期限内のお知らせ情報を取得する
     */
    // @formatter:off
    @Select({
        "<script>"
        , "select"
        , "  *"
        , "from notices"
        , "where"
        , "<![CDATA["
        , "  expires >= current_timestamp"
        , "]]>"
        , "</script>"
    })
    // @formatter:on
    public List<Notices> selectAllAvailable();
}
