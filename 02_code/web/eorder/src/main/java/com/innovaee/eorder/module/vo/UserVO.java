/***********************************************
 * Filename        : UserVO.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.vo;

/**
 * @Title: UserVO
 * @Description: 用户值对象
 *
 * @version V1.0
 */
public class UserVO extends BaseVo {

	/** 用户ID */
	private Integer userId;

	/** 用户名称 */
	private String username;

	/** 角色名称 */
	private String roleName;

	/** 密码 */
	private String password;

	/** 手机号码 */
	private String cellphone;

	/** 等级名称 */
	private String levelName;

	/** 用户状态 */
	private Boolean userStatus;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
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

	public Boolean getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Boolean userStatus) {
		this.userStatus = userStatus;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", username=" + username
				+ ", roleName=" + roleName + ", password=" + password
				+ ", cellphone=" + cellphone + ", levelName=" + levelName
				+ ", userStatus=" + userStatus + "]";
	}
}