package com.innovaee.eorder.module.vo;

public class ResetPasswordVo extends BaseVo {

	private static final long serialVersionUID = 1L;

	private String username;
	private String oldPassword;
	private String newPassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}