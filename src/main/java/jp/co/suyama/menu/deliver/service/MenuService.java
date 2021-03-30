package jp.co.suyama.menu.deliver.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.suyama.menu.deliver.common.S3Access;
import jp.co.suyama.menu.deliver.exception.MenuDeliverException;
import jp.co.suyama.menu.deliver.mapper.CompositionsMapperImpl;
import jp.co.suyama.menu.deliver.mapper.FavoriteMenusMapperImpl;
import jp.co.suyama.menu.deliver.mapper.MenuCategoriesMapperImpl;
import jp.co.suyama.menu.deliver.mapper.MenuCompositionsMapperImpl;
import jp.co.suyama.menu.deliver.mapper.MenuDetailsMapperImpl;
import jp.co.suyama.menu.deliver.mapper.MenuPicturesMapperImpl;
import jp.co.suyama.menu.deliver.mapper.MenuViewsMapperImpl;
import jp.co.suyama.menu.deliver.mapper.MenusMapperImpl;
import jp.co.suyama.menu.deliver.mapper.UsersMapperImpl;
import jp.co.suyama.menu.deliver.model.MenuComposition;
import jp.co.suyama.menu.deliver.model.MenusAndPage;
import jp.co.suyama.menu.deliver.model.auto.CompositionData;
import jp.co.suyama.menu.deliver.model.auto.MenuCategoryData;
import jp.co.suyama.menu.deliver.model.auto.MenuData;
import jp.co.suyama.menu.deliver.model.auto.PageNation;
import jp.co.suyama.menu.deliver.model.db.Compositions;
import jp.co.suyama.menu.deliver.model.db.FavoriteMenus;
import jp.co.suyama.menu.deliver.model.db.MenuCategories;
import jp.co.suyama.menu.deliver.model.db.MenuCompositions;
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

    // オブジェクトマッパー
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

    // 献立素材テーブル
    @Autowired
    private MenuCompositionsMapperImpl menuCompositionsMapper;

    // 献立閲覧数テーブル
    @Autowired
    private MenuViewsMapperImpl menuViewsMapper;

    // お気に入り献立テーブル
    @Autowired
    private FavoriteMenusMapperImpl favoriteMenusMapper;

    // ユーザテーブル
    @Autowired
    private UsersMapperImpl usersMapper;

    // 食品成分表テーブル
    @Autowired
    private CompositionsMapperImpl compositionsMapper;

    /**
     * 献立を削除する
     *
     * @param email メールアドレス
     * @param id    献立ID
     */
    public void deleteMenu(String email, int id) {

        // ユーザ情報取得
        Users user = usersMapper.selectEmail(email);

        // 存在していない場合エラー
        if (user == null) {
            throw new MenuDeliverException("ユーザが存在しません。");
        }

        // 献立を取得
        Menus menu = menusMapper.selectByPrimaryKey(id);

        if (menu == null) {
            // 献立が存在しない場合、その場で終了
            return;
        }

        // 自分が投稿したものでなければ削除不可
        if (menu.getUserId() != user.getId()) {
            throw new MenuDeliverException("削除対象が自身のものではありません。");
        }

        // 献立を削除
        menusMapper.deleteByPrimaryKey(id);

        // お気に入り献立を削除
        favoriteMenusMapper.deleteAllByMenuId(id);

        // 献立画像を取得
        List<MenuPictures> pictures = menuPicturesMapper.selectAllByMenuId(id);

        // 献立画像を削除
        menuPicturesMapper.deleteAllByMenuId(id);

        // 献立内容を取得
        MenuDetails detail = menuDetailsMapper.selectByMenusId(id);

        // 献立内容を削除
        menuDetailsMapper.deleteByPrimaryKey(detail.getId());

        // 献立閲覧数を削除
        menuViewsMapper.deleteAllByMenuId(id);

        // 献立素材を削除
        menuCompositionsMapper.deleteAllByMenuId(id);

        // S3のものをすべて削除する
        List<String> deleteKeys = new ArrayList<>();
        deleteKeys.add(PathUtils.getMenuImagePath(menu.getPath()));
        deleteKeys.add(PathUtils.getMenuDetailsPath(detail.getPath()));
        deleteKeys.addAll(
                pictures.stream().map(p -> PathUtils.getMenuImagePath(p.getPath())).collect(Collectors.toList()));

        s3Access.deleteItems(deleteKeys);
    }

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
        menus.setPath("no_image");

        if (id == 0) {
            // IDが0の場合は登録
            menusId = menusMapper.registMenu(menus);

            // サムネイル画像がある場合
            if (!thumb.isEmpty()) {
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
                throw new MenuDeliverException("更新対象が自身のものではありません。");
            }

            // サムネイル画像がある場合
            if (!thumb.isEmpty()) {
                // サムネイル画像のパスを更新する
                String fileName = thumb.getOriginalFilename();
                thumbPath = PathUtils.createMenuImagePath(menusId, fileName);
                menus.setPath(thumbPath);
                deletePath.add(PathUtils.getArticleImagePath(existMenus.getPath()));
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
            deletePath.add(PathUtils.getMenuDetailsPath(existMenuDetails.getPath()));

            // 更新
            menuDetails.setId(existMenuDetails.getId());
            menuDetailsMapper.updateMenuDetails(menuDetails);

        }

        // 献立素材を削除する
        menuCompositionsMapper.deleteAllByMenuId(menusId);

        // 献立素材を登録する
        MenuComposition[] menuCompArray = null;
        try {
            // 献立内容をオブジェクトに変換する
            menuCompArray = objectMapper.readValue(contents, MenuComposition[].class);
        } catch (Exception e) {
            throw new MenuDeliverException("献立内容の変換に失敗しました。");
        }

        // 重複しない食品番号のリストを取得する
        List<Integer> compositionIdList = new ArrayList<>(
                Arrays.asList(menuCompArray).stream().map(mc -> mc.getCompId()).collect(Collectors.toSet()));

        // 登録用のオブジェクトを生成
        final int compMenusId = menusId;
        List<MenuCompositions> menuCompositionsList = compositionIdList.stream().map(c -> {
            MenuCompositions m = new MenuCompositions();
            m.setMenuId(compMenusId);
            m.setCompositionItemNo(c);
            return m;
        }).collect(Collectors.toList());

        // 登録
        menuCompositionsMapper.registMenuCompositions(menuCompositionsList);

        // 献立画像をすべて取得する
        deletePath = menuPicturesMapper.selectAllByMenuId(menusId).stream()
                .map(m -> PathUtils.getMenuImagePath(m.getPath())).collect(Collectors.toList());

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

        // S3関連の処理を実装する

        // 献立画像を削除
        if (!deletePath.isEmpty()) {
            s3Access.deleteItems(deletePath);
        }

        // サムネイルファイルをS3にアップロードする
        if (!thumb.isEmpty()) {
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

        List<MenuData> menuDataList = convertMenuDataList(null, menusList);

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

        List<MenuData> menuDataList = convertMenuDataList(null, menusList);

        // レスポンスに値を設定する
        result.setMenuDataList(menuDataList);
        result.setPage(pageNation);

        return result;
    }

    /**
     * 献立を検索する
     *
     * @param keywordList 検索キーワード
     * @param categories  検索カテゴリID
     * @param page        取得ページ
     * @return 献立一覧
     */
    public MenusAndPage searchMenus(List<String> keywordList, List<Integer> categories, int page) {

        // レスポンス
        MenusAndPage result = new MenusAndPage();

        // 取得件数
        int limit = 8;

        // 全体の件数を取得する
        int count = menusMapper.countSearchMenus(keywordList, categories);

        // ページネーションを取得
        PageNation pageNation = PageNationUtils.createPageNation(page, count, limit);

        // 取得ページからoffsetを計算する
        int offset = (pageNation.getCurrentPage() - 1) * limit;

        // 検索処理
        // カテゴリは in句
        // キーワードは部分一致のAND
        List<Menus> menusList = menusMapper.searchMenus(keywordList, categories, limit, offset);

        List<MenuData> menuDataList = convertMenuDataList(null, menusList);

        // レスポンスに値を設定する
        result.setMenuDataList(menuDataList);
        result.setPage(pageNation);

        return result;
    }

    /**
     * 新着献立を取得する
     *
     * @return 新着献立一覧
     */
    public MenusAndPage getMenuNewArrival() {

        // レスポンス
        MenusAndPage result = new MenusAndPage();

        // 取得件数
        int limit = 8;

        // 献立画像と献立内容以外を取得する
        List<Menus> menusList = menusMapper.selectAllNewArrival(limit);

        List<MenuData> menuDataList = convertMenuDataList(null, menusList);

        // レスポンスに値を設定する
        result.setMenuDataList(menuDataList);

        return result;
    }

    /**
     * 人気献立を取得する
     *
     * @return 人気献立一覧
     */
    public MenusAndPage getMenuPopular() {

        // レスポンス
        MenusAndPage result = new MenusAndPage();

        // 取得件数
        int limit = 8;

        // 献立画像と献立内容以外を取得する
        List<Menus> menusList = menusMapper.selectAllPopular(limit);

        List<MenuData> menuDataList = convertMenuDataList(null, menusList);

        // レスポンスに値を設定する
        result.setMenuDataList(menuDataList);

        return result;
    }

    /**
     * 献立カテゴリ一覧を取得する
     *
     * @return 献立カテゴリ一覧
     */
    public List<MenuCategoryData> getMenuCategories() {

        // レスポンス
        MenuCategoryData data = null;
        List<MenuCategoryData> result = new ArrayList<>();

        // カテゴリ一覧を取得する
        List<MenuCategories> categories = menuCategoriesMapper.selectAll();

        // 詰め替えをする
        for (MenuCategories category : categories) {
            data = new MenuCategoryData();
            data.setId(category.getId());
            data.setName(category.getName());

            result.add(data);
        }

        return result;
    }

    /**
     * 食品成分表情報を取得する
     *
     * @return 食品成分表情報
     */
    public List<CompositionData> getCompositions() {

        // レスポンス
        CompositionData data = null;
        List<CompositionData> result = new ArrayList<>();

        // 食品成分表情報を取得する
        List<Compositions> compositions = compositionsMapper.selectAll();

        // 詰め替えする
        for (Compositions composition : compositions) {
            data = new CompositionData();
            data.setId(composition.getItemNo());
            data.setName(composition.getName());
            data.setRefuse(composition.getRefuse().intValue());
            data.setEnergy(composition.getEnergy().intValue());
            data.setProtein(composition.getProtein());
            data.setLipid(composition.getLipid());
            data.setCarbohydrate(composition.getCarbohydrate());
            data.setCalcium(composition.getCalcium().intValue());
            data.setIron(composition.getIron());
            data.setCholesterol(composition.getCholesterol().intValue());
            data.setDietaryFibers(composition.getDietaryFibers());
            data.setSaltEquivalents(composition.getSaltEquivalents());

            result.add(data);
        }

        return result;
    }

    /**
     * 献立内容を取得する
     *
     * @param email メールアドレス
     * @param id    献立ID
     * @return 献立内容
     */
    public MenuData getMenu(String email, int id) {

        // ユーザ情報取得
        Integer userId = null;
        Users user = usersMapper.selectEmail(email);

        // 存在する場合、ユーザIDを設定
        if (user != null) {
            userId = user.getId();
        }

        // 献立情報を取得する
        Menus menu = menusMapper.selectByPrimaryKey(id);

        // 存在しない場合エラー
        if (menu == null) {
            throw new MenuDeliverException("献立が存在しません。");
        }

        // 詰め替えする
        MenuData result = convertMenuData(userId, menu);

        // 献立閲覧数を追加
        if (!result.isMine()) {
            // 自分のもの以外の場合追加
            menuViewsMapper.registMenuViews(result.getId());
        }

        return result;
    }

    /**
     * 献立をお気に入りに登録する
     *
     * @param email メールアドレス
     * @param id    献立ID
     * @param added 追加・解除フラグ
     */
    public void favoriteMenu(String email, int id, boolean added) {

        // ユーザ情報取得
        Users user = usersMapper.selectEmail(email);

        // 存在していない場合エラー
        if (user == null) {
            throw new MenuDeliverException("ユーザが存在しません。");
        }

        // 献立情報取得
        Menus menu = menusMapper.selectByPrimaryKey(id);

        // 存在していない場合エラー
        if (menu == null) {
            throw new MenuDeliverException("献立が存在しません。");
        }

        if (added) {
            // 追加処理
            // 現在存在するか確認する
            FavoriteMenus favoriteMenu = favoriteMenusMapper.selectByUserIdMenuId(user.getId(), menu.getId());

            if (favoriteMenu != null) {
                // 存在する場合、何もせず終了
                return;
            }

            // 追加
            FavoriteMenus registFavoriteMenu = new FavoriteMenus();
            registFavoriteMenu.setUserId(user.getId());
            registFavoriteMenu.setMenuId(menu.getId());

            favoriteMenusMapper.registFavoriteMenus(registFavoriteMenu);
        } else {
            // 解除処理
            favoriteMenusMapper.deleteByUserIdMenuId(user.getId(), menu.getId());
        }
    }

    /**
     * 献立情報リストから関連情報を取得し、献立データリストに変換する
     *
     * @param userId    ユーザID(自分の投稿かを判断する)
     * @param menusList 献立情報リスト
     * @return 献立データリスト
     */
    private List<MenuData> convertMenuDataList(Integer userId, List<Menus> menusList) {

        // レスポンス献立データ
        List<MenuData> menuDataList = new ArrayList<>();

        // 画像と内容を取得する
        for (Menus menus : menusList) {
            // 献立データを追加
            menuDataList.add(convertMenuData(userId, menus));
        }

        return menuDataList;
    }

    /**
     * 献立情報から関連情報を取得し、献立データに変換する
     *
     * @param userId ユーザID(自分の投稿かを判断する)
     * @param menus  献立情報
     * @return 献立データ
     */
    private MenuData convertMenuData(Integer userId, Menus menus) {

        // 画像情報を取得する
        List<MenuPictures> menuPictureList = menuPicturesMapper.selectAllByMenuId(menus.getId());

        // 献立内容を取得する
        MenuDetails contents = menuDetailsMapper.selectByMenusId(menus.getId());

        // データを詰め替える
        MenuData data = new MenuData();
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
        data.setMine(userId == null ? false : userId == menus.getUserId());

        return data;
    }
}
