/***********************************************
 * Filename		: CategoryDaoImpl.java																									: DishService.java
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

import com.innovaee.eorder.bean.Category;
import com.innovaee.eorder.dao.CategoryDao;
import com.innovaee.eorder.util.HibernateUtil;

/**   
* @Title: CategoryDaoImpl 
* @Description: 菜品分类数据访问接口实现 
* @author coderdream@gmail.com   
* @version V1.0   
*/
public class CategoryDaoImpl implements CategoryDao {

	@Override
	public Category getCategoryById(String id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		Category category = null;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			String hql = "from Category where categoryId=" + id;
			Query query = s.createQuery(hql);
			category = (Category) query.uniqueResult();
			t.commit();
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
		return category;
	}

	@Override
	public boolean deleteCategoryById(String id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		boolean flag = false;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			Category category = new Category();
			category.setCategoryId(Integer.parseInt(id));
			s.delete(category);
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
	public boolean createCategory(Category category) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		boolean flag = false;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			s.save(category);
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
	public boolean updateCategory(Category category) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		boolean flag = false;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			s.update(category);
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
	public List<Category> getAllCategories() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session s = null;
		Transaction t = null;
		List<Category> uesrs = null;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			String hql = "select * from t_category";
			Query query = s.createSQLQuery(hql).addEntity(Category.class);
			query.setCacheable(true); // 设置缓存
			uesrs = query.list();
			t.commit();
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
		return uesrs;
	}

}
