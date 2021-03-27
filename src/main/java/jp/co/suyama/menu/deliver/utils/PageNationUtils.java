package jp.co.suyama.menu.deliver.utils;

import jp.co.suyama.menu.deliver.model.auto.PageNation;

public class PageNationUtils {

    public static PageNation createPageNation(int page, int count, int limit) {

        // 総ページを計算する
        int totalPage = count % limit == 0 ? count / limit : count / limit + 1;
        totalPage = totalPage < 1 ? 1 : totalPage;

        // 取得ページ番号が異常な場合、正常な値に設定する
        if (page < 1) {
            page = 1;
        } else if (totalPage < page) {
            page = totalPage;
        }

        // ページネーション情報を設定する
        PageNation pageNation = new PageNation();
        pageNation.setTotalPages(totalPage);
        pageNation.setCurrentPage(page);

        return pageNation;
    }
}
