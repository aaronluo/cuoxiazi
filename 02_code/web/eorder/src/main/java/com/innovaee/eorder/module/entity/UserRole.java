package com.innovaee.eorder.module.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ROLE")
public class UserRole extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Override
	public Serializable getPK() {
		return userRoleName;
	}

	@Id
	@Column(name = "user_role_name")
	private String userRoleName;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "role_name")
	private String roleName;

	public UserRole() {
	}

	public UserRole(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	public UserRole(String userRoleName, String userName, String roleName) {
		super();
		this.userRoleName = userRoleName;
		this.userName = userName;
		this.roleName = roleName;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "UserRole [userRoleName=" + userRoleName + ", userName=" + userName + ", roleName=" + roleName + "]";
	}

}