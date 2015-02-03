/***********************************************
 * Filename    : OrderInfoDataBean.java
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.databean;

import java.io.Serializable;


/**
 * 订单详情数据Bean
 * 
 */
public class OrderInfoDataBean implements Serializable {
    //序列化id
    private static final long serialVersionUID = 1L;

    // 表单名定义
    public static final String TABLE = "orderitems";

    // id定义
    public static final String COLUM_DISHID = "dishId";

    // 名字定义
    public static final String COLUM_DISHNAME = "dishName";

    // 商品价格定义
    public static final String COLUM_DISHPRICE = "dishPrice";

    // 商品数量定义
    public static final String COLUM_DISHAMOUNT = "dishAmount";

    // 商品图片url地址定义
    public static final String COLUM_DISHPICTURE = "dishPicture";

    // id
    private int dishId;

    // 名字
    private String dishName;

    // 商品价格
    private Double dishPrice;

    // 商品数量
    private int dishAmount;

    // 商品图片url地址
    private String dishPicture;

    /**
     * 构造函数
     */
    public OrderInfoDataBean() {
    }

    /**
     * 构造函数
     * @param dishId 订单id
     * @param dishName 订单id
     * @param dishPrice 订单价格
     * @param dishAmount 商品数量
     * @param dishPicture 订单价格
     */
    public OrderInfoDataBean(int dishId, String dishName, Double dishPrice,
            int dishAmount, String dishPicture) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishAmount = dishAmount;
        this.dishPicture = dishPicture;
    }

    /**
     * 获取订单id
     * 
     * @return 订单id
     */
    public int getId() {
        return this.dishId;
    }

    /**
     * 设置订单id
     * 
     * @param dishId
     *            订单id
     */
    public void setId(int dishId) {
        this.dishId = dishId;
    }

    /**
     * 得到订单名
     * 
     * @return 订单id
     */
    public String getDishName() {
        return this.dishName;
    }

    /**
     * 设置订单名
     * 
     * @param dishName
     *            订单名
     */
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    /**
     * 获取订单价格
     * 
     * @return 订单价格
     */
    public Double getDishPrice() {
        return this.dishPrice;
    }

    /**
     * 设置订单价格
     * 
     * @param dishPrice
     *            订单价格
     */
    public void setDishPrice(Double dishPrice) {
        this.dishPrice = dishPrice;
    }

    /**
     * 获取订单商品数量
     * 
     * @return 商品数量
     */
    public int getDishAmount() {
        return this.dishAmount;
    }

    /**
     * 设置订单商品数量
     * 
     * @param dishAmount
     *            商品数量
     */
    public void setDishAmount(int dishAmount) {
        this.dishAmount = dishAmount;
    }

    /**
     * 获取订单图片地址
     * 
     * @return 订单图片地址
     */
    public String getDishPicture() {
        return this.dishPicture;
    }

    /**
     * 设置订单图片地址
     * 
     * @param dishPicture
     *            设置订单图片地址
     */
    public void setDishPicture(String dishPicture) {
        this.dishPicture = dishPicture;
    }
}
