/***********************************************
 * Filename        : UserLevel.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Title: UserLevel
 * @Description: 用户等级
 * 
 * @version V1.0
 */
@Entity
@Table(name = "t_user_level")
@XmlRootElement
public class UserLevel extends BaseEntity {

    /** 对象序列化ID */
    private static final long serialVersionUID = -5670126597834842556L;

    /**
     * 返回主键
     * 
     * @return 主键
     */
    @Override
    public Serializable getPK() {
        return levelId;
    }

    /** 用户id, 不能为空, 必须唯一 */
    @Id
    @Column(name = "level_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer levelId;

    /** 名称 */
    @Column(name = "level_name")
    private String levelName;

    /** 折扣 */
    @Column(name = "discount")
    private float discount;

    /** 等级积分 */
    @Column(name = "level_score")
    private Integer levelScore;

    /** 用户状态 */
    @Column(name = "level_status")
    private Boolean levelStatus;

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Integer getLevelScore() {
        return levelScore;
    }

    public void setLevelScore(Integer levelScore) {
        this.levelScore = levelScore;
    }

    public Boolean getLevelStatus() {
        return levelStatus;
    }

    public void setLevelStatus(Boolean levelStatus) {
        this.levelStatus = levelStatus;
    }

    /**
     * @return 返回该对象的字符串表示
     */
    @Override
    public String toString() {
        return "UserLevel [levelId=" + getPK() + ", levelName=" + levelName
                + ", discount=" + discount + ", levelScore=" + levelScore
                + ", levelStatus=" + levelStatus + ", createAt="
                + this.getCreateAt() + ", updateAt=" + this.getUpdateAt() + "]";
    }

}