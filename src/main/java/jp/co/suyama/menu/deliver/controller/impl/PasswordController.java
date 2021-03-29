package jp.co.suyama.menu.deliver.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jp.co.suyama.menu.deliver.common.MenuDeliverStatus;
import jp.co.suyama.menu.deliver.controller.interfaces.PasswordApi;
import jp.co.suyama.menu.deliver.model.auto.BasicResponse;
import jp.co.suyama.menu.deliver.model.auto.ErrorInfo;
import jp.co.suyama.menu.deliver.model.auto.PasswordResetParam;
import jp.co.suyama.menu.deliver.service.AccountService;
import jp.co.suyama.menu.deliver.utils.ParameterCheckUtils;

@RestController
public class PasswordController implements PasswordApi {

    // アカウントサービス
    @Autowired
    private AccountService accountService;

    @Override
    public ResponseEntity<BasicResponse> resetPassword(@Valid PasswordResetParam passwordResetParam) {

        // レスポンス作成
        BasicResponse response = new BasicResponse();

        // メールアドレスの形式チェック
        if (!ParameterCheckUtils.checkEmail(passwordResetParam.getEmail())) {
            // チェックで失敗した場合
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("メールアドレスの形式が不正です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // メールアドレス有効性確認
        accountService.resetPassword(passwordResetParam.getEmail());

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(MenuDeliverStatus.SUCCESS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
