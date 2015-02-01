/***********************************************
 * Filename       : DishVO.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Title: DishVO
 * @Description: 菜品值对象
 * 
 * @version V1.0
 */
@XmlRootElement
public class DishVO implements Serializable {

    /** 对象序列化ID */
    private static final long serialVersionUID = 9116536950254258889L;

    /** 菜品ID */
    private Integer dishId;

    /** 菜品名称 */
    private String dishName;

    /** 菜品价格 */
    private Float dishPrice;

    /** 菜品图片 */
    private String dishPicture;

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
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

    public String getDishPicture() {
        return dishPicture;
    }

    public void setDishPicture(String dishPicture) {
        this.dishPicture = dishPicture;
    }

    /**
     * @return 返回该对象的字符串表示
     */
    @Override
    public String toString() {
        return "DishVO [dishId=" + dishId + ", dishName=" + dishName
                + ", dishPrice=" + dishPrice + ", dishPicture=" + dishPicture
                + "]";
    }

}