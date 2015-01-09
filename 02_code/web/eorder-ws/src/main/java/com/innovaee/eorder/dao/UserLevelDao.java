/***********************************************
 * Filename        : UserLevelDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.innovaee.eorder.entity.UserLevel;
import com.innovaee.eorder.util.HibernateUtil;

/**
 * @Title: UserLevelDao
 * @Description: 用户等级数据访问对象
 * 
 * @version V1.0
 */
public class UserLevelDao {

    /**
     * 根据用户等级ID查找用户等级
     * 
     * @param id
     *            用户等级ID
     * @return 用户等级实体
     */
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