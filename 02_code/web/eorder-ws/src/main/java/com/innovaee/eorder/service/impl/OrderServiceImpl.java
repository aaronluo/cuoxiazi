/***********************************************
 * Filename       : OrderServiceImpl.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.service.impl;

import com.innovaee.eorder.dao.OrderDao;
import com.innovaee.eorder.entity.Order;
import com.innovaee.eorder.service.OrderService;

import java.util.List;

/**
 * @Title: OrderServiceImpl
 * @Description: 订单服务实现类
 * @version V1.0
 */
public class OrderServiceImpl implements OrderService {

    /** 订单数据访问实现类对象 */
    private OrderDao orderDao;

    /**
     * 根据订单ID得到订单
     * 
     * @param orderId
     *            订单ID
     * @return 订单
     */
    @Override
    public Order getOrderById(Integer orderId) {
        return orderDao.get(orderId);
    }

    /**
     * 根据用户ID得到订单列表
     * 
     * @param memberId
     *            用户ID
     * @return 订单列表
     */
    @Override
    public List<Order> getOrdersByMemberId(Integer memberId) {
        return orderDao.getOrdersByMemberId(memberId);
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }
}