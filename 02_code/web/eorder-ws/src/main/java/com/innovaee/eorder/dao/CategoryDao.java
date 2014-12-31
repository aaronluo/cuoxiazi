/***********************************************
 * Filename        : CategoryDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao;

import com.innovaee.eorder.bean.Category;

import java.util.List;

/**
 * @Title: CategoryDao
 * @Description: 分类数据访问对象接口
 * @author coderdream@gmail.com
 * @version V1.0
 */
public interface CategoryDao {

    /**
     * 根据分类ID查找分类
     * 
     * @param id
     *            分类ID
     * @return 分类实体
     */
    public Category getCategoryById(String id);

    /**
     * 根据分类ID删除分类
     * 
     * @param id
     *            分类实体
     * @return 分类是否删除成功
     */
    public boolean deleteCategoryById(String id);

    /**
     * 创建分类
     * 
     * @param category
     *            待创建的分类
     * @return 分类是否创建成功
     */
    public boolean createCategory(Category category);

    /**
     * 更新分类
     * 
     * @param category
     *            待更新的分类
     * @return 分类是否更新成功
     */
    public boolean updateCategory(Category category);

    /**
     * 获取所有分类
     * 
     * @return 所有分类列表
     */
    public List<Category> getAllCategories();
}