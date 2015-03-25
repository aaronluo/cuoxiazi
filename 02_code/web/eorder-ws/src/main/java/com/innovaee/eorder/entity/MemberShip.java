/***********************************************
 * Filename       : MemberShip.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created         : 03/19/2015
 ************************************************/

package com.innovaee.eorder.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @Title: MemberShip
 * @Description: 用户会员信息
 * 
 * @version V1.0
 */
@Entity
@Table(name = "T_MEMBER_SHIP")
public class MemberShip extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 会员用户信息 */
    private User user;
    /** 会员等级信息 */
    private UserLevel level;
    /** 会员当前积分 */
    private Integer currentScore;
    /** 会员号 */
    private String memberId;
   
    @OneToOne
    @JoinColumn(name="user_id", insertable=true, unique=true)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne(targetEntity=UserLevel.class, fetch=FetchType.LAZY)
    @JoinColumn(name="level_id")
    public UserLevel getLevel() {
        return level;
    }

    public void setLevel(UserLevel level) {
        this.level = level;
    }
    @Basic
    @Column(name="current_score")
    public Integer getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Integer currentScore) {
        this.currentScore = currentScore;
    }

    @Basic
    @Column(name="member_id")
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
