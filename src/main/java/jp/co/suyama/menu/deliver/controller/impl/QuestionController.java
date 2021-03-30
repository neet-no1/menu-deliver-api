package jp.co.suyama.menu.deliver.controller.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jp.co.suyama.menu.deliver.common.MenuDeliverConstants;
import jp.co.suyama.menu.deliver.common.MenuDeliverStatus;
import jp.co.suyama.menu.deliver.controller.interfaces.QuestionApi;
import jp.co.suyama.menu.deliver.model.auto.AnswersResponse;
import jp.co.suyama.menu.deliver.model.auto.BasicResponse;
import jp.co.suyama.menu.deliver.model.auto.BestAnswerResponse;
import jp.co.suyama.menu.deliver.model.auto.DecideBestAnswerParam;
import jp.co.suyama.menu.deliver.model.auto.ErrorInfo;
import jp.co.suyama.menu.deliver.model.auto.QuestionCategoriesResponse;
import jp.co.suyama.menu.deliver.model.auto.QuestionDataResponse;
import jp.co.suyama.menu.deliver.service.QuestionService;

@RestController
public class QuestionController implements QuestionApi {

    // 質問サービス
    @Autowired
    private QuestionService questionService;

    @Override
    public ResponseEntity<BasicResponse> answerQuestion(String contents, @Valid MultipartFile file) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public ResponseEntity<BasicResponse> decideBestAnswer(@Valid DecideBestAnswerParam decideBestAnswerParam) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public ResponseEntity<AnswersResponse> getAnswers(@NotNull @Valid String id) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public ResponseEntity<BestAnswerResponse> getBestAnswer(@NotNull @Valid String id) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public ResponseEntity<QuestionDataResponse> getQuestion(@NotNull @Valid String id) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public ResponseEntity<QuestionCategoriesResponse> getQuestionCategories() {

        // レスポンス作成
        QuestionCategoriesResponse response = new QuestionCategoriesResponse();

        // 質問投稿
        List<Object> categories = new ArrayList<>(questionService.getCategories());

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(categories);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BasicResponse> postQuestion(String contents, @Valid MultipartFile file) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // レスポンス作成
        BasicResponse response = new BasicResponse();

        // ログインチェック
        if (MenuDeliverConstants.UNKNOWN_USER_NAME.equals(auth.getPrincipal().toString())) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("ログインされていません。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // 内容の存在チェック
        if (contents == null) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("質問内容が空です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // 質問投稿
        questionService.postQuestion(auth.getPrincipal().toString(), contents, file);

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(MenuDeliverStatus.SUCCESS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
