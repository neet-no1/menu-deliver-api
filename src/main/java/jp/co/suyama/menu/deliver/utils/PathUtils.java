package jp.co.suyama.menu.deliver.utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;

import jp.co.suyama.menu.deliver.common.MenuDeliverConstants;
import jp.co.suyama.menu.deliver.exception.MenuDeliverException;

public class PathUtils {

    // 暗号化＆復号化で使用する鍵
    private static final String ENCRYPT_KEY = "d114240101119629";
    // 初期ベクトル
    private static final String INIT_VECTOR = "d118811168398147";

    private static final IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes());
    private static final SecretKeySpec key = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");

    /**
     * 献立詳細のパスを生成する
     */
    public static String createMenuDetailsPath(String email, int menusId) {
        return menusId + "_" + encrypt(email);
    }

    /**
     * 献立画像のパスを生成する
     */
    public static String createMenuImagePath(int menusId, String fileName) {
        return menusId + "_" + encrypt(fileName);
    }

    /**
     * ユーザアイコンのパスを生成する
     */
    public static String createUserIconPath(int userId, String email) {
        return userId + "_" + encrypt(email);
    }

    /**
     * 記事画像のパスを生成する
     */
    public static String createArticleImagePath(int articleId, String fileName) {
        return articleId + "_" + encrypt(fileName);
    }

    /**
     * 記事詳細のパスを生成する
     */
    public static String createArticleDetailsPath(String email, int articleId) {
        return articleId + "_" + encrypt(email);
    }

    /**
     * 質問画像のパスを生成する
     */
    public static String createQuestionImagePath(int questionId, String fileName) {
        return questionId + "_" + encrypt(fileName);
    }

    /**
     * 回答画像のパスを生成する
     */
    public static String createAnswerImagePath(int answerId, String fileName) {
        return answerId + "_" + encrypt(fileName);
    }

    /**
     * 献立詳細のパスを取得する
     */
    public static String getMenuDetailsPath(String path) {
        return "/" + MenuDeliverConstants.MENU_DETAIL_PATH + path;
    }

    /**
     * 献立画像のパスを取得する
     */
    public static String getMenuImagePath(String path) {
        return "/" + MenuDeliverConstants.MENU_IMAGE_PATH + path;
    }

    /**
     * ユーザアイコンのパスを取得する
     */
    public static String getUserIconPath(String path) {
        return "/" + MenuDeliverConstants.USER_ICON_PATH + path;
    }

    /**
     * 記事画像のパスを取得する
     */
    public static String getArticleImagePath(String path) {
        return "/" + MenuDeliverConstants.ARTICLE_IMAGE_PATH + path;
    }

    /**
     * 記事詳細のパスを取得する
     */
    public static String getArticleDetailsPath(String path) {
        return "/" + MenuDeliverConstants.ARTICLE_DETAIL_PATH + path;
    }

    /**
     * おすすめ記事画像のパスを取得する
     */
    public static String getRecommendImagePath(String path) {
        return "/" + MenuDeliverConstants.RECOMMEND_IMAGE_PATH + path;
    }

    /**
     * おすすめ記事詳細のパスを取得する
     */
    public static String getRecommendDetailsPath(String path) {
        return "/" + MenuDeliverConstants.RECOMMEND_DETAIL_PATH + path;
    }

    /**
     * 質問画像のパスを取得する
     */
    public static String getQuestionImagePath(String path) {
        return "/" + MenuDeliverConstants.QUESTION_IMAGE_PATH + path;
    }

    /**
     * 回答画像のパスを取得する
     */
    public static String getAnswerImagePath(String path) {
        return "/" + MenuDeliverConstants.ANSWER_IMAGE_PATH + path;
    }

    private static String encodeUrl(String param) {
        URLCodec codec = new URLCodec("UTF-8");
        try {
            return codec.encode(param);
        } catch (EncoderException e) {
            throw new MenuDeliverException("エンコードに失敗しました。", e);
        }
    }

    private static String encrypt(String param) {
        Cipher encrypter;
        byte[] byteToken = null;

        try {
            encrypter = Cipher.getInstance("AES/CBC/PKCS5Padding");
            encrypter.init(Cipher.ENCRYPT_MODE, key, iv);
            byteToken = encrypter.doFinal(param.getBytes());

        } catch (Exception e) {
            throw new MenuDeliverException("暗号化に失敗しました。", e);
        }

        return new String(Base64.getEncoder().encode(byteToken));
    }

}
