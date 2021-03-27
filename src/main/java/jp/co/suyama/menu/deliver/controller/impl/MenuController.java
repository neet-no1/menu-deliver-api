package jp.co.suyama.menu.deliver.controller.impl;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jp.co.suyama.menu.deliver.common.MenuDeliverStatus;
import jp.co.suyama.menu.deliver.controller.interfaces.MenuApi;
import jp.co.suyama.menu.deliver.model.auto.BasicResponse;
import jp.co.suyama.menu.deliver.model.auto.CompositionsResponse;
import jp.co.suyama.menu.deliver.model.auto.DeleteMenuParam;
import jp.co.suyama.menu.deliver.model.auto.ErrorInfo;
import jp.co.suyama.menu.deliver.model.auto.FavoriteMenuParam;
import jp.co.suyama.menu.deliver.model.auto.MenuCategoriesResponse;
import jp.co.suyama.menu.deliver.model.auto.MenuDataResponse;
import jp.co.suyama.menu.deliver.model.auto.MenusResponse;
import jp.co.suyama.menu.deliver.service.MenuService;

@RestController
public class MenuController implements MenuApi {

    // 献立サービス
    @Autowired
    private MenuService menuService;

    @Override
    public ResponseEntity<BasicResponse> deleteMenu(@Valid DeleteMenuParam deleteArticleParam) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public ResponseEntity<BasicResponse> favoriteMenu(@Valid FavoriteMenuParam favoriteMenuParam) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public ResponseEntity<CompositionsResponse> getCompositions() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public ResponseEntity<MenuDataResponse> getMenu(@NotNull @Valid String id) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public ResponseEntity<MenuCategoriesResponse> getMenuCategories() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public ResponseEntity<MenusResponse> getMenuNewArrival() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public ResponseEntity<MenusResponse> getMenuPopular() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public ResponseEntity<BasicResponse> postMenu(Integer id, String title, Integer category, String contents,
            Boolean opened, String subTitle, @Valid MultipartFile thumb, String cookery, List<MultipartFile> files,
            List<String> filesDescription) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // レスポンス作成
        BasicResponse response = new BasicResponse();

        if ("anonymousUser".equals(auth.getPrincipal().toString())) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("ログインされていません。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // IDの存在チェック
        if (id == null) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("IDが空です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // "献立名の存在チェック
        if (StringUtils.isEmpty(title)) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("献立名が空です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // カテゴリの存在チェック
        if (category == null) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("カテゴリが空です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // 公開フラグの存在チェック
        if (opened == null) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("公開フラグが空です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // 献立名文字数チェック(100文字まで)
        if (title.length() > 100) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("献立名が長すぎます。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // キャッチコピー文字数チェック(200文字まで)
        if (subTitle != null && subTitle.length() > 200) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("キャッチコピーが長すぎます。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // 献立投稿処理
        menuService.postMenu(id.intValue(), title, subTitle, category.intValue(), contents, thumb, cookery, files,
                filesDescription, opened.booleanValue(), auth.getPrincipal().toString());

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(MenuDeliverStatus.SUCCESS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
