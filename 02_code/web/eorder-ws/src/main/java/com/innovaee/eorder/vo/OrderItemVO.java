/***********************************************
 * Filename        : OrderItemVO.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**   
* @Title: OrderItemVO 
* @Description: 订单明细值对象
*    
* @version V1.0   
*/
@XmlRootElement
public class OrderItemVO implements Serializable {

    // 菜品ID
    private Integer dishId;

    // 菜品名称
    private String dishName;

    // 菜品价格
    private Float dishPrice;

    // 菜品数量
    private Integer dishAmount;

    // 菜品图片
    private String dishPicture;

    public OrderItemVO() {

    }

    public OrderItemVO(Integer dishId, String dishName, Float dishPrice,
            Integer dishAmount, String dishPicture) {
        super();
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishAmount = dishAmount;
        this.dishPicture = dishPicture;
    }

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