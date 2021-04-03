package jp.co.suyama.menu.deliver.common;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.suyama.menu.deliver.exception.MenuDeliverException;

@Component
public class S3Access {

    // ロガー
    private Logger log = LoggerFactory.getLogger(S3Access.class);

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
    public void uploadUserIcon(String key, File file, String contentType, long length) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        metadata.setContentLength(length);
        PutObjectRequest request = new PutObjectRequest(bucket, MenuDeliverConstants.USER_ICON_PATH + key, file);
        request.setMetadata(metadata);

        log.info("contentType: [{}], length: [{}]", contentType, length);

        s3.putObject(request);
    }

    /**
     * 献立画像をS3にアップロードする
     *
     * @param key  オブジェクトキー
     * @param file 献立画像
     */
    public void uploadMenuImage(String key, MultipartFile file, String contentType, long length) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            metadata.setContentLength(length);
            PutObjectRequest request;
            request = new PutObjectRequest(bucket, MenuDeliverConstants.MENU_IMAGE_PATH + key, file.getInputStream(),
                    metadata);

            log.info("bucket: [{}], path: [{}], contentType: [{}], length: [{}]", bucket,
                    MenuDeliverConstants.MENU_IMAGE_PATH + key, contentType, length);

            s3.putObject(request);
        } catch (IOException e) {
            // アップロード時にエラー
            throw new MenuDeliverException("アップロードが失敗しました。", e);
        }
    }

    /**
     * 記事画像をS3にアップロードする
     *
     * @param key  オブジェクトキー
     * @param file 記事画像
     */
    public void uploadArticleImage(String key, File file, String contentType, long length) {
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentType(contentType);
//        metadata.setContentLength(length);
//        PutObjectRequest request = new PutObjectRequest(bucket, MenuDeliverConstants.ARTICLE_IMAGE_PATH + key, file);
//        request.setMetadata(metadata);
//
//        log.info("contentType: [{}], length: [{}]", contentType, length);
//
//        s3.putObject(request);
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
//            String contentsString = objectMapper.writeValueAsString(contents);
//
//            ObjectMetadata metadata = new ObjectMetadata();
//            metadata.setContentType("json");
//            metadata.setContentLength(contentsString.getBytes().length);
//            PutObjectRequest request = new PutObjectRequest(bucket, MenuDeliverConstants.MENU_DETAIL_PATH + key,
//                    contentsString);
//            request.setMetadata(metadata);
//
//            log.info("length: [{}]", contentsString.getBytes().length);
//
//            s3.putObject(request);
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
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("json");
            metadata.setContentLength(contents.getBytes().length);
            PutObjectRequest request = new PutObjectRequest(bucket, MenuDeliverConstants.ARTICLE_DETAIL_PATH + key,
                    objectMapper.writeValueAsString(contents));
            request.setMetadata(metadata);

            log.info("length: [{}]", contents.getBytes().length);

            s3.putObject(request);
        } catch (Exception e) {
            // アップロード時にエラー
            throw new MenuDeliverException("アップロードが失敗しました。", e);
        }
    }

    /**
     * 質問画像をS3にアップロードする
     *
     * @param key  オブジェクトキー
     * @param file 質問画像
     */
    public void uploadQuestionImage(String key, File file, String contentType, long length) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        metadata.setContentLength(length);
        PutObjectRequest request = new PutObjectRequest(bucket, MenuDeliverConstants.QUESTION_IMAGE_PATH + key, file);
        request.setMetadata(metadata);

        log.info("contentType: [{}], length: [{}]", contentType, length);

        s3.putObject(request);
    }

    /**
     * 回答画像をS3にアップロードする
     *
     * @param key  オブジェクトキー
     * @param file 回答画像
     */
    public void uploadAnswerImage(String key, File file, String contentType, long length) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        metadata.setContentLength(length);
        PutObjectRequest request = new PutObjectRequest(bucket, MenuDeliverConstants.ANSWER_IMAGE_PATH + key, file);
        request.setMetadata(metadata);
        s3.putObject(request);
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
