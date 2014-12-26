/***********************************************
 * Filename		: DishDaoImpl.java																									: DishService.java
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

import com.innovaee.eorder.bean.Dish;
import com.innovaee.eorder.dao.DishDao;
import com.innovaee.eorder.util.HibernateUtil;

/**   
* @Title: DishDaoImpl 
* @Description: 菜品数据访问对象接口
* @author coderdream@gmail.com   
* @version V1.0   
*/
public class DishDaoImpl implements DishDao {

	@Override
	public Dish getDishById(String id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		Dish dish = null;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			String hql = "from Dish where dishId=" + id;
			Query query = s.createQuery(hql);
			dish = (Dish) query.uniqueResult();
			t.commit();
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
		return dish;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Dish> getDishesByCategoryId(String categoryId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;

		List<Dish> dishes = null;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			String hql = "from Dish where categoryId=" + categoryId;
			Query query = s.createQuery(hql);
			query.setCacheable(true); // 设置缓存
			dishes = query.list();
			t.commit();
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
		return dishes;
	}

	@Override
	public boolean deleteDishById(String id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		boolean flag = false;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			Dish dish = new Dish();
			dish.setDishId(Integer.parseInt(id));
			s.delete(dish);
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
	public boolean createDish(Dish dish) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		boolean flag = false;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			s.save(dish);
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
	public boolean updateDish(Dish dish) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		boolean flag = false;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			s.update(dish);
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
	public List<Dish> getAllDishes() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		List<Dish> dishes = null;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			String hql = "select * from t_dish";
			Query query = s.createSQLQuery(hql).addEntity(Dish.class);
			query.setCacheable(true); // 设置缓存
			dishes = query.list();
			t.commit();
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
		return dishes;
	}

}
