package com.innovaee.eorder.service;

import java.util.List;

import com.innovaee.eorder.entity.Order;

public interface OrderService {

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