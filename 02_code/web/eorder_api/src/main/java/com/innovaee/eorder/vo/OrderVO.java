package com.innovaee.eorder.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrderVO extends BaseVO {

    private float totalPrice;

    /** 订单时间 */
    private Date createAt;

    public OrderVO() {
        super();
    }

    public OrderVO(float totalPrice, Date createAt) {
        super();
        this.totalPrice = totalPrice;
        this.createAt = createAt;
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