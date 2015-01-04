/***********************************************
 * Filename        : Function.java 
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
 * @Title: Function
 * @Description: 功能实体
 *
 * @version V1.0
 */
@Entity
@Table(name = "t_function")
public class Function extends BaseEntity {

	@Override
	public Serializable getPK() {
		return functionId;
	}

	/** 功能ID */
	@Id
	@Column(name = "FUNCTION_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer functionId;

	/** 功能名称 */
	@Column(name = "FUNCTION_NAME")
	private String functionName;

	/** 功能描述 */
	@Column(name = "FUNCTION_DESC")
	private String functionDesc;

	/** 功能路径 */
	@Column(name = "FUNCTION_PATH")
	private String functionPath;

	/** 父功能ID */
	@Column(name = "FUNCTION_PARENT")
	private Integer functionParent;

	/** 功能排序 */
	@Column(name = "FUNCTION_ORDER")
	private String functionOrder;

	/** 功能状态 */
	@Column(name = "FUNCTION_STATUS")
	private Boolean functionStatus;

	/**
	 * 构造函数
	 */
	public Function() {
	}

	/**
	 * 构造函数
	 * 
	 * @param functionId
	 *            功能ID
	 */
	public Function(Integer functionId) {
		this.functionId = functionId;
	}

	/**
	 * 构造函数
	 * 
	 * @param functionId
	 *            功能ID
	 * @param functionName
	 *            功能名称
	 */
	public Function(Integer functionId, String functionName) {
		super();
		this.functionId = functionId;
		this.functionName = functionName;
	}

	public Integer getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

	public Function(String functionName) {
		super();
		this.functionName = functionName;
	}

	public Function(String functionName, String functionDesc,
			String functionPath, Integer functionParent, String functionOrder,
			Boolean functionStatus) {
		super();
		this.functionName = functionName;
		this.functionDesc = functionDesc;
		this.functionPath = functionPath;
		this.functionParent = functionParent;
		this.functionOrder = functionOrder;
		this.functionStatus = functionStatus;
	}

	public Function(Integer functionId, String functionName,
			String functionDesc, String functionPath, Integer functionParent,
			String functionOrder, Boolean functionStatus) {
		super();
		this.functionId = functionId;
		this.functionName = functionName;
		this.functionDesc = functionDesc;
		this.functionPath = functionPath;
		this.functionParent = functionParent;
		this.functionOrder = functionOrder;
		this.functionStatus = functionStatus;
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

	public String getFunctionOrder() {
		return functionOrder;
	}

	public void setFunctionOrder(String functionOrder) {
		this.functionOrder = functionOrder;
	}

	public Boolean getFunctionStatus() {
		return functionStatus;
	}

	public void setFunctionStatus(Boolean functionStatus) {
		this.functionStatus = functionStatus;
	}

	@Override
	public String toString() {
		return "Function [functionId=" + functionId + ", functionName="
				+ functionName + ", functionDesc=" + functionDesc
				+ ", functionPath=" + functionPath + ", functionParent="
				+ functionParent + ", functionOrder=" + functionOrder
				+ ", functionStatus=" + functionStatus + ", createAt="
				+ super.getCreateAt() + ", updateAt=" + super.getUpdateAt()
				+ "]";
	}

}