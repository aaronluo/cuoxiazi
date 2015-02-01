/***********************************************
 * Filename        : Order.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @Title: Order
 * @Description: 订单实体
 * 
 * @version V1.0
 */
@Entity
@Table(name = "T_ORDER")
public class Order extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 订单序列号 */
    private String orderSeq;

    /** 桌号 */
    private Integer tableNumber;

    /** 就餐人数 */
    private Integer attendeeNumber;

    /** 订单总价 */
    private Float totalPrice;

    /** 订单状态 */
    private String orderStatus;

    /** 点餐员 */
    private User servent;

    /** 用户（会员） */
    private User member;

    /** 收银员 */
    private User casher;

    /** 订单明细 */
    private List<OrderItem> orderItems;

    /**
     * 默认构造函数
     */
    public Order() {
    }

    /**
     * 构造函数
     * 
     * @param orderSeq
     *            订单序列号
     * @param tableNumber
     *            桌号
     * @param attendeeNumber
     *            就餐人数
     * @param totalPrice
     *            订单总价
     * @param orderStatus
     *            订单状态
     */
    public Order(String orderSeq, Integer tableNumber, Integer attendeeNumber,
            Float totalPrice, String orderStatus) {
        super();
        this.orderSeq = orderSeq;
        this.tableNumber = tableNumber;
        this.attendeeNumber = attendeeNumber;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "ORDER_SEQ")
    public String getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(String orderSeq) {
        this.orderSeq = orderSeq;
    }

    @Basic
    @Column(name = "TABLE_NUMBER")
    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    @Basic
    @Column(name = "ATTENDEE_NUMBER")
    public Integer getAttendeeNumber() {
        return attendeeNumber;
    }

    public void setAttendeeNumber(Integer attendeeNumber) {
        this.attendeeNumber = attendeeNumber;
    }

    @Basic
    @Column(name = "TOTAL_PRICE")
    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Basic
    @Column(name = "ORDER_STATUS")
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "SERVENT_ID")
    public User getServent() {
        return servent;
    }

    public void setServent(User servent) {
        this.servent = servent;
    }

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "MEMBER_ID")
    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "CASHER_ID")
    public User getCasher() {
        return casher;
    }

    public void setCasher(User casher) {
        this.casher = casher;
    }

    @OneToMany(targetEntity = OrderItem.class, fetch = FetchType.EAGER, mappedBy = "order")
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}