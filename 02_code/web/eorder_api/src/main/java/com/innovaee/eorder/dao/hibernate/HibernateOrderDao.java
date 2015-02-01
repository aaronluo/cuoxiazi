package com.innovaee.eorder.dao.hibernate;

import java.util.List;

import com.innovaee.eorder.dao.OrderDao;
import com.innovaee.eorder.entity.Order;

public class HibernateOrderDao extends HibernateBaseDao<Order> implements
        OrderDao {
    public List<Order> getOrdersByMemberId(Integer memberId) {
        String hql = "from Order as order where order.member.id = ?";
        Object[] paras = { memberId };
        List<Order> users = getPage(0, 999, hql, paras);
        return users;
    }
}