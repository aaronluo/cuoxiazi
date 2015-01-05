/***********************************************
 * Filename        : Order.java
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
 * @Title: Order
 * @Description: 订单实体
 * 
 * @version V1.0
 */
@Entity
@Table(name = "t_order")
@XmlRootElement
public class Order extends BaseEntity {

    /** 对象序列化ID */
    private static final long serialVersionUID = 387041723125665410L;

    /**
     * 返回主键
     * 
     * @return 主键
     */
    @Override
    public Serializable getPK() {
        return orderId;
    }

    /** 订单id, 不能为空, 必须唯一 */
    @Id
    @Column(name = "order_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;

    /** 订单序列号 */
    @Column(name = "orderSeq")
    private String orderSeq;

    /** 桌号 */
    @Column(name = "table_number")
    private Integer tableNumber;

    /** 就餐人数 */
    @Column(name = "attendee_number")
    private Integer attendeeNumber;

    /** 密码 */
    @Column(name = "total_price")
    private float totalPrice;

    /** 手机号码 */
    @Column(name = "cellphone")
    private String cellphone;

    /** 点餐员ID */
    @Column(name = "servent_id")
    private Integer serventId;

    /** 用户ID（会员） */
    @Column(name = "member_id")
    private Integer memberId;

    /** 收银员ID */
    @Column(name = "casher_id")
    private Integer casherId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(String orderSeq) {
        this.orderSeq = orderSeq;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Integer getAttendeeNumber() {
        return attendeeNumber;
    }

    public void setAttendeeNumber(Integer attendeeNumber) {
        this.attendeeNumber = attendeeNumber;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Integer getServentId() {
        return serventId;
    }

    public void setServentId(Integer serventId) {
        this.serventId = serventId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getCasherId() {
        return casherId;
    }

    public void setCasherId(Integer casherId) {
        this.casherId = casherId;
    }

    /**
     * @return 返回该对象的字符串表示
     */
    @Override
    public String toString() {
        return "Order [orderId=" + getPK() + ", orderSeq=" + orderSeq
                + ", tableNumber=" + tableNumber + ", attendeeNumber="
                + attendeeNumber + ", totalPrice=" + totalPrice
                + ", cellphone=" + cellphone + ", serventId=" + serventId
                + ", memberId=" + memberId + ", casherId=" + casherId
                + ", createAt=" + this.getCreateAt() + ", updateAt="
                + this.getUpdateAt() + "]";
    }

}