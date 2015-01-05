/***********************************************
 * Filename        : CategoryDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao;

import java.util.List;

import com.innovaee.eorder.bean.Category;

/**
 * @Title: CategoryDao
 * @Description: 分类数据访问对象接口
 * 
 * @version V1.0
 */
public interface CategoryDao {

    /**
     * 获取所有分类
     * 
     * @return 所有分类列表
     */
    public List<Category> getAllCategories();
}