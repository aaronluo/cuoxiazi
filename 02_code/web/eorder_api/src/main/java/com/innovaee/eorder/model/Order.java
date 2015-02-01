package com.innovaee.eorder.model;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_ORDER")
public class Order extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String orderSeq;

    private Integer tableNumber;

    private Integer attendeeNumber;

    private Float totalPrice;

    private User servent;

    private User member;

    private User casher;

    private String orderStatus;

    private Set<OrderItem> orderItems;

    public Order() {
    }

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
    
    @OneToMany(targetEntity = OrderItem.class, fetch = FetchType.LAZY, mappedBy = "order")
    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}