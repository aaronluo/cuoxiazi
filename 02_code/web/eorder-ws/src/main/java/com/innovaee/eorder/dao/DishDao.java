/***********************************************
 * Filename        : DishDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao;

import com.innovaee.eorder.bean.Dish;

import java.util.List;

/**
 * @Title: DishDao
 * @Description: 菜品数据访问对象接口
 * @author coderdream@gmail.com
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
    public Dish getDishById(String id);

    /**
     * 根据菜品ID删除菜品
     * 
     * @param id
     *            菜品实体
     * @return 菜品是否删除成功
     */
    public boolean deleteDishById(String id);

    /**
     * 创建菜品
     * 
     * @param category
     *            待创建的菜品
     * @return 菜品是否创建成功
     */
    public boolean createDish(Dish dish);

    /**
     * 更新菜品
     * 
     * @param category
     *            待更新的菜品
     * @return 菜品是否更新成功
     */
    public boolean updateDish(Dish dish);

    /**
     * 获取所有菜品
     * 
     * @return 所有菜品列表
     */
    public List<Dish> getAllDishes();

    /**
     * 根据分类ID得到菜品列表
     * 
     * @param categoryId
     *            分类ID
     * @return 菜品列表
     */
    public List<Dish> getDishesByCategoryId(String categoryId);
}