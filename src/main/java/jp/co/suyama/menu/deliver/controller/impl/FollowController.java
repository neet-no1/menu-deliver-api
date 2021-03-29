package jp.co.suyama.menu.deliver.controller.impl;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import jp.co.suyama.menu.deliver.common.MenuDeliverConstants;
import jp.co.suyama.menu.deliver.common.MenuDeliverStatus;
import jp.co.suyama.menu.deliver.controller.interfaces.FollowApi;
import jp.co.suyama.menu.deliver.model.FollowersAndPage;
import jp.co.suyama.menu.deliver.model.auto.BasicResponse;
import jp.co.suyama.menu.deliver.model.auto.ErrorInfo;
import jp.co.suyama.menu.deliver.model.auto.FollowUserParam;
import jp.co.suyama.menu.deliver.model.auto.FollowersResponse;
import jp.co.suyama.menu.deliver.service.FollowService;

@RestController
public class FollowController implements FollowApi {

    // フォローサービス
    @Autowired
    private FollowService followService;

    @Override
    public ResponseEntity<BasicResponse> followUser(@Valid FollowUserParam followUserParam) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // レスポンス作成
        BasicResponse response = new BasicResponse();

        // ログインチェック
        if (MenuDeliverConstants.UNKNOWN_USER_NAME.equals(auth.getPrincipal().toString())) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("ログインされていません。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // フォローユーザID存在チェック
        if (followUserParam.getUserId() == null) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("フォローするユーザIDが空です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // フォローする
        followService.followUser(auth.getPrincipal().toString(), followUserParam.getUserId().intValue());

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(MenuDeliverStatus.SUCCESS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FollowersResponse> getFollowers(@Valid Integer followPage, @Valid Integer followerPage) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // レスポンス作成
        FollowersResponse response = new FollowersResponse();

        // ログインチェック
        if (MenuDeliverConstants.UNKNOWN_USER_NAME.equals(auth.getPrincipal().toString())) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("ログインされていません。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // フォロー取得ページ番号存在チェック
        if (followPage == null) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("フォロー取得ページ番号が空です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // フォロワー取得ページ番号存在チェック
        if (followerPage == null) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("フォロワー取得ページ番号が空です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        Map<String, Object> followItem = new HashMap<>();
        // フォロー一覧を取得する
        FollowersAndPage follows = followService.getFollows(auth.getPrincipal().toString(), followPage.intValue());
        followItem.put("follows", follows.getFollowerList());
        followItem.put("followPage", follows.getPage());

        // フォロワー一覧を取得する
        FollowersAndPage followers = followService.getFollowers(auth.getPrincipal().toString(),
                followerPage.intValue());
        followItem.put("followers", followers.getFollowerList());
        followItem.put("followerPage", followers.getPage());

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(followItem);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
