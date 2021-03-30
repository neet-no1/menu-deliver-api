package jp.co.suyama.menu.deliver.model;

import java.util.List;

import jp.co.suyama.menu.deliver.model.auto.PageNation;
import jp.co.suyama.menu.deliver.model.auto.QuestionData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionAndPage {

    private List<QuestionData> questionDataList;

    private PageNation page;
}
