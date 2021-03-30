package jp.co.suyama.menu.deliver.controller.impl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import jp.co.suyama.menu.deliver.common.MenuDeliverStatus;
import jp.co.suyama.menu.deliver.controller.interfaces.FavoriteApi;
import jp.co.suyama.menu.deliver.model.auto.ErrorInfo;
import jp.co.suyama.menu.deliver.model.auto.FavoriteArticleIsAddedResponse;
import jp.co.suyama.menu.deliver.model.auto.FavoriteMenuIsAddedResponse;
import jp.co.suyama.menu.deliver.service.MenuService;

@RestController
public class FavoriteController implements FavoriteApi {

    // 献立サービス
    @Autowired
    private MenuService menuService;

    @Override
    public ResponseEntity<FavoriteArticleIsAddedResponse> favoriteArticleIsAdded(@NotNull @Valid Integer id) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public ResponseEntity<FavoriteMenuIsAddedResponse> favoriteMenuIsAdded(@NotNull @Valid Integer id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // レスポンス作成
        FavoriteMenuIsAddedResponse response = new FavoriteMenuIsAddedResponse();

        // 献立IDの存在チェック
        if (id == null) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("献立IDが空です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // 献立投稿処理
        boolean added = menuService.getFavoriteMenuAdded(auth.getPrincipal().toString(), id);

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(added);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
