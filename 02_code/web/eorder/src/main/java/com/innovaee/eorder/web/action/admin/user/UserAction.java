/***********************************************
 * Filename		: UserAction.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.web.action.admin.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.User;
import com.innovaee.eorder.module.service.UserRoleService;
import com.innovaee.eorder.module.service.UserService;
import com.innovaee.eorder.module.utils.MenuUtil;
import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.innovaee.eorder.module.vo.UserDetailsVo;
import com.innovaee.eorder.module.vo.UserVO;
import com.innovaee.eorder.web.action.BaseAction;

/**   
* @Title: UserAction 
* @Description: 用户Action（查找和删除）
* @author coderdream@gmail.com   
* @version V1.0   
*/
public class UserAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserAction.class);

	private List<RoleLinkVo> menulist = new ArrayList<RoleLinkVo>();

	private String userId;
	private String username;
	private String password;
	private String cellphone;
	private String[] userIds;

	private List<UserVO> uservos = new ArrayList<UserVO>();

	private String message = "";

	@Resource
	private UserService userService;

	@Resource
	private UserRoleService userRoleService;

	private List<Role> myRoles = new ArrayList<Role>();

	private List<Role> leftRoles = new ArrayList<Role>();

	private String myRolesArray;

	private String leftRolesArray;

	private String contextPath;

	private String operation;

	public String login() {
		logger.debug("enter login() method");

		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	public String doUser() {
		logger.debug("enter doUser() method");

		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	public String doLoad() {
		if (null != userId) {
			User user = userService.loadUser(Integer.parseInt(userId));
			username = user.getUsername();
			password = user.getPassword();
			cellphone = user.getCellphone();

			// 加载用户角色信息
			myRoles = userRoleService.findRolesByUserId(Integer
					.parseInt(userId));
			leftRoles = userRoleService.findLeftRolesByUserId(Integer
					.parseInt(userId));
		}

		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	public String doList() {
		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public void refreshData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		request.setAttribute("menulist", MenuUtil.getRoleLinkVOList());
		List<RoleLinkVo> sessionMenulist= (List<RoleLinkVo>)session.getAttribute("menulist");
		this.setMenulist(sessionMenulist);
		uservos = userService.findAllUserVOs();
		UserDetailsVo userDetail = (UserDetailsVo) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		this.setLoginName(userDetail.getUser().getUsername());
	}

	public String doRemove() {
		if (null != userId) {
			userService.removeUser(Integer.parseInt(userId));
		} 

		this.setMessage("删除成功！");
		
		// 清空删除时传入的Id，防止返回后页面取到
		this.setUserId("");
		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	public String doUserInfo() {
		logger.debug("enter doUserInfo() method");

		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	public String doRight() {
		logger.debug("enter doRight() method");
		return SUCCESS;
	}

	public String doBottom() {
		logger.debug("enter doBottom() method");
		return SUCCESS;
	}

	public List<RoleLinkVo> getMenulist() {
		return menulist;
	}

	public void setMenulist(List<RoleLinkVo> menulist) {
		this.menulist = menulist;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public List<UserVO> getUservos() {
		return uservos;
	}

	public void setUservos(List<UserVO> uservos) {
		this.uservos = uservos;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public List<Role> getMyRoles() {
		return myRoles;
	}

	public void setMyRoles(List<Role> myRoles) {
		this.myRoles = myRoles;
	}

	public List<Role> getLeftRoles() {
		return leftRoles;
	}

	public void setLeftRoles(List<Role> leftRoles) {
		this.leftRoles = leftRoles;
	}

	public String getMyRolesArray() {
		return myRolesArray;
	}

	public void setMyRolesArray(String myRolesArray) {
		this.myRolesArray = myRolesArray;
	}

	public String getLeftRolesArray() {
		return leftRolesArray;
	}

	public void setLeftRolesArray(String leftRolesArray) {
		this.leftRolesArray = leftRolesArray;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}