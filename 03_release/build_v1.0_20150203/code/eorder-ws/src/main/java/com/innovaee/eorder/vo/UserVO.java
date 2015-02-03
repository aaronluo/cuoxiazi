/***********************************************
 * Filename        : UserVO.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Title: UserVO
 * @Description: 用户值对象
 * 
 * @version V1.0
 */
@XmlRootElement
public class UserVO extends BaseVO {

    /** 用户名称 */
    private String username;

    /** 手机号码 */
    private String cellphone;

    /** 用户等级名称 */
    private String levelName;

    /** 折扣 */
    private Float discount;

    /**
     * 构造函数
     */
    public UserVO() {
        super();
    }

    /**
     * 构造函数
     * 
     * @param username
     *            用户名称
     * @param cellphone
     *            手机号码
     * @param levelName
     *            用户等级名称
     * @param discount
     *            折扣
     */
    public UserVO(String username, String cellphone, String levelName,
            Float discount) {
        super();
        this.username = username;
        this.cellphone = cellphone;
        this.levelName = levelName;
        this.discount = discount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "UserVO [username=" + username + ", cellphone=" + cellphone
                + ", levelName=" + levelName + ", discount=" + discount + "]";
    }

}