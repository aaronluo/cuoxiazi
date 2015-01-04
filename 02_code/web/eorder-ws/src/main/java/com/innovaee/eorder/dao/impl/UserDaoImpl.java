/***********************************************
 * Filename		: UserDaoImpl.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import com.innovaee.eorder.bean.User;
import com.innovaee.eorder.dao.UserDao;
import com.innovaee.eorder.util.HibernateUtil;

/**
 * @Title: UserDaoImpl
 * @Description: 用户数据访问对象实现类
 * 
 * @version V1.0
 */
public class UserDaoImpl implements UserDao {

	@Override
	public User getUserByCellphone(String cellphone) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		String hql = "from User where cellphone=?";
		Query query = session.createQuery(hql).setString(0, cellphone);
		User user = (User) query.uniqueResult();
		HibernateUtil.commitTransaction();
		HibernateUtil.closeSession();
		return user;
	}

}