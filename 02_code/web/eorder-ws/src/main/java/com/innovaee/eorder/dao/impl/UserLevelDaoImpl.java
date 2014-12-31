/***********************************************
 * Filename        : UserLevelDaoImpl.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao.impl;

import com.innovaee.eorder.bean.UserLevel;
import com.innovaee.eorder.dao.UserLevelDao;
import com.innovaee.eorder.util.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @Title: UserLevelDaoImpl
 * @Description: 用户等级数据访问对象实现类
*
 * @version V1.0
 */
public class UserLevelDaoImpl implements UserLevelDao {

    @Override
    public UserLevel getUserLevelById(String id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        UserLevel userLevel = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "from UserLevel where levelId=" + id;
            Query query = session.createQuery(hql);
            userLevel = (UserLevel) query.uniqueResult();
            transaction.commit();
        } catch (Exception err) {
            transaction.rollback();
            err.printStackTrace();
        } finally {
            session.close();
        }
        return userLevel;
    }

    @Override
    public boolean deleteUserLevelById(String id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            UserLevel userLevel = new UserLevel();
            userLevel.setLevelId(Integer.parseInt(id));
            session.delete(userLevel);
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
    public boolean createUserLevel(UserLevel userLevel) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(userLevel);
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
    public boolean updateUserLevel(UserLevel userLevel) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(userLevel);
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
    public List<UserLevel> getAllUserLeveles() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        List<UserLevel> userLevel = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "select * from t_user_level";
            Query query = session.createSQLQuery(hql).addEntity(UserLevel.class);
            query.setCacheable(true); // 设置缓存
            userLevel = query.list();
            transaction.commit();
        } catch (Exception err) {
            transaction.rollback();
            err.printStackTrace();
        } finally {
            session.close();
        }
        return userLevel;
    }

    @Override
    public UserLevel getUserLevelByUserId(String userId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        UserLevel userLevel = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "from UserLevel where user_id=" + userId;
            Query query = session.createQuery(hql);
            userLevel = (UserLevel) query.uniqueResult();
            transaction.commit();
        } catch (Exception err) {
            transaction.rollback();
            err.printStackTrace();
        } finally {
            session.close();
        }
        return userLevel;
    }

}
