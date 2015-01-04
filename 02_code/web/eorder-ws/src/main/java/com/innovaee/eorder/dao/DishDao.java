/***********************************************
 * Filename		: DishDao.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao;

import java.util.List;

import com.innovaee.eorder.bean.Dish;

/**
 * @Title: DishDao
 * @Description: 菜品数据访问对象接口
 * 
 * @version V1.0
 */
public interface DishDao {

	/**
	 * 根据菜品ID查找菜品
	 * 
	 * @param id
	 *            菜品ID
	 * @return 菜品实体
	 */
	public Dish getDishById(Integer id);

	/**
	 * 根据分类ID得到菜品列表
	 * 
	 * @param categoryId
	 *            分类ID
	 * @return 菜品列表
	 */
	public List<Dish> getDishesByCategoryId(Integer categoryId);
}