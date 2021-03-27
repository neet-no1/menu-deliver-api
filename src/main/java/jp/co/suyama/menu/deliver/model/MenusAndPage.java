package jp.co.suyama.menu.deliver.model;

import java.util.List;

import jp.co.suyama.menu.deliver.model.auto.MenuData;
import jp.co.suyama.menu.deliver.model.auto.PageNation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenusAndPage {

    private List<MenuData> menuDataList;

    private PageNation page;
}
