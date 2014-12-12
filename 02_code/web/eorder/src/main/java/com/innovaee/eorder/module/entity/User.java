package com.innovaee.eorder.module.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_user")
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Override
	public Serializable getPK() {
		return userId;
	}

	@Id
	@Column(name = "USER_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @Column(name = "USER_ID")
	private Integer userId;
	@Column(name = "USERNAME")
	private String username;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "CELLPHONE")
	private String cellphone;
	@Column(name = "LEVEL_ID")
	private Integer levelId;
	@Column(name = "USER_STATUS")
	private Boolean userStatus;
	@Column(name = "CREATE_AT")
	private Timestamp createAt;
	@Column(name = "UPDATE_AT")
	private Timestamp updateAt;

	public User() {
	}

	public User(String username, String password, String cellphone,
			Integer levelId, Boolean userStatus, Timestamp createAt) {
		super();
		this.username = username;
		this.password = password;
		this.cellphone = cellphone;
		this.levelId = levelId;
		this.userStatus = userStatus;
		this.createAt = createAt;
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
		return "User [userId=" + userId + ", username=" + username
				+ ", password=" + password + ", cellphone=" + cellphone
				+ ", levelId=" + levelId + ", userStatus=" + userStatus
				+ ", createAt=" + createAt + ", updateAt=" + updateAt + "]";
	}

}