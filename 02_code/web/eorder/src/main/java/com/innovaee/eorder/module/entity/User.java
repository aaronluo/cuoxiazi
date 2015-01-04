/***********************************************
 * Filename        : User.java 
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
 * @Title: User
 * @Description: 用户实体
 *
 * @version V1.0
 */
@Entity
@Table(name = "t_user")
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Override
	public Serializable getPK() {
		return userId;
	}

	/**
	 * 用户ID
	 */
	@Id
	@Column(name = "USER_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;

	/**
	 * 用户名
	 */
	@Column(name = "USERNAME")
	private String username;

	/**
	 * 密码
	 */
	@Column(name = "PASSWORD")
	private String password;

	/**
	 * 手机号码
	 */
	@Column(name = "CELLPHONE")
	private String cellphone;

	/**
	 * 等级ID
	 */
	@Column(name = "LEVEL_ID")
	private Integer levelId;

	/**
	 * 用户状态
	 */
	@Column(name = "USER_STATUS")
	private Boolean userStatus;

	/**
	 * 构造函数
	 */
	public User() {
	}

	/**
	 * 构造函数
	 * 
	 * @param username
	 *            用户名称
	 * @param password
	 *            密码
	 * @param cellphone
	 *            手机号码
	 * @param levelId
	 *            等级ID
	 * @param userStatus
	 *            用户状态
	 */
	public User(String username, String password, String cellphone,
			Integer levelId, Boolean userStatus) {
		super();
		this.username = username;
		this.password = password;
		this.cellphone = cellphone;
		this.levelId = levelId;
		this.userStatus = userStatus;
	}

	public User(Integer userId) {
		this.userId = userId;
	}

	public User(String username) {
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

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public Boolean getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Boolean userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username
				+ ", password=" + password + ", cellphone=" + cellphone
				+ ", levelId=" + levelId + ", userStatus=" + userStatus
				+ ", createAt=" + super.getCreateAt() + ", updateAt="
				+ super.getUpdateAt() + "]";
	}

}