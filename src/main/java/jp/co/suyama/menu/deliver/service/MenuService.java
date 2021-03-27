package jp.co.suyama.menu.deliver.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.suyama.menu.deliver.common.MenuDeliverConstants;
import jp.co.suyama.menu.deliver.exception.MenuDeliverException;
import jp.co.suyama.menu.deliver.mapper.MenuCategoriesMapperImpl;
import jp.co.suyama.menu.deliver.mapper.MenuDetailsMapperImpl;
import jp.co.suyama.menu.deliver.mapper.MenuPicturesMapperImpl;
import jp.co.suyama.menu.deliver.mapper.MenusMapperImpl;
import jp.co.suyama.menu.deliver.mapper.UsersMapperImpl;
import jp.co.suyama.menu.deliver.model.db.MenuCategories;
import jp.co.suyama.menu.deliver.model.db.MenuDetails;
import jp.co.suyama.menu.deliver.model.db.MenuPictures;
import jp.co.suyama.menu.deliver.model.db.Menus;
import jp.co.suyama.menu.deliver.model.db.Users;
import jp.co.suyama.menu.deliver.utils.ConvertUtils;
import jp.co.suyama.menu.deliver.utils.PathUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class MenuService {

    // バケット名
    @Value("${aws.s3.bucket}")
    private String bucket;

    // S3クライアント
    @Autowired
    private AmazonS3 s3;

    @Autowired
    private ObjectMapper objectMapper;

    // 献立テーブル
    @Autowired
    private MenusMapperImpl menusMapper;

    // 献立画像テーブル
    @Autowired
    private MenuPicturesMapperImpl menuPicturesMapper;

    // 献立詳細テーブル
    @Autowired
    private MenuDetailsMapperImpl menuDetailsMapper;

    // 献立カテゴリテーブル
    @Autowired
    private MenuCategoriesMapperImpl menuCategoriesMapper;

    // ユーザテーブル
    @Autowired
    private UsersMapperImpl usersMapper;

    /**
     * 献立を投稿する
     *
     * @param id               献立ID 新規追加時は0を指定
     * @param title            献立名
     * @param subTitle         キャッチコピー
     * @param categoryId       カテゴリID
     * @param contents         献立内容
     * @param thumb            サムネイル画像
     * @param cookery          作り方
     * @param files            作り方のファイル
     * @param filesDescription 作り方のファイルの説明
     * @param opened           公開フラグ
     */
    public void postMenu(int id, String title, String subTitle, int categoryId, String contents, MultipartFile thumb,
            String cookery, List<MultipartFile> files, List<String> filesDescription, boolean opened, String email) {

        // 献立ID
        int menusId = id;

        // サムネイル画像パス
        String thumbPath = null;

        // 作り方画像パス
        Map<String, MultipartFile> filePaths = new HashMap<>();

        // 献立内容パス
        String contentsPath = null;

        // カテゴリ存在チェック
        MenuCategories menuCategories = menuCategoriesMapper.selectByPrimaryKey(categoryId);
        if (menuCategories == null) {
            // DBにカテゴリが存在しない場合はエラー
            throw new MenuDeliverException("カテゴリが存在しません。");
        }

        // ユーザID取得
        Users users = usersMapper.selectEmail(email);
        if (users == null) {
            // ユーザが存在しない場合はエラー
            throw new MenuDeliverException("ユーザが存在しません。");
        }

        // 献立テーブルを登録・更新する
        Menus menus = new Menus();
        menus.setTitle(title);
        menus.setSubTitle(subTitle);
        menus.setCategoryId(categoryId);
        menus.setOpened(opened);
        menus.setUserId(users.getId());

        if (id == 0) {
            // IDが0の場合は登録
            menusId = menusMapper.registMenu(menus);

            // サムネイル画像がある場合
            if (thumb != null) {
                // サムネイル画像のパスを更新する
                String fileName = thumb.getOriginalFilename();
                thumbPath = PathUtils.createMenuImagePath(menusId, fileName);
                menusMapper.updateMenusPath(menusId, thumbPath);
            }

            // 献立詳細テーブルに登録
            contentsPath = PathUtils.createMenuDetailsPath(email, menusId);
            MenuDetails menuDetails = new MenuDetails();
            menuDetails.setMenuId(menusId);
            menuDetails.setPath(contentsPath);

            menuDetailsMapper.registMenuDetails(menuDetails);

        } else {
            // IDが0以外の場合は更新
            Menus existMenus = menusMapper.selectByPrimaryKey(id);
            if (existMenus == null) {
                // DBに対象が存在しない場合はエラー
                throw new MenuDeliverException("更新対象が存在しません。");
            }

            // 更新対象が自分のものか確認
            if (existMenus.getUserId() != users.getId()) {
                throw new MenuDeliverException("更新対象が自分のものではありません。");
            }

            // サムネイル画像がある場合
            if (thumb != null) {
                // サムネイル画像のパスを更新する
                String fileName = thumb.getOriginalFilename();
                thumbPath = PathUtils.createMenuImagePath(menusId, fileName);
                menus.setPath(thumbPath);
            }

            // 更新
            menus.setId(existMenus.getId());
            menusMapper.updateMenu(menus);

            // 献立詳細テーブルを更新
            MenuDetails existMenuDetails = menuDetailsMapper.selectByMenusId(menusId);
            if (existMenuDetails == null) {
                // DBに対象が存在しない場合はエラー
                throw new MenuDeliverException("更新対象が存在しません。");
            }

            contentsPath = PathUtils.createMenuDetailsPath(email, menusId);
            MenuDetails menuDetails = new MenuDetails();
            menuDetails.setMenuId(menusId);
            menuDetails.setPath(contentsPath);

            // 更新
            menuDetails.setId(existMenuDetails.getId());
            menuDetailsMapper.updateMenuDetails(menuDetails);

        }

        // 献立画像テーブルを登録する
        files = files == null ? new ArrayList<>() : files;

        filesDescription = filesDescription == null ? new ArrayList<>() : filesDescription;

        for (int i = 0; i < files.size(); i++) {
            // ファイルが空なら次へ進む
            if (files.get(i) == null) {
                continue;
            }

            // ファイルの説明を取得する
            String description = "";

            if (filesDescription.size() > i) {
                description = filesDescription.get(i);
                description = description == null ? "" : description;
            }

            // ファイルパスを取得する
            MultipartFile file = files.get(i);
            String imgPath = PathUtils.createMenuImagePath(menusId, file.getOriginalFilename());

            MenuPictures pictures = new MenuPictures();
            pictures.setMenuId(menusId);
            pictures.setPath(imgPath);
            pictures.setOrderOf(i);
            pictures.setDescription(description);

            menuPicturesMapper.registMenuPicture(pictures);

            // 作り方画像パスリストに追加
            filePaths.put(imgPath, files.get(i));
        }

        // サムネイルファイルをS3にアップロードする
        if (thumb != null) {
            File thumbFile = ConvertUtils.convertFile(thumb);
            s3.putObject(bucket, MenuDeliverConstants.MENU_IMAGE_PATH + thumbPath, thumbFile);
        }

        // 作り方ファイルをS3にアップロードする
        for (Entry<String, MultipartFile> filePath : filePaths.entrySet()) {
            File file = ConvertUtils.convertFile(filePath.getValue());
            s3.putObject(bucket, MenuDeliverConstants.MENU_IMAGE_PATH + filePath.getKey(), file);
        }

        // 献立と作り方をまとめてS3にアップロードする
        if (contentsPath != null) {
            Map<String, String> contentsMap = new HashMap<>();
            contentsMap.put("contents", contents);
            contentsMap.put("cookery", cookery);

            try {
                s3.putObject(bucket, MenuDeliverConstants.MENU_DETAIL_PATH + contentsPath,
                        objectMapper.writeValueAsString(contentsMap));
            } catch (Exception e) {
                // アップロード時にエラー
                throw new MenuDeliverException("アップロードが失敗しました。", e);
            }
        }

    }
}
