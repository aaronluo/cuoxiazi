package com.innovaee.eorder.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_ORDER_ITEM")
public class OrderItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Order order;

    private Dish dish;

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

    @OneToOne(targetEntity = Dish.class, fetch = FetchType.LAZY)
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

}