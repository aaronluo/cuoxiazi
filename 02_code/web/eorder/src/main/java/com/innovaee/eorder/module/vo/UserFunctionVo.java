/***********************************************
 * Filename		: UserFunctionVo.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.module.vo;

import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.User;

/**   
* @Title: UserFunctionVo 
* @Description: 用户功能值对象 
* @author coderdream@gmail.com   
* @version V1.0   
*/
public class UserFunctionVo extends BaseVo {

	private static final long serialVersionUID = 1L;

	private User user;
	private Role role;
	private Function function;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	@Override
	public String toString() {
		return "UserFunctionVo [user=" + user + ", role=" + role
				+ ", function=" + function + "]";
	}

}