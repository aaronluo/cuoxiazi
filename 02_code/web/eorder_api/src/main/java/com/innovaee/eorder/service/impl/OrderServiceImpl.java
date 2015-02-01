package com.innovaee.eorder.service.impl;

import java.util.List;

import com.innovaee.eorder.dao.OrderDao;
import com.innovaee.eorder.entity.Order;
import com.innovaee.eorder.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

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