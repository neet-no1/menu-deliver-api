package jp.co.suyama.menu.deliver.controller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jp.co.suyama.menu.deliver.common.MenuDeliverStatus;
import jp.co.suyama.menu.deliver.controller.interfaces.SearchApi;
import jp.co.suyama.menu.deliver.model.ArticlesAndPage;
import jp.co.suyama.menu.deliver.model.MenusAndPage;
import jp.co.suyama.menu.deliver.model.QuestionAndPage;
import jp.co.suyama.menu.deliver.model.auto.ArticlesResponse;
import jp.co.suyama.menu.deliver.model.auto.MenusResponse;
import jp.co.suyama.menu.deliver.model.auto.QuestionsResponse;
import jp.co.suyama.menu.deliver.service.ArticleService;
import jp.co.suyama.menu.deliver.service.MenuService;
import jp.co.suyama.menu.deliver.service.QuestionService;
import jp.co.suyama.menu.deliver.utils.PageNationUtils;

@RestController
public class SearchController implements SearchApi {

    // ロガー
    private Logger log = LoggerFactory.getLogger(SearchController.class);

    // 献立サービス
    @Autowired
    private MenuService menuService;

    // 記事サービス
    @Autowired
    private ArticleService articleService;

    // 質問サービス
    @Autowired
    private QuestionService questionService;

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

        // ログ
        log.info("献立検索 keyword: {}, categories: {}, page: {}", keyword, categories, page);

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

        // レスポンス作成
        QuestionsResponse response = new QuestionsResponse();

        // デフォルト値を設定
        keyword = keyword == null ? "" : keyword;
        tags = tags == null ? new ArrayList<>() : tags;
        pageNewArrival = pageNewArrival == null ? 1 : pageNewArrival;
        pageUnsolved = pageUnsolved == null ? 1 : pageUnsolved;
        pageSolved = pageSolved == null ? 1 : pageSolved;

        // キーワードを区切る
        // split少なくとも、空文字が1つ入ってしまうため、それを除く
        List<String> keywordList = new ArrayList<>();
        for (String k : keyword.split("\\s")) {
            if (StringUtils.isNotEmpty(k)) {
                keywordList.add(k);
            }
        }

        Map<String, Object> questionItems = new HashMap<>();
        // 質問を検索
        QuestionAndPage questionAndPage = null;
        // 新着質問はカテゴリが指定されていない時のみ取得する
        if (tags.isEmpty()) {
            questionAndPage = questionService.searchQuestionNewArrival(keywordList, pageNewArrival);
            questionItems.put("questionsNewArrival", questionAndPage.getQuestionDataList());
            questionItems.put("pageNewArrival", questionAndPage.getPage());
        } else {
            questionItems.put("questionsNewArrival", new ArrayList<>());
            questionItems.put("pageNewArrival", PageNationUtils.createPageNation(1, 1, 1));
        }

        if (tags.isEmpty() || tags.contains(1)) {
            questionAndPage = questionService.searchQuestion(keywordList, pageUnsolved, false);
            questionItems.put("questionsUnsolved", questionAndPage.getQuestionDataList());
            questionItems.put("pageUnsolved", questionAndPage.getPage());
        } else {
            questionItems.put("questionsUnsolved", new ArrayList<>());
            questionItems.put("pageUnsolved", PageNationUtils.createPageNation(1, 1, 1));
        }

        if (tags.isEmpty() || tags.contains(2)) {
            questionAndPage = questionService.searchQuestion(keywordList, pageSolved, true);
            questionItems.put("questionsSolved", questionAndPage.getQuestionDataList());
            questionItems.put("pageSolved", questionAndPage.getPage());
        } else {
            questionItems.put("questionsSolved", new ArrayList<>());
            questionItems.put("pageSolved", PageNationUtils.createPageNation(1, 1, 1));
        }

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(questionItems);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
