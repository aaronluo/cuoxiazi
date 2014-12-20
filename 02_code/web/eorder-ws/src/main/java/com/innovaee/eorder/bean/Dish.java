/***********************************************
 * Filename		: Dish.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.innovaee.eorder.util.TimestampAdapter;

/**   
* @Title: Dish 
* @Description: 菜品实体 
* @author coderdream@gmail.com   
* @version V1.0   
*/
@Entity
@Table(name = "t_dish")
@XmlRootElement
public class Dish extends BaseEntity {

	@Override
	public Serializable getPK() {
		return dishId;
	}

	// 菜品id, 不能为空, 必须唯一
	@Id
	@Column(name = "dish_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer dishId;

	// 分类ID
	@Column(name = "category_id")
	private Integer categoryId;

	// 菜品名称
	@Column(name = "dish_name")
	private String dishName;

	// 菜品图片地址
	@Column(name = "dish_picture")
	private String dishPicture;

	// 菜品价格
	@Column(name = "dish_price")
	private float dishPrice;

	// 在售状态
	@Column(name = "on_sell")
	private boolean onSell;

	// 其他信息
	@Column(name = "misc")
	private String misc;

	// 创建时间
	@Column(name = "create_at")
	private Timestamp createAt;

	// 更新时间
	@Column(name = "update_at")
	private Timestamp updateAt;

	public Dish() {
	}

	public Dish(String dishName) {
		this.dishName = dishName;
	}

	public Dish(Integer dishId) {
		this.setDishId(dishId);
	}

	public Dish(Integer dishId, String dishName) {
		this.setDishId(dishId);
		this.dishName = dishName;
	}

	@Override
	public int hashCode() {
		return this.getDishId();
	}

	public Dish(Integer dishId, String dishName, String dishPicture,
			Timestamp createAt, Timestamp updateAt) {
		super();
		this.dishId = dishId;
		this.dishName = dishName;
		this.dishPicture = dishPicture;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	public Dish(Integer dishId, Integer categoryId, String dishName,
			String dishPicture, float dishPrice, boolean onSell, String misc,
			Timestamp createAt, Timestamp updateAt) {
		super();
		this.dishId = dishId;
		this.categoryId = categoryId;
		this.dishName = dishName;
		this.dishPicture = dishPicture;
		this.dishPrice = dishPrice;
		this.onSell = onSell;
		this.misc = misc;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Dish))
			return false;
		final Dish other = (Dish) object;
		return this.dishId == other.dishId;
	}

	public Integer getDishId() {
		return dishId;
	}

	public void setDishId(Integer dishId) {
		this.dishId = dishId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getDishPicture() {
		return dishPicture;
	}

	public void setDishPicture(String dishPicture) {
		this.dishPicture = dishPicture;
	}

	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public float getDishPrice() {
		return dishPrice;
	}

	public void setDishPrice(float dishPrice) {
		this.dishPrice = dishPrice;
	}

	public boolean isOnSell() {
		return onSell;
	}

	public void setOnSell(boolean onSell) {
		this.onSell = onSell;
	}

	public String getMisc() {
		return misc;
	}

	public void setMisc(String misc) {
		this.misc = misc;
	}

	@Override
	public String toString() {
		return "Dish [dishId=" + dishId + ", categoryId=" + categoryId
				+ ", dishName=" + dishName + ", dishPicture=" + dishPicture
				+ ", dishPrice=" + dishPrice + ", onSell=" + onSell + ", misc=" + misc
				+ ", createAt=" + createAt + ", updateAt=" + updateAt + "]";
	}

}