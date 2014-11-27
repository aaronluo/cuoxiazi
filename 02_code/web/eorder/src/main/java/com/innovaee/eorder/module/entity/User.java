package com.innovaee.eorder.module.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Override
	public Serializable getPK() {
		return userName;
	}

	@Id
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "USER_PASSWORD")
	private String userPassword;
	@Column(name = "REPORT_ENTITY_NAME")
	private String reportEntityName;
	@Column(name = "USER_ENABLE")
	private Boolean userEnable;
	@Column(name = "USER_REGISTER_DATE")
	private Date userRegisterDate;
	@Column(name = "USER_EXPIRATION_DATE")
	private Date userExpirationDate;
	@Column(name = "USER_PASSWD_EXP_DATE")
	private Date userPasswdExpDate;
	@Column(name = "USER_EMAIL")
	private String userEmail;
	@Column(name = "USER_QUESTION1")
	private String userQuestion1;
	@Column(name = "USER_ANSWER1")
	private String userAnswer1;
	@Column(name = "USER_QUESTION2")
	private String userQuestion2;
	@Column(name = "USER_ANSWER2")
	private String userAnswer2;
	@Column(name = "USER_QUESTION3")
	private String userQuestion3;
	@Column(name = "USER_ANSWER3")
	private String userAnswer3;

	public User() {
	}

	public User(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getReportEntityName() {
		return reportEntityName;
	}

	public void setReportEntityName(String reportEntityName) {
		this.reportEntityName = reportEntityName;
	}

	public Boolean getUserEnable() {
		return userEnable;
	}

	public void setUserEnable(Boolean userEnable) {
		this.userEnable = userEnable;
	}

	public Date getUserRegisterDate() {
		return userRegisterDate;
	}

	public void setUserRegisterDate(Date userRegisterDate) {
		this.userRegisterDate = userRegisterDate;
	}

	public Date getUserExpirationDate() {
		return userExpirationDate;
	}

	public void setUserExpirationDate(Date userExpirationDate) {
		this.userExpirationDate = userExpirationDate;
	}

	public Date getUserPasswdExpDate() {
		return userPasswdExpDate;
	}

	public void setUserPasswdExpDate(Date userPasswdExpDate) {
		this.userPasswdExpDate = userPasswdExpDate;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserQuestion1() {
		return userQuestion1;
	}

	public void setUserQuestion1(String userQuestion1) {
		this.userQuestion1 = userQuestion1;
	}

	public String getUserAnswer1() {
		return userAnswer1;
	}

	public void setUserAnswer1(String userAnswer1) {
		this.userAnswer1 = userAnswer1;
	}

	public String getUserQuestion2() {
		return userQuestion2;
	}

	public void setUserQuestion2(String userQuestion2) {
		this.userQuestion2 = userQuestion2;
	}

	public String getUserAnswer2() {
		return userAnswer2;
	}

	public void setUserAnswer2(String userAnswer2) {
		this.userAnswer2 = userAnswer2;
	}

	public String getUserQuestion3() {
		return userQuestion3;
	}

	public void setUserQuestion3(String userQuestion3) {
		this.userQuestion3 = userQuestion3;
	}

	public String getUserAnswer3() {
		return userAnswer3;
	}

	public void setUserAnswer3(String userAnswer3) {
		this.userAnswer3 = userAnswer3;
	}
}
