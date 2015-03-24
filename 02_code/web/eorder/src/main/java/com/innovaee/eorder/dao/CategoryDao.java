/***********************************************
 * Filename       : CategoryDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao;

import com.innovaee.eorder.entity.Category;

import java.util.List;

/**
 * @Title: CategoryDao
 * @Description: 分类数据访问对象接口
 * 
 * @version V1.0
 */
public interface CategoryDao extends BaseDao<Category> {

    /**
     * 根据指定菜品分类名称查找菜品分类
     * 
     * @param name
     *            菜品分类名称
     * @return 菜品分类对象或者null
     */
    public Category getCategoryByName(final String name);
    
   public List<Category> getPage(int startIndex, int pageSize);
}
