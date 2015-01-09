/***********************************************
 * Filename        : CategoryDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao;

import java.util.List;

import org.hibernate.Session;

import com.innovaee.eorder.entity.Category;
import com.innovaee.eorder.util.HibernateUtil;

/**
 * @Title: CategoryDao
 * @Description: 分类数据访问对象
 * 
 * @version V1.0
 */
public class CategoryDao {

    /**
     * 获取所有分类
     * 
     * @return 所有分类列表
     */
    @SuppressWarnings("unchecked")
    public List<Category> getAllCategories() {
        Session session = HibernateUtil.getSession();
        HibernateUtil.beginTransaction();
        String hql = "from Category";
        List<Category> categories = session.createQuery(hql).list();
        HibernateUtil.commitTransaction();
        HibernateUtil.closeSession();
        return categories;
    }
}