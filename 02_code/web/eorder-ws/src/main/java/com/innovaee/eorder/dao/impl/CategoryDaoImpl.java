/***********************************************
 * Filename        : CategoryDaoImpl.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.innovaee.eorder.bean.Category;
import com.innovaee.eorder.dao.CategoryDao;
import com.innovaee.eorder.util.HibernateUtil;

/**
 * @Title: CategoryDaoImpl
 * @Description: 菜品分类数据访问接口实现
 * 
 * @version V1.0
 */
public class CategoryDaoImpl implements CategoryDao {

    /**
     * 获取所有分类
     * 
     * @return 所有分类列表
     */
    @SuppressWarnings("unchecked")
    @Override
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