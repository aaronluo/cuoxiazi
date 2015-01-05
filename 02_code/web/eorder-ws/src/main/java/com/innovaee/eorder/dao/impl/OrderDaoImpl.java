/***********************************************
 * Filename        : OrderDaoImpl.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.innovaee.eorder.bean.Order;
import com.innovaee.eorder.dao.OrderDao;
import com.innovaee.eorder.util.HibernateUtil;

/**
 * @Title: OrderDaoImpl
 * @Description: 订单数据访问对象实现类
 * 
 * @version V1.0
 */
public class OrderDaoImpl implements OrderDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> getOrdersByMemberId(Integer memberId) {
        Session session = HibernateUtil.getSession();
        HibernateUtil.beginTransaction();
        String hql = "from Order as O where O.memberId = ?";
        List<Order> orders = session.createQuery(hql).setInteger(0, memberId)
                .list();
        HibernateUtil.commitTransaction();
        HibernateUtil.closeSession();
        return orders;
    }

    public static void main(String[] args) {
        Integer memberId = 9;
        OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
        List<Order> orders = orderDaoImpl.getOrdersByMemberId(memberId);
        for (Order order : orders) {
            System.out.println("Order: " + order);
        }
        System.exit(0);
    }

}