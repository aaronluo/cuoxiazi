package com.innovaee.eorder.module.vo;

import java.sql.Timestamp;

public class UserVO extends BaseVo {

	private Integer userId;

	private String username;

	private String roleName;

	private String password;

	private String cellphone;

	private String levelName;

	private Boolean userStatus;

	private Timestamp createAt;

	private Timestamp updateAt;

	public UserVO() {
	}

	public UserVO(String username, String password, String cellphone,
			Integer levelId, Boolean userStatus, Timestamp createAt) {
		super();
		this.username = username;
		this.password = password;
		this.cellphone = cellphone;
		this.userStatus = userStatus;
		this.createAt = createAt;
	}

	public UserVO(Integer userId) {
		this.userId = userId;
	}

	public UserVO(String username) {
		this.username = username;
	}

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

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
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
				+ ", userStatus=" + userStatus + ", createAt=" + createAt
				+ ", updateAt=" + updateAt + "]";
	}

}