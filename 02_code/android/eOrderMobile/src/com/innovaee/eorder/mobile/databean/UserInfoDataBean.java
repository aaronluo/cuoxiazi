/***********************************************
 * Filename		: UserInfoDataBean.java	
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 12/20/2014
 ************************************************/
package com.innovaee.eorder.mobile.databean;

import java.io.Serializable;

/**
 * 会员信息数据Bean
 * 
 */
public class UserInfoDataBean implements Serializable {
    private static final long serialVersionUID = 1L;

    // 表单名定义
    public final static String TABLE = "user";

    // 会员id定义
    public final static String COLUM_ID = "userId";

    // 会员名字定义
    public final static String COLUM_NAME = "userName";

    // 会员电话定义
    public final static String COLUM_PRICE = "cellphone";

    // 会员等级名称定义
    public final static String COLUM_BITMAPURL = "levelName";

    // 会员等级折扣定义
    public final static String COLUM_COUNT = "discount";

    // 会员id
    private int userId;

    // 会员名字
    private String userName;

    // 会员电话
    private String cellphone;

    // 会员等级名称
    private String levelName;

    // 会员等级折扣
    private Double discount;

    public UserInfoDataBean() {
    }

    public UserInfoDataBean(int userId, String userName, String cellphone,
            String levelName, Double discount) {
        this.userId = userId;
        this.userName = userName;
        this.cellphone = cellphone;
        this.levelName = levelName;
        this.discount = discount;
    }

    /**
     * 获取会员id
     * 
     * @return 会员id
     */
    public int getId() {
        return this.userId;
    }

    /**
     * 设置会员id
     * 
     * @param userId
     *            会员id
     */
    public void setId(int userId) {
        this.userId = userId;
    }

    /**
     * 获取会员名
     * 
     * @return 会员名
     */
    public String getName() {
        return this.userName;
    }

    /**
     * 设置会员名
     * 
     * @param userName
     *            会员名
     */
    public void setName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取会员电话号码
     * 
     * @return 电话号码
     */
    public String getCellphone() {
        return this.cellphone;
    }

    /**
     * 设置会员电话号码
     * 
     * @param cellphone
     *            电话号码
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    /**
     * 获取会员等级名
     * 
     * @return 等级名
     */
    public String getLevelName() {
        return this.levelName;
    }

    /**
     * 设置会员等级名
     * 
     * @param levelName
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * 获取会员折扣
     * 
     * @return 会员折扣
     */
    public Double getDiscount() {
        return this.discount;
    }

    /**
     * 设置会员折扣
     * 
     * @param discount
     *            会员折扣
     */
    public void setCount(Double discount) {
        this.discount = discount;
    }
}
