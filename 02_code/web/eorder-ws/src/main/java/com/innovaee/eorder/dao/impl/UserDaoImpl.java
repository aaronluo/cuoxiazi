/***********************************************
 * Filename        : UserDaoImpl.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao.impl;

import com.innovaee.eorder.bean.User;
import com.innovaee.eorder.dao.UserDao;
import com.innovaee.eorder.util.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @Title: UserDaoImpl
 * @Description: 用户数据访问对象实现类
*
 * @version V1.0
 */
public class UserDaoImpl implements UserDao {

    @Override
    public User getUserById(String id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        User user = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "from User where userId=" + id;
            Query query = session.createQuery(hql);
            user = (User) query.uniqueResult();
            transaction.commit();
        } catch (Exception err) {
            transaction.rollback();
            err.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public boolean deleteUserById(String id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            User user = new User();
            user.setUserId(Integer.parseInt(id));
            session.delete(user);
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
    public boolean createUser(User user) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
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
    public boolean updateUser(User user) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(user);
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
    public List<User> getAllUseres() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        List<User> user = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "select * from t_user";
            Query query = session.createSQLQuery(hql).addEntity(User.class);
            query.setCacheable(true); // 设置缓存
            user = query.list();
            transaction.commit();
        } catch (Exception err) {
            transaction.rollback();
            err.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public User getUserByCellphone(String cellphone) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        User user = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "from User where cellphone='" + cellphone + "'";
            Query query = session.createQuery(hql);
            user = (User) query.uniqueResult();
            transaction.commit();
        } catch (Exception err) {
            transaction.rollback();
            err.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

}
