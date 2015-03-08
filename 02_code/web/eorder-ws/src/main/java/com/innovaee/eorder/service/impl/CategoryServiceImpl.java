/***********************************************
 * Filename       : CategoryServiceImpl.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.service.impl;

import com.innovaee.eorder.dao.CategoryDao;
import com.innovaee.eorder.entity.Category;
import com.innovaee.eorder.entity.Dish;
import com.innovaee.eorder.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: CategoryServiceImpl
 * @Description: 菜品分类服务实现类
 * 
 * @version V1.0
 */
public class CategoryServiceImpl implements CategoryService {

    /** 菜品分类数据访问实现类对象 */
    private CategoryDao categoryDao;

    /**
     * 获取所有分类
     * 
     * @return 所有分类列表
     */
    @Override
    public List<Category> getAllCategories() {
        return categoryDao.loadAll();
    }

    /**
     * 根据菜品ID查找菜品列表
     * 
     * @param id
     *            菜品ID
     * @return 菜品实体列表
     */
    @Override
    public List<Dish> getDishesByCategoryId(Long categoryId) {
        List<Dish> result = new ArrayList<Dish>();

        Category category = categoryDao.get(categoryId);

        if (null != category) {
            result.addAll(category.getDishes());
        }

        return result;
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

}