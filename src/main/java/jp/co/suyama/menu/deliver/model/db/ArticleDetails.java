package jp.co.suyama.menu.deliver.model.db;

import java.util.Date;

public class ArticleDetails {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article_details.id
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article_details.article_id
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    private Integer articleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article_details.path
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    private String path;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article_details.created_at
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    private Date createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article_details.updated_at
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    private Date updatedAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_details.id
     *
     * @return the value of article_details.id
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_details.id
     *
     * @param id the value for article_details.id
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_details.article_id
     *
     * @return the value of article_details.article_id
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_details.article_id
     *
     * @param articleId the value for article_details.article_id
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_details.path
     *
     * @return the value of article_details.path
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public String getPath() {
        return path;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_details.path
     *
     * @param path the value for article_details.path
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_details.created_at
     *
     * @return the value of article_details.created_at
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_details.created_at
     *
     * @param createdAt the value for article_details.created_at
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article_details.updated_at
     *
     * @return the value of article_details.updated_at
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article_details.updated_at
     *
     * @param updatedAt the value for article_details.updated_at
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}