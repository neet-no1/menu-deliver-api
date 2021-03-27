package jp.co.suyama.menu.deliver.model;

import java.util.List;

import jp.co.suyama.menu.deliver.model.auto.ArticleData;
import jp.co.suyama.menu.deliver.model.auto.PageNation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticlesAndPage {

    private List<ArticleData> articleDataList;

    private PageNation page;
}
