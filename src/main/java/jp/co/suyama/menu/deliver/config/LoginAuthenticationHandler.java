package jp.co.suyama.menu.deliver.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.suyama.menu.deliver.common.MenuDeliverConstants;
import jp.co.suyama.menu.deliver.common.MenuDeliverStatus;
import jp.co.suyama.menu.deliver.model.auto.BasicResponse;
import jp.co.suyama.menu.deliver.model.auto.ErrorInfo;

public class LoginAuthenticationHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    private Logger log = LoggerFactory.getLogger(LoginAuthenticationHandler.class);

    /**
     * <pre>
     * 認証成功時の処理
     * JSON文字列を返却するようレスポンスに設定を行う
     * </pre>
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // レスポンスを設定
        BasicResponse result = new BasicResponse();
        result.setCode(MenuDeliverStatus.SUCCESS);
        result.setInfo(MenuDeliverStatus.SUCCESS);

        // ContentTypeを設定
        response.setContentType(MenuDeliverConstants.APPLICATION_JSON_UTF8);
        // レスポンスを書き込み
        response.getWriter().write(new ObjectMapper().writeValueAsString(result));
        // HTTPStatusを設定
        response.setStatus(HttpStatus.OK.value());
    }

    /**
     * <pre>
     * 認証失敗時の処理
     * JSON文字列を返却するようレスポンスに設定を行う
     * </pre>
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        // エラー内容を表示
        log.info("認証エラー発生。message: " + exception.getMessage());

        // レスポンスを設定
        BasicResponse result = new BasicResponse();
        result.setCode(MenuDeliverStatus.FAILED);
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorMessage("認証が失敗しました。");
        result.setErrorInfo(errorInfo);

        // ContentTypeを設定
        response.setContentType(MenuDeliverConstants.APPLICATION_JSON_UTF8);
        // レスポンスを書き込み
        response.getWriter().write(new ObjectMapper().writeValueAsString(result));
        // HTTPStatusを設定
        response.setStatus(HttpStatus.OK.value());
    }
}
