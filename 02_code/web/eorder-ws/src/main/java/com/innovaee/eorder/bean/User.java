/***********************************************
 * Filename        : User.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.bean;

import com.innovaee.eorder.util.TimestampAdapter;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @Title: User
 * @Description: 用户实体
*
 * @version V1.0
 */
@Entity
@Table(name = "t_user")
@XmlRootElement
public class User extends BaseEntity {

    @Override
    public Serializable getPK() {
        return userId;
    }

    // 用户id, 不能为空, 必须唯一
    @Id
    @Column(name = "user_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    // 名称
    @Column(name = "username")
    private String userName;

    // 密码
    @Column(name = "password")
    private String password;

    // 手机号码
    @Column(name = "cellphone")
    private String cellphone;

    // 用户积分
    @Column(name = "user_score")
    private Integer userScore;

    // 用户等级ID
    @Column(name = "level_id")
    private Integer levelId;

    // 用户状态
    @Column(name = "user_status")
    private String userStatus;

    // 创建时间
    @Column(name = "create_at")
    private Timestamp createAt;

    // 更新时间
    @Column(name = "update_at")
    private Timestamp updateAt;

    public User() {
    }

    public User(Integer userId, String userName, String password,
            String cellphone, String userStatus, Integer levelId,
            Timestamp createAt, Timestamp updateAt) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.cellphone = cellphone;
        this.userStatus = userStatus;
        this.levelId = levelId;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public User(String userName) {
        this.userName = userName;
    }

    public User(Integer userId) {
        this.setUserId(userId);
    }

    public User(Integer userId, String userName) {
        this.setUserId(userId);
        this.userName = userName;
    }

    @Override
    public int hashCode() {
        return this.getUserId();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        
        final User other = (User) object;
        return this.userId == other.userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @XmlJavaTypeAdapter(TimestampAdapter.class)
    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    @XmlJavaTypeAdapter(TimestampAdapter.class)
    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName
                + ", password=" + password + ", cellphone=" + cellphone
                + ", userStatus=" + userStatus + ", levelId=" + levelId
                + ", createAt=" + createAt + ", updateAt=" + updateAt + "]";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

}