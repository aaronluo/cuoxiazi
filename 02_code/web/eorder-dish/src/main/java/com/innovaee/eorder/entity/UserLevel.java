/***********************************************
 * Filename        : UserLevel.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Title: UserLevel
 * @Description: 用户等级实体
 *
 * @version V1.0
 */
@Entity
@Table(name = "t_user_level")
public class UserLevel extends BaseEntity {

    /** 对象序列化ID */
    private static final long serialVersionUID = 2189941376177920282L;

    /** 等级名称 */
    private String levelName;

    /** 折扣 */
    private Float discount;

    /** 等级积分 */
    private Integer levelScore;

    /** 等级状态 */
    private Boolean levelStatus;

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
    @Column(name = "level_score")
    public Long getLevelScore() {
        return levelScore;
    }

    public void setLevelScore(Long levelScore) {
        this.levelScore = levelScore;
    }

    @Basic
    @Column(name = "Level_STATUS")
    public Boolean getLevelStatus() {
        return levelStatus;
    }

    public void setLevelStatus(Boolean levelStatus) {
        this.levelStatus = levelStatus;
    }

}