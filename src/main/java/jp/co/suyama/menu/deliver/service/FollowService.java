package jp.co.suyama.menu.deliver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.suyama.menu.deliver.exception.MenuDeliverException;
import jp.co.suyama.menu.deliver.mapper.FollowersMapperImpl;
import jp.co.suyama.menu.deliver.mapper.UsersMapperImpl;
import jp.co.suyama.menu.deliver.model.FollowersAndPage;
import jp.co.suyama.menu.deliver.model.auto.FollowUserData;
import jp.co.suyama.menu.deliver.model.auto.PageNation;
import jp.co.suyama.menu.deliver.model.db.Followers;
import jp.co.suyama.menu.deliver.model.db.Users;
import jp.co.suyama.menu.deliver.utils.PageNationUtils;
import jp.co.suyama.menu.deliver.utils.PathUtils;

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

    /**
     * フォロー一覧を取得する
     *
     * @param email メールアドレス
     * @param page  取得ページ番号
     * @return フォロー一覧
     */
    public FollowersAndPage getFollows(String email, int page) {

        // レスポンス
        FollowersAndPage result = new FollowersAndPage();

        // 取得件数
        int limit = 16;

        // 全体の件数を取得する
        int count = followersMapper.countAllFollowsByEmail(email);

        // ページネーションを取得
        PageNation pageNation = PageNationUtils.createPageNation(page, count, limit);

        // 取得ページからoffsetを計算する
        int offset = (pageNation.getCurrentPage() - 1) * limit;

        // メールアドレスからフォロー一覧を取得
        List<Followers> followsList = followersMapper.selectAllFollowsByEmail(email, limit, offset);

        // フォローユーザIDの一覧を作成
        List<Integer> followUserIdList = followsList.stream().map(f -> f.getFollowerUserId())
                .collect(Collectors.toList());

        // 詰め替える
        List<FollowUserData> followDataList = convertFollowUserData(followUserIdList);

        // レスポンスに値を設定する
        result.setFollowerList(followDataList);
        result.setPage(pageNation);

        return result;
    }

    /**
     * フォロワー一覧を取得する
     *
     * @param email メールアドレス
     * @param page  取得ページ番号
     * @return フォロワー一覧
     */
    public FollowersAndPage getFollowers(String email, int page) {

        // レスポンス
        FollowersAndPage result = new FollowersAndPage();

        // 取得件数
        int limit = 16;

        // 全体の件数を取得する
        int count = followersMapper.countAllFollowersByEmail(email);

        // ページネーションを取得
        PageNation pageNation = PageNationUtils.createPageNation(page, count, limit);

        // 取得ページからoffsetを計算する
        int offset = (pageNation.getCurrentPage() - 1) * limit;

        // メールアドレスからフォロワー一覧を取得
        List<Followers> followersList = followersMapper.selectAllFollowersByEmail(email, limit, offset);

        // フォロワーユーザIDの一覧を作成
        List<Integer> followerUserIdList = followersList.stream().map(f -> f.getUserId()).collect(Collectors.toList());

        // 詰め替える
        List<FollowUserData> followerDataList = convertFollowUserData(followerUserIdList);

        // レスポンスに値を設定する
        result.setFollowerList(followerDataList);
        result.setPage(pageNation);

        return result;
    }

    private List<FollowUserData> convertFollowUserData(List<Integer> followersList) {

        // レスポンス
        FollowUserData data = null;
        List<FollowUserData> followDataList = new ArrayList<>();

        // 画像と内容を取得する
        for (int menus : followersList) {
            // ユーザ情報を取得する
            Users user = usersMapper.selectByPrimaryKey(menus);

            // データを詰め替える
            data = new FollowUserData();
            data.setId(user.getId());
            data.setName(user.getName());
            data.setImgPath(PathUtils.getUserIconPath(user.getIcon()));

            // フォローデータを追加
            followDataList.add(data);
        }

        return followDataList;
    }
}
