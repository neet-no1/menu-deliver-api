package jp.co.suyama.menu.deliver.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import org.springframework.web.multipart.MultipartFile;

import jp.co.suyama.menu.deliver.exception.MenuDeliverException;

public class ConvertUtils {

    /**
     * MultipartFileをFileに変換する
     */
    public static File convertFile(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());

        try {
            convFile.createNewFile();
        } catch (IOException e) {
            throw new MenuDeliverException("ファイル生成に失敗しました。", e);
        }

        try (InputStream is = file.getInputStream()) {
            Files.copy(is, convFile.toPath());
        } catch (IOException e) {
            throw new MenuDeliverException("ファイルコピーに失敗しました。", e);
        }
        return convFile;
    }
}
