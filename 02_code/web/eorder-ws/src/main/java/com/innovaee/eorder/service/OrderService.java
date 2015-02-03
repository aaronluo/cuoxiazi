/***********************************************
 * Filename       : OrderService.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.service;

import com.innovaee.eorder.entity.Order;

import java.util.List;

/**
 * @Title: OrderService
 * @Description: 订单服务
 * @version V1.0
 */
public interface OrderService {

    /**
     * 根据订单ID得到订单
     * 
     * @param orderId
     *            订单ID
     * @return 订单
     */
    public Order getOrderById(Integer userId);

    /**
     * 根据用户ID得到订单列表
     * 
     * @param memberId
     *            用户ID
     * @return 订单列表
     */
    public List<Order> getOrdersByMemberId(Integer memberId);
}