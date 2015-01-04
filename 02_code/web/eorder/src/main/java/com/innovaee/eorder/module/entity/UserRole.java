/***********************************************
 * Filename        : UserRole.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Title: UserRole
 * @Description: 用户角色
 *
 * @version V1.0
 */
@Entity
@Table(name = "t_user_role")
public class UserRole extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Override
	public Serializable getPK() {
		return userRoleId;
	}

	/**
	 * 用户角色ID
	 */
	@Id
	@Column(name = "USER_ROLE_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userRoleId;

	/**
	 * 用户ID
	 */
	@Column(name = "user_id")
	private Integer userId;

	/**
	 * 角色ID
	 */
	@Column(name = "role_id")
	private Integer roleId;

	/**
	 * 构造函数
	 */
	public UserRole() {
	}

	/**
	 * 构造函数
	 * 
	 * @param userId
	 *            用户ID
	 */
	public UserRole(Integer userId) {
		super();
		this.userId = userId;
	}

	/**
	 * 构造函数
	 * 
	 * @param userId
	 *            用户ID
	 * @param roleId
	 *            角色ID
	 */
	public UserRole(Integer userId, Integer roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserRole [userRoleId=" + userRoleId + ", userId=" + userId
				+ ", roleId=" + roleId + ", createAt=" + super.getCreateAt()
				+ ", updateAt=" + super.getUpdateAt() + "]";
	}

}