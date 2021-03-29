package jp.co.suyama.menu.deliver.controller.impl;

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
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

}
