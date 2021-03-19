package jp.co.suyama.menu.deliver.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.suyama.menu.deliver.common.MenuDeliverConstants;
import jp.co.suyama.menu.deliver.common.MenuDeliverStatus;
import jp.co.suyama.menu.deliver.model.auto.BasicResponse;

public class LogoutHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
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

}
