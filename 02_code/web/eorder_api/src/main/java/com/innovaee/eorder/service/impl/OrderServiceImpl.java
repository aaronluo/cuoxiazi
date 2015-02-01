package com.innovaee.eorder.service.impl;

import java.util.List;

import com.innovaee.eorder.dao.OrderDao;
import com.innovaee.eorder.model.Order;
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
        String hql = "from Order as order where order.member.id = ?";
        Object[] paras = { memberId };
        List<Order> users = orderDao.getPage(0, 99, hql, paras);
        return users;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }
}