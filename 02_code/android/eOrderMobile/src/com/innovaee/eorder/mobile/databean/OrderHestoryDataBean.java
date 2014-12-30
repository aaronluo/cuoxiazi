/***********************************************
 * Filename    : OrderHestoryDataBean.java
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.databean;

import java.io.Serializable;

/**
 * 订单历史详情信息数据Bean
 * 
 */
public class OrderHestoryDataBean implements Serializable {
    private static final long serialVersionUID = 1L;

    // 表单名定义
    public final static String TABLE = "orderhestory";

    // 订单id定义
    public final static String COLUM_ID = "id";

    // 订单时间定义
    public final static String COLUM_TIME = "time";

    // 订单总价格定义
    public final static String COLUM_TOTALPRICE = "totalprice";

    // 订单id
    private int id;

    // 订单时间
    private String time;

    // 订单总价格
    private Double totalPrice;

    public OrderHestoryDataBean() {
    }

    public OrderHestoryDataBean(int id, String time, Double totalPrice) {
        this.id = id;
        this.time = time;
        this.totalPrice = totalPrice;
    }

    /**
     * 获取订单id
     * 
     * @return 订单id
     */
    public int getId() {
        return this.id;
    }

    /**
     * 设置订单id
     * 
     * @param id
     *            订单id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 得到订单时间
     * 
     * @return 订单时间
     */
    public String getTime() {
        return this.time;
    }

    /**
     * 设置订单时间
     * 
     * @param time
     *            订单时间
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 获取订单总价格
     * 
     * @return 订单总价格
     */
    public Double getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * 设置订单总价格
     * 
     * @param totalPrice
     *            订单总价格
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
