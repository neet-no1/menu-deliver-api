package jp.co.suyama.menu.deliver.model.db;

import java.util.Date;

public class ArticleViews {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column article_views.article_id
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	private Integer articleId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column article_views.date
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	private Date date;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column article_views.count
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	private Integer count;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column article_views.article_id
	 * @return  the value of article_views.article_id
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	public Integer getArticleId() {
		return articleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column article_views.article_id
	 * @param articleId  the value for article_views.article_id
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column article_views.date
	 * @return  the value of article_views.date
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column article_views.date
	 * @param date  the value for article_views.date
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column article_views.count
	 * @return  the value of article_views.count
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column article_views.count
	 * @param count  the value for article_views.count
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
}