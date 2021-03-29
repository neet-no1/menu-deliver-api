package jp.co.suyama.menu.deliver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.suyama.menu.deliver.exception.MenuDeliverException;
import jp.co.suyama.menu.deliver.mapper.FollowersMapperImpl;
import jp.co.suyama.menu.deliver.mapper.UsersMapperImpl;
import jp.co.suyama.menu.deliver.model.db.Followers;
import jp.co.suyama.menu.deliver.model.db.Users;

@Service
@Transactional(rollbackFor = Exception.class)
public class FollowService {

    // ユーザテーブル
    @Autowired
    private UsersMapperImpl usersMapper;

    // フォロワーテーブル
    @Autowired
    private FollowersMapperImpl followersMapper;

    /**
     * ユーザをフォローする
     *
     * @param email        自身メールアドレス
     * @param targetUserId フォローするユーザのID
     */
    public void followUser(String email, int targetUserId) {

        // ユーザ情報を取得
        Users me = usersMapper.selectEmail(email);

        // 存在していない場合エラー
        if (me == null) {
            throw new MenuDeliverException("ユーザが存在しません。");
        }

        // フォローするユーザを取得する
        Users followUser = usersMapper.selectByPrimaryKey(targetUserId);

        // 存在していない場合エラー
        if (followUser == null) {
            throw new MenuDeliverException("ユーザが存在しません。");
        }

        // フォローテーブルにレコードを追加する
        Followers followers = new Followers();
        followers.setUserId(me.getId());
        followers.setFollowerUserId(followUser.getId());

        followersMapper.registFollower(followers);
    }
}
