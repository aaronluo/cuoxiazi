/***********************************************
 * Filename        : UserLevelDaoImpl.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import com.innovaee.eorder.bean.UserLevel;
import com.innovaee.eorder.dao.UserLevelDao;
import com.innovaee.eorder.util.HibernateUtil;

/**
 * @Title: UserLevelDaoImpl
 * @Description: 用户等级数据访问对象实现类
 * 
 * @version V1.0
 */
public class UserLevelDaoImpl implements UserLevelDao {

    /* (non-Javadoc)
     * @see com.innovaee.eorder.dao.UserLevelDao#getUserLevelById(java.lang.String)
     */
    @Override
    public UserLevel getUserLevelById(String id) {
        Session session = HibernateUtil.getSession();
        HibernateUtil.beginTransaction();
        String hql = "from UserLevel where levelId=" + id;
        Query query = session.createQuery(hql);
        UserLevel userLevel = (UserLevel) query.uniqueResult();
        HibernateUtil.commitTransaction();
        HibernateUtil.closeSession();
        return userLevel;
    }

}