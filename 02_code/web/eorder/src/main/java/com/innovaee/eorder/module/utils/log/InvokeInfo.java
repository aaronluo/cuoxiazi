/***********************************************
 * Filename		: InvokeInfo.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.module.utils.log;

import java.util.Date;

/**   
* @Title: InvokeInfo 
* @Description: 调用信息 
* @author coderdream@gmail.com   
* @version V1.0   
*/
public class InvokeInfo {
	
	/**
	 * 调用者名称
	 */
	private String invokerName;
	
	/**
	 * 开始调用时间
	 */
	private Date date; 

	
	/**
	 * 嵌套等级
	 */
	private int level; 
	
	/**
	 * 是否在事务
	 */
	private boolean inTransaction;

	public String getInvokerName() {
		return invokerName;
	}

	public void setInvokerName(String invokerName) {
		this.invokerName = invokerName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isInTransaction() {
		return inTransaction;
	}

	public void setInTransaction(boolean inTransaction) {
		this.inTransaction = inTransaction;
	}
}