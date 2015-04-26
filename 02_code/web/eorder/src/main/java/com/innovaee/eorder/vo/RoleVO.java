/***********************************************
 * Filename        : RoleVO.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.vo;

/**
 * @Title: RoleVO
 * @Description: 角色值对象
 *
 * @version V1.0
 */
public class RoleVO extends BaseVO {

    /** 对象序列化ID */
    private static final long serialVersionUID = -3831009342301894624L;

    /** 角色描述 */
    private String roleDesc;

    /** 角色名称 */
    private String roleName;

    /** 功能名称 */
    private String functionName;

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

}