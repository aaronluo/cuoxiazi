/***********************************************
 * Filename       : OrderService.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.service;

import java.util.List;

import com.innovaee.eorder.dao.OrderDao;
import com.innovaee.eorder.dao.OrderItemDao;
import com.innovaee.eorder.entity.Order;
import com.innovaee.eorder.entity.OrderItem;

/**
 * @Title: OrderService
 * @Description: 订单服务
 * @version V1.0
 */
public class OrderService extends BaseService {

    /** 订单数据访问实现类对象 */
    private OrderDao orderDao = new OrderDao();

    /** 订单明细数据访问实现类对象 */
    private OrderItemDao orderItemDao = new OrderItemDao();

    /**
     * 根据用户ID得到订单列表
     * 
     * @param memberId
     *            用户ID
     * @return 订单列表
     */
    public List<Order> getOrdersByMemberId(Integer memberId) {
        return orderDao.getOrdersByMemberId(memberId);
    }

    /**
     * 根据订单ID得到订单明细列表
     * 
     * @param orderId
     *            订单ID
     * @return 订单明细列表
     */
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
        return orderItemDao.getOrderItemsByOrderId(orderId);
    }

}