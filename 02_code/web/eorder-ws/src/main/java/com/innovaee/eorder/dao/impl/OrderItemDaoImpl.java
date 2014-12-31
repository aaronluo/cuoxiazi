/***********************************************
 * Filename        : OrderItemDaoImpl.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao.impl;

import com.innovaee.eorder.bean.OrderItem;
import com.innovaee.eorder.dao.OrderItemDao;
import com.innovaee.eorder.util.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @Title: OrderItemDaoImpl
 * @Description: 订单明细数据访问对象实现类
 * @author coderdream@gmail.com
 * @version V1.0
 */
public class OrderItemDaoImpl implements OrderItemDao {

    @Override
    public OrderItem getOrderItemById(String orderItemId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        OrderItem orderItem = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "from OrderItem where orderItemId=" + orderItemId;
            Query query = session.createQuery(hql);
            orderItem = (OrderItem) query.uniqueResult();
            transaction.commit();
        } catch (Exception err) {
            transaction.rollback();
            err.printStackTrace();
        } finally {
            session.close();
        }
        return orderItem;
    }

    @Override
    public boolean deleteOrderItemById(String id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderItemId(Integer.parseInt(id));
            session.delete(orderItem);
            transaction.commit();
            flag = true;
        } catch (Exception err) {
            transaction.rollback();
            err.printStackTrace();
        } finally {
            session.close();
        }
        return flag;
    }

    @Override
    public boolean createOrderItem(OrderItem orderItem) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(orderItem);
            transaction.commit();
            flag = true;
        } catch (Exception err) {
            transaction.rollback();
            err.printStackTrace();
        } finally {
            session.close();
        }
        return flag;
    }

    @Override
    public boolean updateOrderItem(OrderItem orderItem) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(orderItem);
            transaction.commit();
            flag = true;
        } catch (Exception err) {
            transaction.rollback();
            err.printStackTrace();
        } finally {
            session.close();
        }
        return flag;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<OrderItem> getAllOrderItems() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        List<OrderItem> orderItems = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "select * from t_order_item";
            Query query = session.createSQLQuery(hql).addEntity(OrderItem.class);
            query.setCacheable(true); // 设置缓存
            orderItems = query.list();
            transaction.commit();
        } catch (Exception err) {
            transaction.rollback();
            err.printStackTrace();
        } finally {
            session.close();
        }
        return orderItems;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<OrderItem> getOrderItemsByOrderId(String orderId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        List<OrderItem> orderItems = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "select * from t_order_item where order_id=" + orderId;
            Query query = session.createSQLQuery(hql).addEntity(OrderItem.class);
            query.setCacheable(true); // 设置缓存
            orderItems = query.list();
            transaction.commit();
        } catch (Exception err) {
            transaction.rollback();
            err.printStackTrace();
        } finally {
            session.close();
        }
        return orderItems;
    }

}
