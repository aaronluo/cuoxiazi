/***********************************************
 * Filename        : BaseAction.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.web.action;

import java.util.ArrayList;
import java.util.List;

import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @Title: BaseAction
 * @Description: Action基类
 *
 * @version V1.0
 */
public class BaseAction extends ActionSupport {

	protected List<RoleLinkVo> menulist = new ArrayList<RoleLinkVo>();

	private String loginName;

	private String message;

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

	public List<RoleLinkVo> getMenulist() {
		return menulist;
	}

	public void setMenulist(List<RoleLinkVo> menulist) {
		this.menulist = menulist;
	}

}