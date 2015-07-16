/***********************************************
 * Filename        : User.java 
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Title: User
 * @Description: 用户实体
 *
 * @version V1.0
 */
@Entity
@Table(name = "t_user")
public class User extends BaseEntity {

    /** 对象序列化ID */
    private static final long serialVersionUID = -2911066252145689038L;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 手机号码 */
    private String cellphone;

    /** 用户状态 */
    private Boolean userStatus;

    /** 会员信息 */
    private MemberShip memberShip = new MemberShip();

    /** 角色列表 */
    private Set<Role> roles;

    /**
     * 构造函数
     */
    public User() {
    }

    /**
     * 构造函数
     * 
     * @param username
     *            用户名称
     * @param password
     *            密码
     * @param cellphone
     *            手机号码
     * @param userStatus
     *            用户状态
     */
    public User(String username, String password, String cellphone,
            Boolean userStatus) {
        super();
        this.username = username;
        this.password = password;
        this.cellphone = cellphone;
        this.userStatus = userStatus;
    }

    public User(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "CELLPHONE")
    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    @OneToOne(targetEntity = MemberShip.class, fetch = FetchType.EAGER, mappedBy = "user")
    public MemberShip getMemberShip() {
        return memberShip;
    }

    public void setMemberShip(MemberShip memberShip) {
        this.memberShip = memberShip;
    }

    @Basic
    @Column(name = "USER_STATUS")
    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "T_USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
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