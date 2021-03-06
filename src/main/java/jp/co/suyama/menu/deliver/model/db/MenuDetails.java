package jp.co.suyama.menu.deliver.model.db;

import java.util.Date;

public class MenuDetails {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column menu_details.id
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column menu_details.menu_id
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	private Integer menuId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column menu_details.path
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	private String path;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column menu_details.created_at
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	private Date createdAt;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column menu_details.updated_at
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	private Date updatedAt;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column menu_details.id
	 * @return  the value of menu_details.id
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column menu_details.id
	 * @param id  the value for menu_details.id
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column menu_details.menu_id
	 * @return  the value of menu_details.menu_id
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	public Integer getMenuId() {
		return menuId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column menu_details.menu_id
	 * @param menuId  the value for menu_details.menu_id
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column menu_details.path
	 * @return  the value of menu_details.path
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	public String getPath() {
		return path;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column menu_details.path
	 * @param path  the value for menu_details.path
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column menu_details.created_at
	 * @return  the value of menu_details.created_at
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column menu_details.created_at
	 * @param createdAt  the value for menu_details.created_at
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column menu_details.updated_at
	 * @return  the value of menu_details.updated_at
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column menu_details.updated_at
	 * @param updatedAt  the value for menu_details.updated_at
	 * @mbg.generated  Wed Mar 24 14:49:18 JST 2021
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}