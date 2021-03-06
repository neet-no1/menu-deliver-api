package jp.co.suyama.menu.deliver.model.db;

import java.util.Date;

public class MenuPictures {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column menu_pictures.id
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column menu_pictures.menu_id
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	private Integer menuId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column menu_pictures.path
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	private String path;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column menu_pictures.description
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column menu_pictures.order_of
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	private Integer orderOf;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column menu_pictures.created_at
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	private Date createdAt;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column menu_pictures.updated_at
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	private Date updatedAt;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column menu_pictures.id
	 * @return  the value of menu_pictures.id
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column menu_pictures.id
	 * @param id  the value for menu_pictures.id
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column menu_pictures.menu_id
	 * @return  the value of menu_pictures.menu_id
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	public Integer getMenuId() {
		return menuId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column menu_pictures.menu_id
	 * @param menuId  the value for menu_pictures.menu_id
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column menu_pictures.path
	 * @return  the value of menu_pictures.path
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	public String getPath() {
		return path;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column menu_pictures.path
	 * @param path  the value for menu_pictures.path
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column menu_pictures.description
	 * @return  the value of menu_pictures.description
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column menu_pictures.description
	 * @param description  the value for menu_pictures.description
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column menu_pictures.order_of
	 * @return  the value of menu_pictures.order_of
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	public Integer getOrderOf() {
		return orderOf;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column menu_pictures.order_of
	 * @param orderOf  the value for menu_pictures.order_of
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	public void setOrderOf(Integer orderOf) {
		this.orderOf = orderOf;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column menu_pictures.created_at
	 * @return  the value of menu_pictures.created_at
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column menu_pictures.created_at
	 * @param createdAt  the value for menu_pictures.created_at
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column menu_pictures.updated_at
	 * @return  the value of menu_pictures.updated_at
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column menu_pictures.updated_at
	 * @param updatedAt  the value for menu_pictures.updated_at
	 * @mbg.generated  Sat Mar 27 15:50:13 JST 2021
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}