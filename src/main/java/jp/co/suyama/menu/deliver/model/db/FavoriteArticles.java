package jp.co.suyama.menu.deliver.model.db;

import java.util.Date;

public class FavoriteArticles {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column favorite_articles.id
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column favorite_articles.user_id
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	private Integer userId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column favorite_articles.article_id
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	private Integer articleId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column favorite_articles.created_at
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	private Date createdAt;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column favorite_articles.updated_at
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	private Date updatedAt;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column favorite_articles.id
	 * @return  the value of favorite_articles.id
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column favorite_articles.id
	 * @param id  the value for favorite_articles.id
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column favorite_articles.user_id
	 * @return  the value of favorite_articles.user_id
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column favorite_articles.user_id
	 * @param userId  the value for favorite_articles.user_id
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column favorite_articles.article_id
	 * @return  the value of favorite_articles.article_id
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	public Integer getArticleId() {
		return articleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column favorite_articles.article_id
	 * @param articleId  the value for favorite_articles.article_id
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column favorite_articles.created_at
	 * @return  the value of favorite_articles.created_at
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column favorite_articles.created_at
	 * @param createdAt  the value for favorite_articles.created_at
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column favorite_articles.updated_at
	 * @return  the value of favorite_articles.updated_at
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column favorite_articles.updated_at
	 * @param updatedAt  the value for favorite_articles.updated_at
	 * @mbg.generated  Fri Mar 19 20:01:37 JST 2021
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}