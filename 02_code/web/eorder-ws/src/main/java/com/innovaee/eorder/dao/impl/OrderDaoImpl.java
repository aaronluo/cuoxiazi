/***********************************************
 * Filename		: OrderDaoImpl.java																									: DishService.java
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

import com.innovaee.eorder.bean.Order;
import com.innovaee.eorder.dao.OrderDao;
import com.innovaee.eorder.util.HibernateUtil;

/**   
* @Title: OrderDaoImpl 
* @Description: 订单数据访问对象实现类
* @author coderdream@gmail.com   
* @version V1.0   
*/
public class OrderDaoImpl implements OrderDao {

	@Override
	public Order getOrderById(String id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		Order order = null;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			String hql = "from Order where orderId=" + id;
			Query query = s.createQuery(hql);
			order = (Order) query.uniqueResult();
			t.commit();
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
		return order;
	}

	@Override
	public boolean deleteOrderById(String id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		boolean flag = false;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			Order order = new Order();
			order.setOrderId(Integer.parseInt(id));
			s.delete(order);
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
	public boolean createOrder(Order order) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		boolean flag = false;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			s.save(order);
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
	public boolean updateOrder(Order order) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		boolean flag = false;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			s.update(order);
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
	public List<Order> getAllOrders() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		List<Order> orders = null;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			String hql = "select * from t_order";
			Query query = s.createSQLQuery(hql).addEntity(Order.class);
			query.setCacheable(true); // 设置缓存
			orders = query.list();
			t.commit();
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
		return orders;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrdersByMemberId(String memberId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		List<Order> orders = null;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			String hql = "select * from t_order where member_id=" + memberId;
			Query query = s.createSQLQuery(hql).addEntity(Order.class);
			query.setCacheable(true); // 设置缓存
			orders = query.list();
			t.commit();
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
		return orders;
	}

}