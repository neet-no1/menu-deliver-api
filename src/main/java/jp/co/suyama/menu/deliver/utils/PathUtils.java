package jp.co.suyama.menu.deliver.utils;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;

import jp.co.suyama.menu.deliver.exception.MenuDeliverException;

public class PathUtils {

    /**
     * 献立詳細のパスを生成する
     */
    public static String createMenuDetailsPath(String email, int menusId) {
        return menusId + "_" + encodeUrl(String.valueOf(email.hashCode()));
    }

    /**
     * 献立画像のパスを生成する
     */
    public static String createMenuImagePath(int menusId, String fileName) {
        return menusId + "_" + encodeUrl(fileName);
    }

    private static String encodeUrl(String param) {
        URLCodec codec = new URLCodec("UTF-8");
        try {
            return codec.encode(param);
        } catch (EncoderException e) {
            throw new MenuDeliverException("エンコードに失敗しました。", e);
        }
    }
}
