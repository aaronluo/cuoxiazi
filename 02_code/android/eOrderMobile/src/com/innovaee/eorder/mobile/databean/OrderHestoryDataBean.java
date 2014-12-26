/***********************************************
 * Filename		: OrderHestoryDataBean.java																								
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
 ************************************************/
package com.innovaee.eorder.mobile.databean;

import java.io.Serializable;

/**
 * 订单历史详情信息数据Bean
 * @author wanglinglong
 * 
 */	
public class OrderHestoryDataBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// 表单名
	public final static String TABLE = "orderhestory"; 	
	
	// id
	public final static String COLUM_ID = "id"; 
	
	// 时间
	public final static String COLUM_TIME = "time"; 
	
	// 订单总价格
	public final static String COLUM_TOTALPRICE = "totalprice"; 	
			
	private int id;	

	private String time;

	private Double totalPrice;
	
	public OrderHestoryDataBean() {
	}

	public OrderHestoryDataBean(int id, String time, Double totalPrice) {
		this.id = id;	
		this.time = time;
		this.totalPrice = totalPrice;			
	}		

	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}			
}
