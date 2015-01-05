/***********************************************
 * Filename        : OrderItem.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Title: OrderItem
 * @Description: 订单明细
 * 
 * @version V1.0
 */
@Entity
@Table(name = "t_order_item")
@XmlRootElement
public class OrderItem extends BaseEntity {

    /** 对象序列化ID */
    private static final long serialVersionUID = 7501199115527593716L;

    /**
     * 返回主键
     * 
     * @return 主键
     */
    @Override
    public Serializable getPK() {
        return orderItemId;
    }

    /** 订单id, 不能为空, 必须唯一 */
    @Id
    @Column(name = "order_item_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderItemId;

    /** 订单ID */
    @Column(name = "order_id")
    private Integer orderId;

    /** 菜品ID */
    @Column(name = "dish_id")
    private Integer dishId;

    /** 菜品数量 */
    @Column(name = "dish_amount")
    private Integer dishAmount;

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public Integer getDishAmount() {
        return dishAmount;
    }

    public void setDishAmount(Integer dishAmount) {
        this.dishAmount = dishAmount;
    }

    /**
     * @return 返回该对象的字符串表示
     */
    @Override
    public String toString() {
        return "OrderItem [orderItemId=" + orderItemId + ", orderId=" + orderId
                + ", dishId=" + dishId + ", dishAmount=" + dishAmount
                + ", createAt=" + this.getCreateAt() + ", updateAt="
                + this.getUpdateAt() + "]";
    }

}