/***********************************************
 * Filename        : OrderItemVO.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.vo;


/**
 * @Title: OrderItemVO
 * @Description: 订单明细值对象
 * 
 * @version V1.0
 */
public class OrderItemVO extends BaseVO {

    /** 对象序列化ID */
    private static final long serialVersionUID = 3806303946270964284L;

    /** 菜品ID */
    private Long dishId;

    /** 菜品名称 */
    private String dishName;

    /** 菜品ID */
    private Float dishPrice;

    /** 菜品数量 */
    private Integer dishAmount;

    /** 菜品图片 */
    private String dishPicture;

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Float getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(Float dishPrice) {
        this.dishPrice = dishPrice;
    }

    public Integer getDishAmount() {
        return dishAmount;
    }

    public void setDishAmount(Integer dishAmount) {
        this.dishAmount = dishAmount;
    }

    public String getDishPicture() {
        return dishPicture;
    }

    public void setDishPicture(String dishPicture) {
        this.dishPicture = dishPicture;
    }

    @Override
    public String toString() {
        return "OrderItemVO [dishId=" + dishId + ", dishName=" + dishName
                + ", dishPrice=" + dishPrice + ", dishAmount=" + dishAmount
                + ", dishPicture=" + dishPicture + "]";
    }

}