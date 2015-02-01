package com.innovaee.eorder.model;

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

    private Integer dishAccount;

    public OrderItem() {
    }

    public OrderItem(Integer dishAccount) {
        super();
        this.dishAccount = dishAccount;
    }

    @ManyToOne(targetEntity = Order.class, fetch = FetchType.LAZY)
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
    @Column(name = "DISH_ACCOUNT")
    public Integer getDishAccount() {
        return dishAccount;
    }

    public void setDishAccount(Integer dishAccount) {
        this.dishAccount = dishAccount;
    }
    
}