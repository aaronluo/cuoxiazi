/***********************************************
 * Filename        : DishDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.innovaee.eorder.entity.Dish;
import com.innovaee.eorder.util.HibernateUtil;

/**
 * @Title: DishDao
 * @Description: 菜品数据访问对象
 * 
 * @version V1.0
 */
public class DishDao {

    /**
     * 根据菜品ID查找菜品
     * 
     * @param id
     *            菜品ID
     * @return 菜品实体
     */
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

    /**
     * 根据分类ID得到菜品列表
     * 
     * @param categoryId
     *            分类ID
     * @return 菜品列表
     */
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