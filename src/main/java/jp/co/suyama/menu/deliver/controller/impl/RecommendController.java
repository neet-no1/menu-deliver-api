package jp.co.suyama.menu.deliver.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jp.co.suyama.menu.deliver.common.MenuDeliverStatus;
import jp.co.suyama.menu.deliver.controller.interfaces.RecommendApi;
import jp.co.suyama.menu.deliver.model.RecommendMetaData;
import jp.co.suyama.menu.deliver.model.auto.RecommendMetaDataResponse;
import jp.co.suyama.menu.deliver.service.RecommendService;

@RestController
public class RecommendController implements RecommendApi {

    // おすすめ記事サービス
    @Autowired
    private RecommendService recommendService;

    @Override
    public ResponseEntity<RecommendMetaDataResponse> getRecommendMeta() {

        // レスポンス作成
        RecommendMetaDataResponse response = new RecommendMetaDataResponse();

        // おすすめ情報を取得
        RecommendMetaData recommendMetaData = recommendService.getRecommendMetaData();

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(recommendMetaData);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
