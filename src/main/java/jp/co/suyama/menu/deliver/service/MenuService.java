package jp.co.suyama.menu.deliver.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import jp.co.suyama.menu.deliver.common.S3Access;
import jp.co.suyama.menu.deliver.exception.MenuDeliverException;
import jp.co.suyama.menu.deliver.mapper.MenuCategoriesMapperImpl;
import jp.co.suyama.menu.deliver.mapper.MenuDetailsMapperImpl;
import jp.co.suyama.menu.deliver.mapper.MenuPicturesMapperImpl;
import jp.co.suyama.menu.deliver.mapper.MenusMapperImpl;
import jp.co.suyama.menu.deliver.mapper.UsersMapperImpl;
import jp.co.suyama.menu.deliver.model.MenusAndPage;
import jp.co.suyama.menu.deliver.model.auto.MenuData;
import jp.co.suyama.menu.deliver.model.auto.PageNation;
import jp.co.suyama.menu.deliver.model.db.MenuCategories;
import jp.co.suyama.menu.deliver.model.db.MenuDetails;
import jp.co.suyama.menu.deliver.model.db.MenuPictures;
import jp.co.suyama.menu.deliver.model.db.Menus;
import jp.co.suyama.menu.deliver.model.db.Users;
import jp.co.suyama.menu.deliver.utils.ConvertUtils;
import jp.co.suyama.menu.deliver.utils.PageNationUtils;
import jp.co.suyama.menu.deliver.utils.PathUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class MenuService {

    // S3アクセス
    @Autowired
    private S3Access s3Access;

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

        // 削除画像パス
        List<String> deletePath = new ArrayList<>();

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

        // 献立画像をすべて取得する
        deletePath = menuPicturesMapper.selectAllByMenuId(menusId).stream().map(m -> m.getPath())
                .collect(Collectors.toList());

        // 献立画像を削除する
        menuPicturesMapper.deleteAllByMenuId(menusId);

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
            s3Access.uploadMenuImage(thumbPath, thumbFile);
        }

        // 作り方ファイルをS3にアップロードする
        for (Entry<String, MultipartFile> filePath : filePaths.entrySet()) {
            File file = ConvertUtils.convertFile(filePath.getValue());
            s3Access.uploadMenuImage(filePath.getKey(), file);
        }

        // 献立と作り方をまとめてS3にアップロードする
        if (contentsPath != null) {
            Map<String, String> contentsMap = new HashMap<>();
            contentsMap.put("contents", contents);
            contentsMap.put("cookery", cookery);

            s3Access.uploadMenuDetail(contentsPath, contentsMap);
        }

        // 献立画像を削除
        if (!deletePath.isEmpty()) {
            s3Access.deleteMenuImages(deletePath);
        }
    }

    /**
     * お気に入り献立一覧を取得する
     *
     * @param email メールアドレス
     * @param page  ページ番号
     * @return お気に入り献立一覧
     */
    public MenusAndPage getFavoriteMenus(String email, int page) {

        // レスポンス
        MenusAndPage result = new MenusAndPage();

        // 取得件数
        int limit = 8;

        // 全体の件数を取得する
        int count = menusMapper.countAllFavoriteMenusByEmail(email);

        // ページネーションを取得
        PageNation pageNation = PageNationUtils.createPageNation(page, count, limit);

        // 取得ページからoffsetを計算する
        int offset = (pageNation.getCurrentPage() - 1) * limit;

        // メールアドレスから献立画像と献立内容以外を取得する
        List<Menus> menusList = menusMapper.selectAllFavoriteMenusByEmail(email, limit, offset);

        List<MenuData> menuDataList = convertMenuData(menusList);

        // レスポンスに値を設定する
        result.setMenuDataList(menuDataList);
        result.setPage(pageNation);

        return result;
    }

    /**
     * 投稿献立一覧を取得する
     *
     * @param email メールアドレス
     * @param page  ページ番号
     * @return 投稿献立一覧
     */
    public MenusAndPage getPostedMenu(String email, int page) {
        // レスポンス
        MenusAndPage result = new MenusAndPage();

        // 取得件数
        int limit = 8;

        // 全体の件数を取得する
        int count = menusMapper.countAllByEmail(email);

        // ページネーションを取得
        PageNation pageNation = PageNationUtils.createPageNation(page, count, limit);

        // 取得ページからoffsetを計算する
        int offset = (pageNation.getCurrentPage() - 1) * limit;

        // メールアドレスから献立画像と献立内容以外を取得する
        List<Menus> menusList = menusMapper.selectAllByEmail(email, limit, offset);

        List<MenuData> menuDataList = convertMenuData(menusList);

        // レスポンスに値を設定する
        result.setMenuDataList(menuDataList);
        result.setPage(pageNation);

        return result;
    }

    /**
     * 献立情報リストから関連情報を取得し、献立データリストに変換する
     *
     * @param menusList 献立情報リスト
     * @return 献立データリスト
     */
    private List<MenuData> convertMenuData(List<Menus> menusList) {
        // レスポンス献立データ
        MenuData data = null;
        List<MenuData> menuDataList = new ArrayList<>();

        // 画像と内容を取得する
        for (Menus menus : menusList) {
            // 画像情報を取得する
            List<MenuPictures> menuPictureList = menuPicturesMapper.selectAllByMenuId(menus.getId());

            // 献立内容を取得する
            MenuDetails contents = menuDetailsMapper.selectByMenusId(menus.getId());

            // データを詰め替える
            data = new MenuData();
            data.setId(menus.getId());
            data.setTitle(menus.getTitle());
            data.setSubTitle(menus.getSubTitle());
            data.setCategoryId(menus.getCategoryId());
            data.setThumbPath(PathUtils.getMenuImagePath(menus.getPath()));
            data.setContents(PathUtils.getMenuDetailsPath(contents.getPath()));
            data.setOpened(menus.getOpened());
            data.setImagePaths(menuPictureList.stream().map(p -> {
                Map<String, String> m = new HashMap<>();
                m.put("imageDescription", p.getDescription());
                m.put("uploadImageUrl", PathUtils.getMenuImagePath(p.getPath()));
                return m;
            }).collect(Collectors.toList()));

            // 献立データを追加
            menuDataList.add(data);
        }

        return menuDataList;
    }
}
