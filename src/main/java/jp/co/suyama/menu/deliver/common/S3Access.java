package jp.co.suyama.menu.deliver.common;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;

@Component
public class S3Access {

    // バケット名
    @Value("${aws.s3.bucket}")
    private String bucket;

    // S3クライアント
    @Autowired
    private AmazonS3 s3;

    /**
     * ユーザアイコンをS3にアップロードする
     *
     * @param key  オブジェクトキー
     * @param file アイコンファイル
     */
    public void uploadUserIcon(String key, File file) {
        s3.putObject(bucket, MenuDeliverConstants.USER_ICON_PATH + key, file);
    }
}
