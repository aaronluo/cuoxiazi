/***********************************************
 * Filename        : RoleVO.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.vo;

/**
 * @Title: RoleVO
 * @Description: 角色值对象
 *
 * @version V1.0
 */
public class RoleVO extends BaseVo {

	/** 角色ID */
	private Integer roleId;

	/** 角色描述 */
	private String roleDesc;

	/** 角色名称 */
	private String roleName;

	/** 功能名称 */
	private String functionName;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	@Override
	public String toString() {
		return "RoleVO [roleId=" + roleId + ", roleDesc=" + roleDesc
				+ ", roleName=" + roleName + ", functionName=" + functionName
				+ "]";
	}

}