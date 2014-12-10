package com.innovaee.eorder.mobile.databean;

import java.io.Serializable;

/**
 * 
 * @author wanglinglong
 * 
 */	
public class OrderHestoryDataBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public final static String TABLE = "orderhestory"; 				// 表单名
	public final static String COLUM_ID = "id"; 					// id
	public final static String COLUM_TIME = "time"; 				// 时间
	public final static String COLUM_TOTALPRICE = "totalprice"; 	// 订单总价格
			
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
