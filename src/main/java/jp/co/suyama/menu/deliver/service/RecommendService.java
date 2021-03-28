package jp.co.suyama.menu.deliver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.suyama.menu.deliver.mapper.RecommendArticlesMapperImpl;
import jp.co.suyama.menu.deliver.model.RecommendMetaData;
import jp.co.suyama.menu.deliver.model.db.RecommendArticles;
import jp.co.suyama.menu.deliver.utils.PathUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class RecommendService {

    // おすすめ記事テーブル
    @Autowired
    private RecommendArticlesMapperImpl recommendArticlesMapper;

    /**
     * おすすめ記事情報を表示するためのパラメタを取得
     * 
     * @return おすすめ記事情報表示パラメタ
     */
    public RecommendMetaData getRecommendMetaData() {

        // レスポンス
        RecommendMetaData result = new RecommendMetaData();

        // 情報取得
        RecommendArticles recommend = recommendArticlesMapper.selectByPrimaryKey(0);

        // 詰め替え
        result.setId(recommend.getId());
        result.setImgPath(PathUtils.getRecommendImagePath(recommend.getPath()));

        return result;
    }
}
