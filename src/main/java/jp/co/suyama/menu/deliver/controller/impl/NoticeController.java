package jp.co.suyama.menu.deliver.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jp.co.suyama.menu.deliver.common.MenuDeliverStatus;
import jp.co.suyama.menu.deliver.controller.interfaces.NoticeApi;
import jp.co.suyama.menu.deliver.model.auto.NoticesResponse;
import jp.co.suyama.menu.deliver.service.NoticeService;

@RestController
public class NoticeController implements NoticeApi {

    // お知らせサービス
    @Autowired
    private NoticeService noticeService;

    @Override
    public ResponseEntity<NoticesResponse> getNotices() {

        // レスポンス作成
        NoticesResponse response = new NoticesResponse();

        // お知らせ情報取得
        List<String> notices = noticeService.getNotices();

        // レスポンスに情報を設定
        response.setCode(MenuDeliverStatus.SUCCESS);
        response.setInfo(notices);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
