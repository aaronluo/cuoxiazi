/***********************************************
 * Filename       : UserLevel.java
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @Title: UserLevel
 * @Description: 用户等级
 * 
 * @version V1.0
 */
@Entity
@Table(name = "T_USER_LEVEL")
public class UserLevel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 名称 */
    private String levelName;

    /** 折扣 */
    private Float discount;

    /** 等级积分 */
    private Integer levelScore;

    /** 用户状态 */
    private boolean levelStatus;

    /** 用户列表 */
    private List<User> users;

    /**
     * 默认构造函数
     */
    public UserLevel() {
    }

    /**
     * 构造函数
     * 
     * @param levelName
     *            名称
     * @param discount
     *            折扣
     * @param levelScore
     *            等级积分
     * @param levelStatus
     *            用户状态
     */
    public UserLevel(String levelName, Float discount, Integer levelScore,
            boolean levelStatus) {
        super();
        this.levelName = levelName;
        this.discount = discount;
        this.levelScore = levelScore;
        this.levelStatus = levelStatus;
    }

    @Basic
    @Column(name = "LEVEL_NAME")
    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    @Basic
    @Column(name = "DISCOUNT")
    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "LEVEL_SCORE")
    public Integer getLevelScore() {
        return levelScore;
    }

    public void setLevelScore(Integer levelScore) {
        this.levelScore = levelScore;
    }

    @Basic
    @Column(name = "LEVEL_STATUS")
    public boolean isLevelStatus() {
        return levelStatus;
    }

    public void setLevelStatus(boolean levelStatus) {
        this.levelStatus = levelStatus;
    }

    @OneToMany(targetEntity = User.class, fetch = FetchType.LAZY, mappedBy = "userLevel")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserLevel [levelName=" + levelName + ", discount=" + discount
                + ", levelScore=" + levelScore + ", levelStatus=" + levelStatus
                + "]";
    }

}