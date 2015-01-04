/***********************************************
 * Filename		: CategoryDaoImpl.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
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

	public static void main(String[] args) {
		CategoryDaoImpl categoryDaoImpl = new CategoryDaoImpl();
		List<Category> categories = categoryDaoImpl.getAllCategories();
		for (Category category : categories) {
			System.out.println("Category: " + category);
		}
		System.exit(0);
	}
}