/***********************************************
 * Filename        : DishDaoImpl.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.innovaee.eorder.bean.Dish;
import com.innovaee.eorder.dao.DishDao;
import com.innovaee.eorder.util.HibernateUtil;

/**
 * @Title: DishDaoImpl
 * @Description: 菜品数据访问对象接口
 * 
 * @version V1.0
 */
public class DishDaoImpl implements DishDao {

    /* (non-Javadoc)
     * @see com.innovaee.eorder.dao.DishDao#getDishesByCategoryId(java.lang.Integer)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Dish> getDishesByCategoryId(Integer categoryId) {
        Session session = HibernateUtil.getSession();
        HibernateUtil.beginTransaction();
        String hql = "from Dish where categoryId=?";
        Query query = session.createQuery(hql).setInteger(0, categoryId);
        query.setCacheable(true); // 设置缓存
        List<Dish> dishes = query.list();
        HibernateUtil.commitTransaction();
        HibernateUtil.closeSession();
        return dishes;
    }

    @Override
    public Dish getDishById(Integer dishId) {
        Session session = HibernateUtil.getSession();
        HibernateUtil.beginTransaction();
        String hql = "from Dish where dishId=?";
        Query query = session.createQuery(hql).setInteger(0, dishId);
        Dish dish = (Dish) query.uniqueResult();
        HibernateUtil.commitTransaction();
        HibernateUtil.closeSession();
        return dish;
    }

}