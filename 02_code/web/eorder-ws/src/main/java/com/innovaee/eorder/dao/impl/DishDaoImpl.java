/***********************************************
 * Filename        : DishDaoImpl.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao.impl;

import com.innovaee.eorder.bean.Dish;
import com.innovaee.eorder.dao.DishDao;
import com.innovaee.eorder.util.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

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
        Session session = null;
        Transaction transaction = null;
        Dish dish = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "from Dish where dishId=" + id;
            Query query = session.createQuery(hql);
            dish = (Dish) query.uniqueResult();
            transaction.commit();
        } catch (Exception err) {
            transaction.rollback();
            err.printStackTrace();
        } finally {
            session.close();
        }
        return dish;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Dish> getDishesByCategoryId(String categoryId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;

        List<Dish> dishes = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "from Dish where categoryId=" + categoryId;
            Query query = session.createQuery(hql);
            query.setCacheable(true); // 设置缓存
            dishes = query.list();
            transaction.commit();
        } catch (Exception err) {
            transaction.rollback();
            err.printStackTrace();
        } finally {
            session.close();
        }
        return dishes;
    }

    @Override
    public boolean deleteDishById(String id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Dish dish = new Dish();
            dish.setDishId(Integer.parseInt(id));
            session.delete(dish);
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
    public boolean createDish(Dish dish) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(dish);
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
    public boolean updateDish(Dish dish) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(dish);
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
    public List<Dish> getAllDishes() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        List<Dish> dishes = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "select * from t_dish";
            Query query = session.createSQLQuery(hql).addEntity(Dish.class);
            query.setCacheable(true); // 设置缓存
            dishes = query.list();
            transaction.commit();
        } catch (Exception err) {
            transaction.rollback();
            err.printStackTrace();
        } finally {
            session.close();
        }
        return dishes;
    }

}
