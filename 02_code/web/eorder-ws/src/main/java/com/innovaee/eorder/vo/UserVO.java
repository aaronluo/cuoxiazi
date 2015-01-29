/***********************************************
 * Filename        : UserVO.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Title: UserVO
 * @Description: 用户值对象
 * 
 * @version V1.0
 */
@Entity
@XmlRootElement
public class UserVO implements Serializable {

    /** 对象序列化ID */
    private static final long serialVersionUID = 8193278769466852242L;

    /** 用户id, 不能为空, 必须唯一 */
    private Integer userId;

    /** 名称 */
    private String userName;

    /** 手机号码 */
    private String cellphone;

    /** 用户等级名称 */
    private String levelName;

    /** 折扣 */
    private float discount;

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

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
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

    /**
     * @return 返回该对象的字符串表示
     */
    @Override
    public String toString() {
        return "UserVO [userId=" + userId + ", userName=" + userName
                + ", cellphone=" + cellphone + ", levelName=" + levelName
                + ", discount=" + discount + "]";
    }

}