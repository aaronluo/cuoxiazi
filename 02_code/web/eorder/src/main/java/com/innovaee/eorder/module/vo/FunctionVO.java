/***********************************************
 * Filename        : FunctionVO.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.vo;

/**
 * @Title: FunctionVO
 * @Description: 功能值对象
 *
 * @version V1.0
 */
public class FunctionVO extends BaseVo {

	/** 功能ID */
	private Integer functionId;

	/** 功能名称 */
	private String functionName;

	/** 功能描述 */
	private String functionDesc;

	/** 功能路径 */
	private String functionPath;

	/** 父功能ID */
	private Integer functionParent;

	/** 父功能名称 */
	private String functionParentName;

	/** 功能排序 */
	private String functionOrder;

	public Integer getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
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

	public String getFunctionPath() {
		return functionPath;
	}

	public void setFunctionPath(String functionPath) {
		this.functionPath = functionPath;
	}

	public Integer getFunctionParent() {
		return functionParent;
	}

	public void setFunctionParent(Integer functionParent) {
		this.functionParent = functionParent;
	}

	public String getFunctionParentName() {
		return functionParentName;
	}

	public void setFunctionParentName(String functionParentName) {
		this.functionParentName = functionParentName;
	}

	public String getFunctionOrder() {
		return functionOrder;
	}

	public void setFunctionOrder(String functionOrder) {
		this.functionOrder = functionOrder;
	}

	@Override
	public String toString() {
		return "FunctionVO [functionId=" + functionId + ", functionName="
				+ functionName + ", functionDesc=" + functionDesc
				+ ", functionPath=" + functionPath + ", functionParent="
				+ functionParent + ", functionParentName=" + functionParentName
				+ ", functionOrder=" + functionOrder + "]";
	}

}