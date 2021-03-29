package jp.co.suyama.menu.deliver.model;

import java.util.List;

import jp.co.suyama.menu.deliver.model.auto.FollowUserData;
import jp.co.suyama.menu.deliver.model.auto.PageNation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowersAndPage {

    private List<FollowUserData> followerList;

    private PageNation page;
}
