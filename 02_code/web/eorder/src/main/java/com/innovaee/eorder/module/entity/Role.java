/***********************************************
 * Filename		: Role.java																									: DishService.java
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
* @Title: Role 
* @Description: 角色实体
* @author coderdream@gmail.com   
* @version V1.0   
*/
@Entity
@Table(name = "t_role")
public class Role extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Override
	public Serializable getPK() {
		return roleId;
	}

	@Id
	@Column(name = "ROLE_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer roleId;
	@Column(name = "ROLE_NAME")
	private String roleName;
	@Column(name = "ROLE_DESC")
	private String roleDesc;
	@Column(name = "ROLE_STATUS")
	private Boolean roleStatus;
	@Column(name = "CREATE_AT")
	private Timestamp createAt;
	@Column(name = "UPDATE_AT")
	private Timestamp updateAt;

	public Role() {
	}

	public Role(Integer roleId) {
		this.roleId = roleId;
	}

	public Role(Integer roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public Role(String roleName, String roleDesc, Boolean roleStatus) {
		super();
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.roleStatus = roleStatus;
	}

	public Role(String roleName, String roleDesc, Boolean roleStatus,
			Timestamp createAt) {
		super();
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.roleStatus = roleStatus;
		this.createAt = createAt;
	}

	public Role(Integer roleId, String roleName, String roleDesc,
			Boolean roleStatus) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.roleStatus = roleStatus;
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

	public Boolean getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(Boolean roleStatus) {
		this.roleStatus = roleStatus;
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
		return "Role [roleId=" + roleId + ", roleName=" + roleName
				+ ", roleDesc=" + roleDesc + ", roleStatus=" + roleStatus
				+ ", createAt=" + createAt + ", updateAt=" + updateAt + "]";
	}

}
