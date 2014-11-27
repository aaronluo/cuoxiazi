package com.innovaee.eorder.module.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FUNCTION")
public class Function extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Override
	public Serializable getPK() {
		return functionName;
	}

	@Id
	@Column(name = "FUNCTION_NAME")
	private String functionName;
	@Column(name = "FUNCTION_DESC")
	private String functionDesc;
	@Column(name = "FUNCTION_DISPLAY")
	private String functionDisplay;
	@Column(name = "FUNCTION_PATH")
	private String functionPath;
	@Column(name = "FUNCTION_PARENT")
	private String functionParent;
	@Column(name = "FUNCTION_order")
	private String functionOrder;
	@Column(name = "FUNCTION_ENABLE")
	private Boolean functionEnable;

	public Function() {
	}

	public Function(String functionName) {
		this.functionName = functionName;
	}

	public Function(String functionName, String functionDesc, String functionDisplay, String functionPath, String functionParent,
			String functionOrder, Boolean functionEnable) {
		super();
		this.functionName = functionName;
		this.functionDesc = functionDesc;
		this.functionDisplay = functionDisplay;
		this.functionPath = functionPath;
		this.functionParent = functionParent;
		this.functionOrder = functionOrder;
		this.functionEnable = functionEnable;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionDesc() {
		return functionDesc;
	}

	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}

	public String getFunctionDisplay() {
		return functionDisplay;
	}

	public void setFunctionDisplay(String functionDisplay) {
		this.functionDisplay = functionDisplay;
	}

	public String getFunctionPath() {
		return functionPath;
	}

	public void setFunctionPath(String functionPath) {
		this.functionPath = functionPath;
	}

	public String getFunctionParent() {
		return functionParent;
	}

	public void setFunctionParent(String functionParent) {
		this.functionParent = functionParent;
	}

	public String getFunctionOrder() {
		return functionOrder;
	}

	public void setFunctionOrder(String functionOrder) {
		this.functionOrder = functionOrder;
	}

	public Boolean getFunctionEnable() {
		return functionEnable;
	}

	public void setFunctionEnable(Boolean functionEnable) {
		this.functionEnable = functionEnable;
	}
}