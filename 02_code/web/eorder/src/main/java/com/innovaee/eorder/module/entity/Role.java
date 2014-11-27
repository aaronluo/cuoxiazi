package com.innovaee.eorder.module.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE")
public class Role extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Override
	public Serializable getPK() {
		return roleName;
	}

	@Id
	@Column(name = "ROLE_NAME")
	private String roleName;
	@Column(name = "ROLE_DESC")
	private String roleDesc;
	@Column(name = "ROLE_ENABLE")
	private Boolean roleEnable;

	public Role() {
	}

	public Role(String roleName) {
		this.roleName = roleName;
	}

	public Role(String roleName, String roleDesc, Boolean roleEnable) {
		super();
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.roleEnable = roleEnable;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Boolean getRoleEnable() {
		return roleEnable;
	}

	public void setRoleEnable(Boolean roleEnable) {
		this.roleEnable = roleEnable;
	}
}
