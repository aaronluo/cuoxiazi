/***********************************************
 * Filename		: OrderItemDaoImpl.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.innovaee.eorder.bean.OrderItem;
import com.innovaee.eorder.dao.OrderItemDao;
import com.innovaee.eorder.util.HibernateUtil;

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
		Session s = null;
		Transaction t = null;
		OrderItem orderItem = null;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			String hql = "from OrderItem where orderItemId=" + orderItemId;
			Query query = s.createQuery(hql);
			orderItem = (OrderItem) query.uniqueResult();
			t.commit();
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
		return orderItem;
	}

	@Override
	public boolean deleteOrderItemById(String id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		boolean flag = false;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderItemId(Integer.parseInt(id));
			s.delete(orderItem);
			t.commit();
			flag = true;
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
		return flag;
	}

	@Override
	public boolean createOrderItem(OrderItem orderItem) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		boolean flag = false;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			s.save(orderItem);
			t.commit();
			flag = true;
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
		return flag;
	}

	@Override
	public boolean updateOrderItem(OrderItem orderItem) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		boolean flag = false;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			s.update(orderItem);
			t.commit();
			flag = true;
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderItem> getAllOrderItems() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		List<OrderItem> orderItems = null;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			String hql = "select * from t_order_item";
			Query query = s.createSQLQuery(hql).addEntity(OrderItem.class);
			query.setCacheable(true); // 设置缓存
			orderItems = query.list();
			t.commit();
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
		return orderItems;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderItem> getOrderItemsByOrderId(String orderId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		List<OrderItem> orderItems = null;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			String hql = "select * from t_order_item where order_id=" + orderId;
			Query query = s.createSQLQuery(hql).addEntity(OrderItem.class);
			query.setCacheable(true); // 设置缓存
			orderItems = query.list();
			t.commit();
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
		return orderItems;
	}

}
