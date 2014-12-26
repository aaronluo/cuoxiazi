/***********************************************
 * Filename		: BaseAction.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.web.action;

import com.opensymphony.xwork2.ActionSupport;

/**   
* @Title: BaseAction 
* @Description: Action基类
* @author coderdream@gmail.com   
* @version V1.0   
*/
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String loginName;

	private String message;
	/**
	 * Action Result Value
	 */
	private String resultValue;

	public String getResultValue() {
		return resultValue;
	}

	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}