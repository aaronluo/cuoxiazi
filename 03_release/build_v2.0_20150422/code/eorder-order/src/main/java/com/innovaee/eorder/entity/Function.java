/***********************************************
 * Filename        : Function.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.entity;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Title: Function
 * @Description: 功能实体
 *
 * @version V1.0
 */
@Entity
@Table(name = "t_function")
public class Function extends BaseEntity {

    /** 对象序列化ID */
    private static final long serialVersionUID = -5034816237841013711L;

    /** 功能名称 */
    private String functionName;

    /** 功能描述 */
    private String functionDesc;

    /** 功能图片 */
    private String functionPicture;

    /** 功能路径 */
    private String functionPath;

    /** 父功能ID */
    private Long functionParent;

    /** 功能排序 */
    private String functionOrder;

    /** 功能状态 */
    private Boolean functionStatus;

    /** 角色列表 */
    private Set<Role> roles;

    /**
     * 构造函数
     */
    public Function() {
    }

    /**
     * 构造函数
     * 
     * @param functionName
     *            功能名称
     * @param functionDesc
     *            功能描述
     * @param functionPath
     *            功能路径
     * @param functionParent
     *            父功能ID
     * @param functionOrder
     *            功能排序
     * @param functionStatus
     *            功能状态
     */
    public Function(String functionName, String functionDesc,
            String functionPicture, String functionPath, Long functionParent,
            String functionOrder, Boolean functionStatus) {
        super();
        this.functionName = functionName;
        this.functionDesc = functionDesc;
        this.functionPicture = functionPicture;
        this.functionPath = functionPath;
        this.functionParent = functionParent;
        this.functionOrder = functionOrder;
        this.functionStatus = functionStatus;
    }

    @Basic
    @Column(name = "FUNCTION_NAME")
    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    @Basic
    @Column(name = "FUNCTION_DESC")
    public String getFunctionDesc() {
        return functionDesc;
    }

    public void setFunctionDesc(String functionDesc) {
        this.functionDesc = functionDesc;
    }

    @Basic
    @Column(name = "FUNCTION_PATH")
    public String getFunctionPath() {
        return functionPath;
    }

    public void setFunctionPath(String functionPath) {
        this.functionPath = functionPath;
    }

    @Basic
    @Column(name = "FUNCTION_PARENT")
    public Long getFunctionParent() {
        return functionParent;
    }

    public void setFunctionParent(Long functionParent) {
        this.functionParent = functionParent;
    }

    @Basic
    @Column(name = "FUNCTION_ORDER")
    public String getFunctionOrder() {
        return functionOrder;
    }

    public void setFunctionOrder(String functionOrder) {
        this.functionOrder = functionOrder;
    }

    @Basic
    @Column(name = "FUNCTION_STATUS")
    public Boolean getFunctionStatus() {
        return functionStatus;
    }

    public void setFunctionStatus(Boolean functionStatus) {
        this.functionStatus = functionStatus;
    }

    @Basic
    @Column(name = "FUNCTION_PICTURE")
    public String getFunctionPicture() {
        return functionPicture;
    }

    public void setFunctionPicture(String functionPicture) {
        this.functionPicture = functionPicture;
    }

    @ManyToMany(mappedBy = "functions", fetch = FetchType.EAGER, targetEntity = Role.class)
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SIMPLE_STYLE);
    }
}