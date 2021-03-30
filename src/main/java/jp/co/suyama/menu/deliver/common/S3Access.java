package jp.co.suyama.menu.deliver.common;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.suyama.menu.deliver.exception.MenuDeliverException;

@Component
public class S3Access {

    // バケット名
    @Value("${aws.s3.bucket}")
    private String bucket;

    // S3クライアント
    @Autowired
    private AmazonS3 s3;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * ユーザアイコンをS3にアップロードする
     *
     * @param key  オブジェクトキー
     * @param file アイコンファイル
     */
    public void uploadUserIcon(String key, File file) {
        s3.putObject(bucket, MenuDeliverConstants.USER_ICON_PATH + key, file);
    }

    /**
     * 献立画像をS3にアップロードする
     *
     * @param key  オブジェクトキー
     * @param file 献立画像
     */
    public void uploadMenuImage(String key, File file) {
        s3.putObject(bucket, MenuDeliverConstants.MENU_IMAGE_PATH + key, file);
    }

    /**
     * 記事画像をS3にアップロードする
     *
     * @param key  オブジェクトキー
     * @param file 記事画像
     */
    public void uploadArticleImage(String key, File file) {
        s3.putObject(bucket, MenuDeliverConstants.ARTICLE_IMAGE_PATH + key, file);
    }

    /**
     * 献立内容をS3にアップロードする
     *
     * @param key      オブジェクトキー
     * @param contents 献立内容
     */
    public void uploadMenuDetail(String key, Map<String, String> contents) {
        try {
            s3.putObject(bucket, MenuDeliverConstants.MENU_DETAIL_PATH + key,
                    objectMapper.writeValueAsString(contents));
        } catch (Exception e) {
            // アップロード時にエラー
            throw new MenuDeliverException("アップロードが失敗しました。", e);
        }
    }

    /**
     * 記事内容をS3にアップロードする
     *
     * @param key      オブジェクトキー
     * @param contents 記事内容
     */
    public void uploadArticleDetail(String key, String contents) {
        try {
            s3.putObject(bucket, MenuDeliverConstants.ARTICLE_DETAIL_PATH + key,
                    objectMapper.writeValueAsString(contents));
        } catch (Exception e) {
            // アップロード時にエラー
            throw new MenuDeliverException("アップロードが失敗しました。", e);
        }
    }

    /**
     * S3オブジェクトを複数削除する
     *
     * @param keys オブジェクトキーリスト
     */
    public void deleteItems(List<String> keys) {
        DeleteObjectsRequest dor = new DeleteObjectsRequest(bucket).withKeys(keys.toArray(new String[] {}));
        s3.deleteObjects(dor);
    }
}
