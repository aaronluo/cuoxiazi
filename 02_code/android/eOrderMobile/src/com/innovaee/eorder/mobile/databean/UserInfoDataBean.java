package com.innovaee.eorder.mobile.databean;

import java.io.Serializable;

/**
 * 
 * @author wanglinglong
 * 
 */
public class UserInfoDataBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public final static String TABLE = "user"; 					// 表单名
	public final static String COLUM_ID = "userId"; 			// 会员id
	public final static String COLUM_NAME = "userName"; 		// 会员名字
	public final static String COLUM_PRICE = "cellphone"; 		// 会员电话
	public final static String COLUM_BITMAPURL = "levelName";	// 会员等级名称
	public final static String COLUM_COUNT = "discount";   		// 会员等级折扣
	
	private int userId;

	private String userName;
	
	private String cellphone;
	
	private String levelName;
	
	private Double discount;
	
	public UserInfoDataBean() {
	}

	public UserInfoDataBean(int userId, String userName, String cellphone, String levelName, Double discount) {
		this.userId = userId;	
		this.userName = userName;
		this.cellphone = cellphone;
		this.levelName = levelName;
		this.discount = discount;
	}	
	
	public int getId() {
		return this.userId;
	}

	public void setId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.userName;
	}

	public void setName(String userName) {
		this.userName = userName;
	}

	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}	
	
	public String getLevelName() {
		return this.levelName;
	}
	
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	public Double getDiscount() {
		return this.discount;
	}
	
	public void setCount(Double discount) {
		this.discount = discount;
	}			
}
