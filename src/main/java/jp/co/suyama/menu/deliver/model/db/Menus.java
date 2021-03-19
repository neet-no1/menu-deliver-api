package jp.co.suyama.menu.deliver.model.db;

import java.util.Date;

public class Menus {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menus.id
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menus.user_id
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menus.title
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menus.category_id
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    private Integer categoryId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menus.opened
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    private Boolean opened;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menus.created_at
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    private Date createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menus.updated_at
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    private Date updatedAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menus.id
     *
     * @return the value of menus.id
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menus.id
     *
     * @param id the value for menus.id
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menus.user_id
     *
     * @return the value of menus.user_id
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menus.user_id
     *
     * @param userId the value for menus.user_id
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menus.title
     *
     * @return the value of menus.title
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menus.title
     *
     * @param title the value for menus.title
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menus.category_id
     *
     * @return the value of menus.category_id
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menus.category_id
     *
     * @param categoryId the value for menus.category_id
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menus.opened
     *
     * @return the value of menus.opened
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public Boolean getOpened() {
        return opened;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menus.opened
     *
     * @param opened the value for menus.opened
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public void setOpened(Boolean opened) {
        this.opened = opened;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menus.created_at
     *
     * @return the value of menus.created_at
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menus.created_at
     *
     * @param createdAt the value for menus.created_at
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menus.updated_at
     *
     * @return the value of menus.updated_at
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menus.updated_at
     *
     * @param updatedAt the value for menus.updated_at
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}