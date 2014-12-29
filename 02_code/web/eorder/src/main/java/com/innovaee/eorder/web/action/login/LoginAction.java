/***********************************************
 * Filename		: LoginAction.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.web.action.login;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import com.innovaee.eorder.module.utils.MenuUtil;
import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.innovaee.eorder.module.vo.UserDetailsVo;
import com.innovaee.eorder.web.action.BaseAction;

/**
 * @Title: LoginAction
 * @Description: 登录Action
 * @author coderdream@gmail.com
 * @version V1.0
 */
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LoginAction.class);

	private List<RoleLinkVo> menulist = new ArrayList<RoleLinkVo>();

	private String username;

	public String login() {
		return SUCCESS;
	}

	public String doLogin() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		request.setAttribute("menulist", MenuUtil.getRoleLinkVOList());
		session.setAttribute("menulist", MenuUtil.getRoleLinkVOList());

		return SUCCESS;
	}

	public String doLogout() {
		return SUCCESS;
	}

	public String doHeader() {
		UserDetailsVo userDetail = (UserDetailsVo) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		Assert.notNull(userDetail);

		username = userDetail.getUser().getUsername();

		return SUCCESS;
	}

	public String doUserInfo() {
		return SUCCESS;
	}

	public String doRight() {
		return SUCCESS;
	}

	public String doBottom() {
		return SUCCESS;
	}

	public List<RoleLinkVo> getMenulist() {
		return menulist;
	}

	public void setMenulist(List<RoleLinkVo> menulist) {
		this.menulist = menulist;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}