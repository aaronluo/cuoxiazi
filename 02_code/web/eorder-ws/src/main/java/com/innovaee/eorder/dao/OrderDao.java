/***********************************************
 * Filename        : OrderDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao;

import java.util.List;

import org.hibernate.Session;

import com.innovaee.eorder.entity.Order;
import com.innovaee.eorder.util.HibernateUtil;

/**
 * @Title: OrderDao
 * @Description: 订单数据访问对象
 * 
 * @version V1.0
 */
public class OrderDao {

    /**
     * 根据用户ID得到订单列表
     * 
     * @param memberId
     *            用户ID
     * @return 订单列表
     */
    @SuppressWarnings("unchecked")
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

}