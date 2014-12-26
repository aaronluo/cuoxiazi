/***********************************************
 * Filename		: UserRole.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.module.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
* @Title: UserRole 
* @Description: 用户角色 
* @author coderdream@gmail.com   
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
	 * 创建时间
	 */
	@Column(name = "CREATE_AT")
	private Timestamp createAt;

	/**
	 * 更新时间
	 */
	@Column(name = "UPDATE_AT")
	private Timestamp updateAt;

	public UserRole() {
	}

	public UserRole(Integer userId) {
		super();
		this.userId = userId;
	}

	public UserRole(Integer userId, Integer roleId, Timestamp createAt) {
		super();
		this.userId = userId;
		this.roleId = roleId;
		this.createAt = createAt;
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

	@Override
	public String toString() {
		return "UserRole [userRoleId=" + userRoleId + ", userId=" + userId
				+ ", roleId=" + roleId + ", createAt=" + createAt
				+ ", updateAt=" + updateAt + "]";
	}

}