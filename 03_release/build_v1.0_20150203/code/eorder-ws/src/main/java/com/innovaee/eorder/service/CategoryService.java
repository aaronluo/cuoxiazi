/***********************************************
 * Filename       : CategoryService.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.service;

import com.innovaee.eorder.entity.Category;
import com.innovaee.eorder.entity.Dish;

import java.util.List;

/**
 * @Title: CategoryService
 * @Description: 菜品分类服务
 * 
 * @version V1.0
 */
public interface CategoryService {

    /**
     * 获取所有分类
     * 
     * @return 所有分类列表
     */
    public List<Category> getAllCategories();

    /**
     * 根据菜品ID查找菜品列表
     * 
     * @param id
     *            菜品ID
     * @return 菜品实体列表
     */
    public List<Dish> getDishesByCategoryId(Integer categoryId);
}