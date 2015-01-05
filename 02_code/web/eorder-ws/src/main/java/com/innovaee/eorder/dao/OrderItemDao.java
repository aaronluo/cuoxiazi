/***********************************************
 * Filename        : OrderItemDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao;

import java.util.List;

import com.innovaee.eorder.bean.OrderItem;

/**
 * @Title: OrderItemDao
 * @Description: 订单明细明细数据访问对象接口
 * 
 * @version V1.0
 */
public interface OrderItemDao {

    /**
     * 根据订单ID得到订单明细列表
     * 
     * @param orderId
     *            订单ID
     * @return 订单明细列表
     */
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId);
}