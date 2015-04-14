/***********************************************
 * Filename        : OrderItem.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Title: OrderItem
 * @Description: 订单明细
 * 
 * @version V1.0
 */
@Entity
@Table(name = "T_ORDER_ITEM")
public class OrderItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 订单 */
    private Order order;

    /** 菜品 */
    private Dish dish;

    /** 菜品数量 */
    private Integer dishAmount;

    public OrderItem() {
    }

    public OrderItem(Integer dishAmount) {
        super();
        this.dishAmount = dishAmount;
    }

    @ManyToOne(targetEntity = Order.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDER_ID")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @OneToOne(targetEntity = Dish.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "DISH_ID")
    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @Basic
    @Column(name = "DISH_AMOUNT")
    public Integer getDishAmount() {
        return dishAmount;
    }

    public void setDishAmount(Integer dishAmount) {
        this.dishAmount = dishAmount;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SIMPLE_STYLE);
    }
}