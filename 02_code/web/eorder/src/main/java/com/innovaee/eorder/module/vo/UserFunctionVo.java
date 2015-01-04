/***********************************************
 * Filename        : UserFunctionVo.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.vo;

import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.User;

/**
 * @Title: UserFunctionVo
 * @Description: 用户功能值对象
 *
 * @version V1.0
 */
public class UserFunctionVo extends BaseVo {

	/** 用户实体 */
	private User user;

	/** 角色实体 */
	private Role role;

	/** 功能实体 */
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