package jp.co.suyama.menu.deliver.utils;

/**
 * パラメタチェック用クラス
 */
public class ParameterCheckUtils {

    // メールアドレス形式
    private static final String EMAL_FORMAT = "^(?:[\\w!#$%&'*+\\/\\-=?^`{|}~]+(?:\\.[\\w!#$%&'*+\\/\\-=?^`{|}~]+)*)@(?:[a-zA-Z]+[a-zA-Z0-9-]*[a-zA-Z0-9](?:\\.[a-zA-Z]+[a-zA-Z0-9-]*[a-zA-Z0-9])*(?:\\.[a-zA-Z0-9]+))$";

    // 数値0文字以上
    private static final String NUMBER_ZERO_OR_MORE = "[0-9]*";

    // 数値1文字以上
    private static final String NUMBER_ONE_OR_MORE = "[0-9]+";

    // 英大文字1文字以上
    private static final String ALPHA_UPPER_ONE_OR_MORE = "[A-Z]+";

    // 英子文字1文字以上
    private static final String ALPHA_LOWER_ONE_OR_MORE = "[a-z]+";

    // 英大文字小文字数値0文字以上
    private static final String ALPHANUMERIC_UPPER_LOWER_ZERO_OR_MORE = "[A-Za-z0-9]*";

    /**
     * メールアドレス形式チェック
     *
     * @param email メールアドレス
     * @return チェック結果 true: 問題なし, false: 問題あり
     */
    public static boolean checkEmail(String email) {

        if (email == null) {
            return false;
        }

        // 形式チェック
        if (email.matches(EMAL_FORMAT)) {
            return true;
        }

        return false;
    }

    /**
     * パスワード形式チェック
     *
     * @param password パスワード
     * @return チェック結果 true: 問題なし, false: 問題あり
     */
    public static boolean checkPassword(String password) {

        if (password == null) {
            return false;
        }

        // 文字数チェック
        if (password.length() < 8) {
            return false;
        }

        // 形式チェック
        if (password.matches(ALPHANUMERIC_UPPER_LOWER_ZERO_OR_MORE)) {
            // 英大文字1文字以上、英子文字1文字以上、数値1文字以上
            if (password.matches(ALPHA_UPPER_ONE_OR_MORE) && password.matches(ALPHA_LOWER_ONE_OR_MORE)
                    && password.matches(NUMBER_ONE_OR_MORE)) {
                return true;
            }
        }

        return false;
    }
}
