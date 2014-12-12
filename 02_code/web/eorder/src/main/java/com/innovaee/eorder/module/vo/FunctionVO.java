package com.innovaee.eorder.module.vo;

public class FunctionVO extends BaseVo {

	private Integer functionId;

	private String functionName;

	private String functionDesc;

	private String functionPath;

	private Integer functionParent;

	private String functionParentName;

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