/***********************************************
 * Filename       : CategoryService.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.service;

import java.util.List;

import com.innovaee.eorder.dao.CategoryDao;
import com.innovaee.eorder.entity.Category;

/**
 * @Title: CategoryService
 * @Description: 菜品分类服务
 * 
 * @version V1.0
 */
public class CategoryService extends BaseService {

    /** 菜品分类数据访问实现类对象 */
    private CategoryDao categoryDao = new CategoryDao();

    /**
     * 获取所有分类
     * 
     * @return 所有分类列表
     */
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

}