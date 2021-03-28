package jp.co.suyama.menu.deliver.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.suyama.menu.deliver.mapper.NoticesMapperImpl;
import jp.co.suyama.menu.deliver.model.db.Notices;

@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeService {

    // お知らせテーブル
    @Autowired
    private NoticesMapperImpl noticesMapper;

    /**
     * お知らせ情報一覧を取得
     *
     * @return お知らせ情報一覧
     */
    public List<String> getNotices() {

        // お知らせ情報一覧を取得
        List<Notices> notices = noticesMapper.selectAllAvailable();

        return notices.stream().map(n -> n.getContents()).collect(Collectors.toList());
    }
}
