/***********************************************
 * Filename		: TableInfoDataBean.java																							
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
 ************************************************/
package com.innovaee.eorder.mobile.databean;

import java.io.Serializable;

/**
 * 提交订单开台信息数据Bean
 * @author wanglinglong
 * 
 */
public class TableInfoDataBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// 表单名
	public final static String TABLE = "table"; 	
	
	// 开台id
	public final static String COLUM_ID = "tableId"; 	
	
	// 会员电话号码
	public final static String COLUM_CELLPHONE = "cellphone"; 	
	
	// 员工工号
	public final static String COLUM_SERVANTID = "servantId"; 
	
	// 总价
	public final static String COLUM_DISHPRICE = "dishPrice";		
	
	private int tableId;

	private String cellphone;
	
	private String servantId;
	
	private Double dishPrice;
			
	public TableInfoDataBean() {
	}
	
	public TableInfoDataBean(int tableId, String cellphone, String servantId, Double dishPrice) {
		this.tableId = tableId;	
		this.cellphone = cellphone;
		this.servantId = servantId;
		this.dishPrice = dishPrice;	
	}	
	
	public int getId() {
		return this.tableId;
	}

	public void setId(int tableId) {
		this.tableId = tableId;
	}

	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}	

	public String getServantId() {
		return this.servantId;
	}
	
	public void setServantId(String servantId) {
		this.servantId = servantId;
	}	
			
	public Double getDishPrice() {
		return this.dishPrice;
	}
			
	public void setDishPrice(Double dishPrice) {
		this.dishPrice = dishPrice;
	}				
}
