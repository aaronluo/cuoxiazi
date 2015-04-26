/***********************************************
 * Filename        : OrderVO.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Title: OrderVO
 * @Description: 订单值对象
 * 
 * @version V1.0
 */
@XmlRootElement
public class OrderVO extends BaseVO {

    /** 订单总价 */
    private float totalPrice;

    /** 订单时间 */
    private Date createAt;

    /** 订单流水号 */
    private String orderSeq;
    /**
     * 默认构造函数
     */
    public OrderVO() {
        super();
    }

    /**
     * 构造函数
     * 
     * @param totalPrice
     *            订单总价
     * @param createAt
     *            订单时间
     */
    public OrderVO(float totalPrice, Date createAt) {
        super();
        this.totalPrice = totalPrice;
        this.createAt = createAt;
    }

    public String getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(String orderSeq) {
        this.orderSeq = orderSeq;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

}