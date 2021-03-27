package jp.co.suyama.menu.deliver.utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;

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
        return menusId + "_" + encodeUrl(encrypt(email));
    }

    /**
     * 献立画像のパスを生成する
     */
    public static String createMenuImagePath(int menusId, String fileName) {
        return menusId + "_" + encodeUrl(encrypt(fileName));
    }

    /**
     * ユーザアイコンのパスを生成する
     */
    public static String createUserIconPath(int userId, String email) {
        return userId + "_" + encodeUrl(encrypt(email));
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
