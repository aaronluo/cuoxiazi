/***********************************************
 * Filename        : Role.java 
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @Title: Role
 * @Description: 角色实体
 *
 * @version V1.0
 */
@Entity
@Table(name = "t_role")
public class Role extends BaseEntity {

    /** 对象序列化ID */
    private static final long serialVersionUID = 3935177845506319976L;

    /** 角色名称 */
    private String roleName;

    /** 角色描述 */
    private String roleDesc;

    /** 角色状态 */
    private Boolean roleStatus;

    /** 功能列表 */
    private Set<Function> functions;

    /** 用户列表 */
    private Set<User> users;

    /**
     * 构造函数
     */
    public Role() {
    }

    /**
     * 构造函数
     * 
     * @param roleName
     *            角色名称
     * @param roleDesc
     *            角色描述
     * @param roleStatus
     *            角色状态
     */
    public Role(String roleName, String roleDesc, Boolean roleStatus) {
        super();
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.roleStatus = roleStatus;
    }

    @Basic
    @Column(name = "ROLE_NAME")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "ROLE_DESC")
    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Basic
    @Column(name = "ROLE_STATUS")
    public Boolean getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(Boolean roleStatus) {
        this.roleStatus = roleStatus;
    }

    @ManyToMany(targetEntity = Function.class, fetch = FetchType.EAGER)
    @JoinTable(name = "T_ROLE_FUNCTION", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "FUNCTION_ID"))
    public Set<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(Set<Function> functions) {
        this.functions = functions;
    }

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER, targetEntity = User.class)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}