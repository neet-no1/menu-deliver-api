package jp.co.suyama.menu.deliver.controller.impl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jp.co.suyama.menu.deliver.common.MenuDeliverConstants;
import jp.co.suyama.menu.deliver.common.MenuDeliverStatus;
import jp.co.suyama.menu.deliver.controller.interfaces.AccountApi;
import jp.co.suyama.menu.deliver.model.auto.AccountAuthResponse;
import jp.co.suyama.menu.deliver.model.auto.AccountData;
import jp.co.suyama.menu.deliver.model.auto.AccountResponse;
import jp.co.suyama.menu.deliver.model.auto.ArticlesResponse;
import jp.co.suyama.menu.deliver.model.auto.BasicResponse;
import jp.co.suyama.menu.deliver.model.auto.ErrorInfo;
import jp.co.suyama.menu.deliver.model.auto.MenusAndArticlesResponse;
import jp.co.suyama.menu.deliver.model.auto.MenusResponse;
import jp.co.suyama.menu.deliver.model.auto.RegistAccountParam;
import jp.co.suyama.menu.deliver.model.auto.UpdatePasswordParam;
import jp.co.suyama.menu.deliver.service.AccountService;
import jp.co.suyama.menu.deliver.utils.ParameterCheckUtils;

@RestController
public class AccountController implements AccountApi {

    /**
     * アカウントサービス
     */
    @Autowired
    AccountService accountService;

    /**
     * パスワードエンコーダー
     */
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<BasicResponse> emailConfirm(@NotNull @Valid String oneTimePassword) {

        // レスポンス作成
        BasicResponse response = new BasicResponse();

        // ワンタイムパスワード存在チェック
        if (oneTimePassword == null) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("ワンタイムパスワードが空です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // メールアドレス有効性確認
        accountService.checkEmailAvailable(oneTimePassword);

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(MenuDeliverStatus.SUCCESS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountAuthResponse> getAccountAuth() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // レスポンス作成
        AccountAuthResponse response = new AccountAuthResponse();

        // ログイン状態を設定
        response.setInfo(!MenuDeliverConstants.UNKNOWN_USER_NAME.equals(auth.getPrincipal()));

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountResponse> getAccountInfo() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // レスポンス作成
        AccountResponse response = new AccountResponse();

        // ログインチェック
        if (MenuDeliverConstants.UNKNOWN_USER_NAME.equals(auth.getPrincipal().toString())) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("ログインされていません。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // アカウント情報取得
        AccountData data = accountService.getAccountInfo(auth.getPrincipal().toString());

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MenusAndArticlesResponse> getFavoriteItems(@Valid Integer menuPage,
            @Valid Integer articlePage) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public ResponseEntity<ArticlesResponse> getPostedArticles(@Valid Integer page) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public ResponseEntity<MenusResponse> getPostedMenus(@Valid Integer page) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public ResponseEntity<BasicResponse> registAccount(@Valid RegistAccountParam registAccountParam) {

        // レスポンス作成
        BasicResponse response = new BasicResponse();

        // メールアドレスの形式チェック
        if (!ParameterCheckUtils.checkEmail(registAccountParam.getEmail())) {
            // チェックで失敗した場合
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("メールアドレスの形式が不正です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // パスワードが一致するかチェック
        if (!ParameterCheckUtils.checkPassword(registAccountParam.getPassword())) {
            // チェックで失敗した場合
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("パスワードの形式が不正です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // パスワードが一致するかチェック
        if (!registAccountParam.getPassword().equals(registAccountParam.getPasswordConfirm())) {
            // チェックで失敗した場合
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("パスワードが一致しません。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // パスワードをエンコードする
        String password = passwordEncoder.encode(registAccountParam.getPassword());

        // アカウント登録処理
        accountService.registAccount(registAccountParam.getEmail(), password);

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(MenuDeliverStatus.SUCCESS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BasicResponse> updateAccountInfo(String name, String email, @Valid MultipartFile icon) {

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

        // ニックネーム存在チェック
        if (StringUtils.isEmpty(name)) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("ニックネームが空です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // メールアドレス存在チェック
        if (StringUtils.isEmpty(email)) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("メールアドレスが空です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // メールアドレス形式チェック
        if (!ParameterCheckUtils.checkEmail(email)) {
            // チェックで失敗した場合
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("メールアドレスの形式が不正です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // アカウント情報更新
        accountService.updateAccountInfo(name, email, icon, auth.getPrincipal().toString());

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(MenuDeliverStatus.SUCCESS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BasicResponse> updatePassword(@Valid UpdatePasswordParam updatePasswordParam) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

}
