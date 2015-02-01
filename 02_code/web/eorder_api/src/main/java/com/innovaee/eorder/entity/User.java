/***********************************************
 * Filename        : User.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @Title: User
 * @Description: 用户实体
 * 
 * @version V1.0
 */
@Entity
@Table(name = "T_USER")
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 用户名称 */
    private String username;

    /** 密码 */
    private String password;

    /** 手机号码 */
    private String cellphone;

    /** 用户积分 */
    private Integer userScore;

    /** 用户状态 */
    private boolean userStatus;

    /** 用户等级ID */
    private UserLevel userLevel;

    /** 用户订单列表 */
    private List<Order> orders;

    /**
     * 默认构造函数
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
     * @param userScore
     *            用户积分
     * @param userStatus
     *            用户状态
     */
    public User(String username, String password, String cellphone,
            Integer userScore, boolean userStatus) {
        super();
        this.username = username;
        this.password = password;
        this.cellphone = cellphone;
        this.userScore = userScore;
        this.userStatus = userStatus;
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

    @Basic
    @Column(name = "USER_SCORE")
    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    @Basic
    @Column(name = "USER_STATUS")
    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    @OneToOne(targetEntity = UserLevel.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_LEVEL_ID")
    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    @OneToMany(targetEntity = Order.class, fetch = FetchType.EAGER, mappedBy = "member")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password
                + ", cellphone=" + cellphone + ", userScore=" + userScore
                + ", userStatus=" + userStatus + ", userLevel=" + userLevel
                + ", orders=" + orders + "]";
    }

}