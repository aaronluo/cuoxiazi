package com.innovaee.eorder.mobile.databean;

import java.io.Serializable;

/**
 * 
 * @author wanglinglong
 * 
 */	
public class OrderInfoDataBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public final static String TABLE = "orderitems"; 				// 表单名
	public final static String COLUM_DISHID = "dishId"; 			// id
	public final static String COLUM_DISHNAME = "dishName"; 		// 名字
	public final static String COLUM_DISHPRICE = "dishPrice"; 		// 商品价格
	public final static String COLUM_DISHAMOUNT = "dishAmount"; 	// 商品数量
	public final static String COLUM_DISHPICTURE = "dishPicture"; 	// 商品图片url地址
						
	private int dishId;	
	
	private String dishName;

	private Double dishPrice;
	
	private int dishAmount;	

	private String dishPicture;
		
	public OrderInfoDataBean() {
	}	

	public OrderInfoDataBean(int dishId, String dishName, Double dishPrice, int dishAmount, String dishPicture) {
		this.dishId = dishId;	
		this.dishName = dishName;
		this.dishPrice = dishPrice;
		this.dishAmount = dishAmount;
		this.dishPicture = dishPicture;
	}			

	public int getId() {
		return this.dishId;
	}
	
	public void setId(int dishId) {
		this.dishId = dishId;
	}

	public String getDishName() {
		return this.dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	
	public Double getDishPrice() {
		return this.dishPrice;
	}
	
	public void setDishPrice(Double dishPrice) {
		this.dishPrice = dishPrice;
	}	
	
	public int getDishAmount() {
		return this.dishAmount;
	}
	
	public void setDishAmount(int dishAmount) {
		this.dishAmount = dishAmount;
	}

	public String getDishPicture() {
		return this.dishPicture;
	}
		
	public void setDishPicture(String dishPicture) {
		this.dishPicture = dishPicture;
	}	
}
