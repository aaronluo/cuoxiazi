package com.innovaee.eorder.mobile.databean;

import java.io.Serializable;

/**
 * 
 * @author wanglinglong
 * 
 */
public class TableInfoDataBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public final static String TABLE = "table"; 					// 表单名
	public final static String COLUM_ID = "tableId"; 				// 开台id
	public final static String COLUM_CELLPHONE = "cellphone"; 		// 会员电话号码
	public final static String COLUM_SERVANTID = "servantId"; 		// 员工工号
	public final static String COLUM_DISHPRICE = "dishPrice";		// 总价
	
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
