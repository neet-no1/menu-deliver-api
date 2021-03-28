package jp.co.suyama.menu.deliver.controller.impl;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jp.co.suyama.menu.deliver.common.MenuDeliverStatus;
import jp.co.suyama.menu.deliver.controller.interfaces.ArticleApi;
import jp.co.suyama.menu.deliver.model.ArticlesAndPage;
import jp.co.suyama.menu.deliver.model.auto.ArticleDataResponse;
import jp.co.suyama.menu.deliver.model.auto.ArticlesResponse;
import jp.co.suyama.menu.deliver.model.auto.BasicResponse;
import jp.co.suyama.menu.deliver.model.auto.DeleteArticleParam;
import jp.co.suyama.menu.deliver.model.auto.FavoriteArticleParam;
import jp.co.suyama.menu.deliver.service.ArticleService;

@RestController
public class ArticleController implements ArticleApi {

    // 記事サービス
    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseEntity<BasicResponse> deleteArticle(@Valid DeleteArticleParam deleteArticleParam) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public ResponseEntity<BasicResponse> favoriteArticle(@Valid FavoriteArticleParam favoriteArticleParam) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public ResponseEntity<ArticleDataResponse> getArticle(@NotNull @Valid String id) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public ResponseEntity<ArticlesResponse> getArticleNewArrival() {

        // レスポンス作成
        ArticlesResponse response = new ArticlesResponse();

        Map<String, Object> articleItems = new HashMap<>();
        // 新着記事を検索
        ArticlesAndPage articlesAndPage = articleService.getArticleNewArrival();
        articleItems.put("articles", articlesAndPage.getArticleDataList());

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(articleItems);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BasicResponse> postArticle(Integer id, String title, String contents, Boolean opened,
            @Valid MultipartFile thumb) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

}
