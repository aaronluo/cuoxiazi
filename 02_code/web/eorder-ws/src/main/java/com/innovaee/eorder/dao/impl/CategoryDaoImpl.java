/***********************************************
 * Filename        : CategoryDaoImpl.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao.impl;

import com.innovaee.eorder.bean.Category;
import com.innovaee.eorder.dao.CategoryDao;
import com.innovaee.eorder.util.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @Title: CategoryDaoImpl
 * @Description: 菜品分类数据访问接口实现
*
 * @version V1.0
 */
public class CategoryDaoImpl implements CategoryDao {

    @Override
    public Category getCategoryById(String id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        Category category = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "from Category where categoryId=" + id;
            Query query = session.createQuery(hql);
            category = (Category) query.uniqueResult();
            transaction.commit();
        } catch (Exception err) {
            transaction.rollback();
            err.printStackTrace();
        } finally {
            session.close();
        }
        return category;
    }

    @Override
    public boolean deleteCategoryById(String id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Category category = new Category();
            category.setCategoryId(Integer.parseInt(id));
            session.delete(category);
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
    public boolean createCategory(Category category) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(category);
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
    public boolean updateCategory(Category category) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(category);
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
    public List<Category> getAllCategories() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        List<Category> uesrs = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "select * from t_category";
            Query query = session.createSQLQuery(hql).addEntity(Category.class);
            query.setCacheable(true); // 设置缓存
            uesrs = query.list();
            transaction.commit();
        } catch (Exception err) {
            transaction.rollback();
            err.printStackTrace();
        } finally {
            session.close();
        }
        return uesrs;
    }

}
