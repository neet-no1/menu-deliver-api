package jp.co.suyama.menu.deliver.controller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jp.co.suyama.menu.deliver.common.MenuDeliverStatus;
import jp.co.suyama.menu.deliver.controller.interfaces.SearchApi;
import jp.co.suyama.menu.deliver.model.ArticlesAndPage;
import jp.co.suyama.menu.deliver.model.MenusAndPage;
import jp.co.suyama.menu.deliver.model.auto.ArticlesResponse;
import jp.co.suyama.menu.deliver.model.auto.MenusResponse;
import jp.co.suyama.menu.deliver.model.auto.QuestionsResponse;
import jp.co.suyama.menu.deliver.service.ArticleService;
import jp.co.suyama.menu.deliver.service.MenuService;

@RestController
public class SearchController implements SearchApi {

    // 献立サービス
    @Autowired
    private MenuService menuService;

    // 記事サービス
    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseEntity<ArticlesResponse> searchArticles(@Valid String keyword, @Valid Integer page) {
        // レスポンス作成
        ArticlesResponse response = new ArticlesResponse();

        // デフォルト値を設定
        keyword = keyword == null ? "" : keyword;
        page = page == null ? 1 : page;

        // キーワードを区切る
        // split少なくとも、空文字が1つ入ってしまうため、それを除く
        List<String> keywordList = new ArrayList<>();
        for (String k : keyword.split("\\s")) {
            if (StringUtils.isNotEmpty(k)) {
                keywordList.add(k);
            }
        }

        Map<String, Object> articleItems = new HashMap<>();
        // 記事を検索
        ArticlesAndPage articlesAndPage = articleService.searchArticles(keywordList, page);
        articleItems.put("articles", articlesAndPage.getArticleDataList());
        articleItems.put("page", articlesAndPage.getPage());

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(articleItems);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MenusResponse> searchMenus(@Valid String keyword, @Valid List<Integer> categories,
            @Valid Integer page) {

        // レスポンス作成
        MenusResponse response = new MenusResponse();

        // デフォルト値を設定
        keyword = keyword == null ? "" : keyword;
        categories = categories == null ? new ArrayList<>() : categories;
        page = page == null ? 1 : page;

        // キーワードを区切る
        // split少なくとも、空文字が1つ入ってしまうため、それを除く
        List<String> keywordList = new ArrayList<>();
        for (String k : keyword.split("\\s")) {
            if (StringUtils.isNotEmpty(k)) {
                keywordList.add(k);
            }
        }

        Map<String, Object> menuItems = new HashMap<>();
        // 献立を検索
        MenusAndPage menusAndPage = menuService.searchMenus(keywordList, categories, page);
        menuItems.put("menus", menusAndPage.getMenuDataList());
        menuItems.put("page", menusAndPage.getPage());

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(menuItems);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<QuestionsResponse> searchQuestions(@NotNull @Valid Integer pageNewArrival,
            @NotNull @Valid Integer pageUnsolved, @NotNull @Valid Integer pageSolved, @Valid List<Integer> tags,
            @Valid String keyword) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

}
