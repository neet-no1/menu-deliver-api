package jp.co.suyama.menu.deliver.model.db;

import java.util.Date;

public class Notices {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notices.id
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notices.contents
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    private String contents;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notices.expires
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    private Date expires;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notices.created_at
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    private Date createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notices.updated_at
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    private Date updatedAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notices.id
     *
     * @return the value of notices.id
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notices.id
     *
     * @param id the value for notices.id
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notices.contents
     *
     * @return the value of notices.contents
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public String getContents() {
        return contents;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notices.contents
     *
     * @param contents the value for notices.contents
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notices.expires
     *
     * @return the value of notices.expires
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public Date getExpires() {
        return expires;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notices.expires
     *
     * @param expires the value for notices.expires
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public void setExpires(Date expires) {
        this.expires = expires;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notices.created_at
     *
     * @return the value of notices.created_at
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notices.created_at
     *
     * @param createdAt the value for notices.created_at
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notices.updated_at
     *
     * @return the value of notices.updated_at
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notices.updated_at
     *
     * @param updatedAt the value for notices.updated_at
     *
     * @mbg.generated Fri Mar 19 19:40:52 JST 2021
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}