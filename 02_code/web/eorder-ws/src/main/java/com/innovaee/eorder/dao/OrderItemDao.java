/***********************************************
 * Filename        : OrderItemDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao;

import java.util.List;

import org.hibernate.Session;

import com.innovaee.eorder.entity.OrderItem;
import com.innovaee.eorder.util.HibernateUtil;

/**
 * @Title: OrderItemDao
 * @Description: 订单明细明细数据访问对象
 * 
 * @version V1.0
 */
public class OrderItemDao {

    /**
     * 根据订单ID得到订单明细列表
     * 
     * @param orderId
     *            订单ID
     * @return 订单明细列表
     */
    @SuppressWarnings("unchecked")
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
        Session session = HibernateUtil.getSession();
        HibernateUtil.beginTransaction();
        String hql = "from OrderItem as OI where OI.orderId=?";
        List<OrderItem> orderItems = session.createQuery(hql)
                .setInteger(0, orderId).list();
        HibernateUtil.commitTransaction();
        HibernateUtil.closeSession();
        return orderItems;
    }
}