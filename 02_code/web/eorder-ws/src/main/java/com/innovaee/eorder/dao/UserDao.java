/***********************************************
 * Filename        : UserDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.innovaee.eorder.entity.User;
import com.innovaee.eorder.util.HibernateUtil;

/**
 * @Title: UserDao
 * @Description: 用户数据访问对象
 * 
 * @version V1.0
 */
public class UserDao {

    /**
     * 根据手机号码得到用户
     * 
     * @param cellphone
     *            手机号码
     * @return 用户
     */
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