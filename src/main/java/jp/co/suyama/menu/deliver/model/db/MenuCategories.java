package jp.co.suyama.menu.deliver.model.db;

import java.util.Date;

public class MenuCategories {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column menu_categories.id
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column menu_categories.name
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column menu_categories.created_at
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	private Date createdAt;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column menu_categories.updated_at
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	private Date updatedAt;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column menu_categories.id
	 * @return  the value of menu_categories.id
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column menu_categories.id
	 * @param id  the value for menu_categories.id
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column menu_categories.name
	 * @return  the value of menu_categories.name
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column menu_categories.name
	 * @param name  the value for menu_categories.name
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column menu_categories.created_at
	 * @return  the value of menu_categories.created_at
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column menu_categories.created_at
	 * @param createdAt  the value for menu_categories.created_at
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column menu_categories.updated_at
	 * @return  the value of menu_categories.updated_at
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column menu_categories.updated_at
	 * @param updatedAt  the value for menu_categories.updated_at
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}