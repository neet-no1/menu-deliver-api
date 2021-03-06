package jp.co.suyama.menu.deliver.controller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import jp.co.suyama.menu.deliver.model.BestAnswerIsExist;
import jp.co.suyama.menu.deliver.model.auto.AnswerData;
import jp.co.suyama.menu.deliver.model.auto.AnswersResponse;
import jp.co.suyama.menu.deliver.model.auto.BasicResponse;
import jp.co.suyama.menu.deliver.model.auto.BestAnswerResponse;
import jp.co.suyama.menu.deliver.model.auto.DecideBestAnswerParam;
import jp.co.suyama.menu.deliver.model.auto.ErrorInfo;
import jp.co.suyama.menu.deliver.model.auto.QuestionCategoriesResponse;
import jp.co.suyama.menu.deliver.model.auto.QuestionData;
import jp.co.suyama.menu.deliver.model.auto.QuestionDataResponse;
import jp.co.suyama.menu.deliver.service.QuestionService;

@RestController
public class QuestionController implements QuestionApi {

    // 質問サービス
    @Autowired
    private QuestionService questionService;

    @Override
    public ResponseEntity<BasicResponse> answerQuestion(Integer id, String contents, @Valid MultipartFile file) {

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

        // 質問IDの存在チェック
        if (id == null) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("質問IDが空です。");
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

        // 回答投稿
        questionService.postAnswer(auth.getPrincipal().toString(), id, contents, file);

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(MenuDeliverStatus.SUCCESS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BasicResponse> decideBestAnswer(@Valid DecideBestAnswerParam decideBestAnswerParam) {

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

        // 質問IDの存在チェック
        if (decideBestAnswerParam.getQuestionId() == null) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("質問IDが空です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // 回答IDの存在チェック
        if (decideBestAnswerParam.getAnswerId() == null) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("回答IDが空です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // ベストアンサー決定
        questionService.decideBestAnswer(auth.getPrincipal().toString(), decideBestAnswerParam.getQuestionId(),
                decideBestAnswerParam.getAnswerId());

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(MenuDeliverStatus.SUCCESS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AnswersResponse> getAnswers(@NotNull @Valid Integer id) {

        // レスポンス作成
        AnswersResponse response = new AnswersResponse();

        // 質問IDの存在チェック
        if (id == null) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("質問IDが空です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        Map<String, Object> answerItems = new HashMap<>();
        // 回答一覧取得
        List<AnswerData> answerDataList = questionService.getAnswers(id);
        answerItems.put("answers", answerDataList);

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(answerItems);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BestAnswerResponse> getBestAnswer(@NotNull @Valid Integer id) {

        // レスポンス作成
        BestAnswerResponse response = new BestAnswerResponse();

        // 質問IDの存在チェック
        if (id == null) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("質問IDが空です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        Map<String, Object> bestAnswerItems = new HashMap<>();
        // ベストアンサーを取得
        BestAnswerIsExist bestAnswer = questionService.getBestAnswer(id);
        bestAnswerItems.put("exist", bestAnswer.isExist());
        bestAnswerItems.put("id", bestAnswer.getId());

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(bestAnswerItems);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<QuestionDataResponse> getQuestion(@NotNull @Valid Integer id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // レスポンス作成
        QuestionDataResponse response = new QuestionDataResponse();

        // 質問IDの存在チェック
        if (id == null) {
            response.setCode(MenuDeliverStatus.FAILED);
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage("質問IDが空です。");
            response.setErrorInfo(error);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // 質問投稿
        QuestionData questionData = questionService.getQuestion(auth.getPrincipal().toString(), id);

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(questionData);

        return new ResponseEntity<>(response, HttpStatus.OK);
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
