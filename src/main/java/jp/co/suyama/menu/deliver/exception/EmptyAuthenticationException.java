package jp.co.suyama.menu.deliver.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 認証情報が空の場合の例外
 */
public class EmptyAuthenticationException extends AuthenticationException {

    public EmptyAuthenticationException(String msg) {
        super(msg);
    }

    public EmptyAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
