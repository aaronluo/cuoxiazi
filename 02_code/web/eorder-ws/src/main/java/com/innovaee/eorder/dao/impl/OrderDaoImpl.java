/***********************************************
 * Filename        : OrderDaoImpl.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao.impl;

import com.innovaee.eorder.bean.Order;
import com.innovaee.eorder.dao.OrderDao;
import com.innovaee.eorder.util.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @Title: OrderDaoImpl
 * @Description: 订单数据访问对象实现类
*
 * @version V1.0
 */
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order getOrderById(String id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        Order order = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "from Order where orderId=" + id;
            Query query = session.createQuery(hql);
            order = (Order) query.uniqueResult();
            transaction.commit();
        } catch (Exception err) {
            transaction.rollback();
            err.printStackTrace();
        } finally {
            session.close();
        }
        return order;
    }

    @Override
    public boolean deleteOrderById(String id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Order order = new Order();
            order.setOrderId(Integer.parseInt(id));
            session.delete(order);
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
    public boolean createOrder(Order order) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
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
    public boolean updateOrder(Order order) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(order);
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
    public List<Order> getAllOrders() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        List<Order> orders = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "select * from t_order";
            Query query = session.createSQLQuery(hql).addEntity(Order.class);
            query.setCacheable(true); // 设置缓存
            orders = query.list();
            transaction.commit();
        } catch (Exception err) {
            transaction.rollback();
            err.printStackTrace();
        } finally {
            session.close();
        }
        return orders;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> getOrdersByMemberId(String memberId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        List<Order> orders = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "select * from t_order where member_id=" + memberId;
            Query query = session.createSQLQuery(hql).addEntity(Order.class);
            query.setCacheable(true); // 设置缓存
            orders = query.list();
            transaction.commit();
        } catch (Exception err) {
            transaction.rollback();
            err.printStackTrace();
        } finally {
            session.close();
        }
        return orders;
    }

}