/***********************************************
 * Filename        : RoleFunction.java 
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
 * @Title: RoleFunction
 * @Description: 角色功能实体
 *
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

	/**
	 * 角色功能ID
	 */
	@Id
	@Column(name = "ROLE_FUNCTION_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer roleFunctionId;

	/**
	 * 角色ID
	 */
	@Column(name = "role_ID")
	private Integer roleId;

	/**
	 * 功能ID
	 */
	@Column(name = "function_ID")
	private Integer functionId;

	/**
	 * 构造函数
	 */
	public RoleFunction() {
	}

	/**
	 * 构造函数
	 * 
	 * @param roleFunctionId
	 *            角色功能ID
	 */
	public RoleFunction(Integer roleFunctionId) {
		this.roleFunctionId = roleFunctionId;
	}

	/**
	 * 构造函数
	 * 
	 * @param roleId
	 *            角色ID
	 * @param functionId
	 *            功能ID
	 */
	public RoleFunction(Integer roleId, Integer functionId) {
		super();
		this.roleId = roleId;
		this.functionId = functionId;
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

	@Override
	public String toString() {
		return "RoleFunction [roleFunctionId=" + roleFunctionId + ", roleId="
				+ roleId + ", functionId=" + functionId + ", createAt="
				+ super.getCreateAt() + ", updateAt=" + super.getUpdateAt()
				+ "]";
	}

}