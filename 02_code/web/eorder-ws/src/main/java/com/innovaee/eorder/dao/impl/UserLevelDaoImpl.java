/***********************************************
 * Filename		: UserLevelDaoImpl.java																									: DishService.java
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

import com.innovaee.eorder.bean.UserLevel;
import com.innovaee.eorder.dao.UserLevelDao;
import com.innovaee.eorder.util.HibernateUtil;

/**   
* @Title: UserLevelDaoImpl 
* @Description: 用户等级数据访问对象实现类
* @author coderdream@gmail.com   
* @version V1.0   
*/
public class UserLevelDaoImpl implements UserLevelDao {

	@Override
	public UserLevel getUserLevelById(String id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		UserLevel userLevel = null;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			String hql = "from UserLevel where levelId=" + id;
			Query query = s.createQuery(hql);
			userLevel = (UserLevel) query.uniqueResult();
			t.commit();
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
		return userLevel;
	}

	@Override
	public boolean deleteUserLevelById(String id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		boolean flag = false;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			UserLevel userLevel = new UserLevel();
			userLevel.setLevelId(Integer.parseInt(id));
			s.delete(userLevel);
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
	public boolean createUserLevel(UserLevel userLevel) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		boolean flag = false;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			s.save(userLevel);
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
	public boolean updateUserLevel(UserLevel userLevel) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		boolean flag = false;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			s.update(userLevel);
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
	public List<UserLevel> getAllUserLeveles() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		List<UserLevel> userLevel = null;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			String hql = "select * from t_user_level";
			Query query = s.createSQLQuery(hql).addEntity(UserLevel.class);
			query.setCacheable(true); // 设置缓存
			userLevel = query.list();
			t.commit();
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
		return userLevel;
	}

	@Override
	public UserLevel getUserLevelByUserId(String userId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		UserLevel userLevel = null;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			String hql = "from UserLevel where user_id=" + userId;
			Query query = s.createQuery(hql);
			userLevel = (UserLevel) query.uniqueResult();
			t.commit();
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
		return userLevel;
	}

}
