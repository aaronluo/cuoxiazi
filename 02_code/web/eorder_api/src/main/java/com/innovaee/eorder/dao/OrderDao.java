package com.innovaee.eorder.dao;

import java.util.List;

import com.innovaee.eorder.entity.Order;

public interface OrderDao extends BaseDao<Order> {
    public List<Order> getOrdersByMemberId(Integer memberId);
}