package jp.co.suyama.menu.deliver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jp.co.suyama.menu.deliver.common.MenuDeliverStatus;
import jp.co.suyama.menu.deliver.exception.MenuDeliverException;
import jp.co.suyama.menu.deliver.model.auto.BasicResponse;
import jp.co.suyama.menu.deliver.model.auto.ErrorInfo;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * <pre>
     * Springのエラーをハンドリングする。
     * エラー毎に分岐が可能。
     * </pre>
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        log.error("エラーが発生しました。", ex);

        // レスポンスを設定
        BasicResponse result = new BasicResponse();
        result.setCode(MenuDeliverStatus.FAILED);

        ErrorInfo errorInfo = new ErrorInfo();

        if (HttpStatus.NOT_FOUND == status) {
            errorInfo.setErrorMessage("ページが見つかりません。");
        } else {
            errorInfo.setErrorMessage("内部エラーが発生しました。");
        }

        result.setErrorInfo(errorInfo);

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    /**
     * MenuDeliverExceptionをハンドリングする
     */
    @ExceptionHandler(MenuDeliverException.class)
    public ResponseEntity<Object> handleMenuDeliverException(MenuDeliverException ex, WebRequest request) {
        log.warn("エラーが発生しました。", ex);

        // レスポンスを設定
        BasicResponse result = new BasicResponse();
        result.setCode(MenuDeliverStatus.FAILED);

        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorMessage(ex.getMessage());

        result.setErrorInfo(errorInfo);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
