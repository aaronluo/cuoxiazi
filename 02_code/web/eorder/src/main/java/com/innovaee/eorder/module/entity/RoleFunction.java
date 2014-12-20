/***********************************************
 * Filename		: RoleFunction.java																									: DishService.java
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
* @Title: RoleFunction 
* @Description: 角色功能实体
* @author coderdream@gmail.com   
* @version V1.0   
*/
@Entity
@Table(name = "t_role_function")
public class RoleFunction extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Override
	public Serializable getPK() {
		return roleFunctionId;
	}

	@Id
	@Column(name = "ROLE_FUNCTION_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer roleFunctionId;
	@Column(name = "role_ID")
	private Integer roleId;
	@Column(name = "function_ID")
	private Integer functionId;
	@Column(name = "CREATE_AT")
	private Timestamp createAt;
	@Column(name = "UPDATE_AT")
	private Timestamp updateAt;

	public RoleFunction() {
	}

	public RoleFunction(Integer roleFunctionId) {
		this.roleFunctionId = roleFunctionId;
	}

	public RoleFunction(Integer roleId, Integer functionId) {
		super();
		this.roleId = roleId;
		this.functionId = functionId;
	}

	public RoleFunction(Integer roleId, Integer functionId, Timestamp createAt) {
		super();
		this.roleId = roleId;
		this.functionId = functionId;
		this.createAt = createAt;
	}

	public Integer getRoleFunctionId() {
		return roleFunctionId;
	}

	public void setRoleFunctionId(Integer roleFunctionId) {
		this.roleFunctionId = roleFunctionId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
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
		return "RoleFunction [roleFunctionId=" + roleFunctionId + ", roleId="
				+ roleId + ", functionId=" + functionId + ", createAt="
				+ createAt + ", updateAt=" + updateAt + "]";
	}

}