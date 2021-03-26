package jp.co.suyama.menu.deliver.exception;

/**
 * menu-deliverにおける包括的な例外
 */
public class MenuDeliverException extends RuntimeException {

    public MenuDeliverException(String msg) {
        super(msg);
    }

    public MenuDeliverException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
