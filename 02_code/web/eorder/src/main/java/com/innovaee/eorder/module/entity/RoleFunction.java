package com.innovaee.eorder.module.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE_FUNCTION")
public class RoleFunction extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Override
	public Serializable getPK() {
		return roleFunctionName;
	}

	@Id
	@Column(name = "role_function_name")
	private String roleFunctionName;
	@Column(name = "role_name")
	private String roleName;
	@Column(name = "function_name")
	private String functionName;

	public RoleFunction() {
	}

	public RoleFunction(String roleFunctionName) {
		this.roleFunctionName = roleFunctionName;
	}

	public RoleFunction(String roleFunctionName, String roleName, String functionName) {
		super();
		this.roleFunctionName = roleFunctionName;
		this.roleName = roleName;
		this.functionName = functionName;
	}

	public String getRoleFunctionName() {
		return roleFunctionName;
	}

	public void setRoleFunctionName(String roleFunctionName) {
		this.roleFunctionName = roleFunctionName;
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
		return "RoleFunction [roleFunctionName=" + roleFunctionName + ", roleName=" + roleName + ", functionName=" + functionName + "]";
	}

}
