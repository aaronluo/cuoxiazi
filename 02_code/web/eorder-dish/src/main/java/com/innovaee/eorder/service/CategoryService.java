/***********************************************
 * Filename       : CategoryService.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.service;

import com.innovaee.eorder.entity.Category;
import com.innovaee.eorder.entity.Dish;
import com.innovaee.eorder.exception.CategoryNotFoundException;
import com.innovaee.eorder.exception.DuplicateNameException;
import com.innovaee.eorder.exception.InvalidPageSizeException;
import com.innovaee.eorder.exception.PageIndexOutOfBoundExcpeiton;
import com.innovaee.eorder.vo.CategoryVO;

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
    public List<Dish> getDishesByCategoryId(final Long categoryId);

    /**
     * 添加新菜品分类
     * 
     * @param categoryVO
     *            菜品分类值对象
     * @return 新创建的菜品分类对象
     * 
     * @throws DuplicateNameException
     *             菜品分类命名重复异常
     */
    public Category addCategory(final CategoryVO categoryVO)
            throws DuplicateNameException;

    /**
     * 更新新菜品分类
     * 
     * @param categoryVO
     *            菜品分类值对象，包含需要更新的数据
     * @return 更新后的菜品分类值对象
     * @throws DuplicateNameException
     *             菜品分类命名重复异常
     * @throws CategoryNotFoundException
     *             菜品分类不存在异常
     * 
     */

    public Category updateCategory(final CategoryVO categoryVO)
            throws DuplicateNameException, CategoryNotFoundException;

    /**
     * 删除菜品分类
     * 
     * @param categoryId
     *            菜品分类id
     * @throws CategoryNotFoundException
     *             菜品分类不存在异常
     */
    public void deleteCategory(final Long categoryId)
            throws CategoryNotFoundException;

    /**
     * 根据指定的菜品分类ID获取菜品分类信息
     * 
     * @param categoryId
     *            菜品分类ID
     * @return 返回菜品分类对象
     * @throws CategoryNotFoundException
     *             菜品分类对象不存在异常
     */
    public Category getCategoryById(final Long categoryId)
            throws CategoryNotFoundException;

    /**
     * 根据指定的菜品分类名字查找菜品分类
     * 
     * @param categoryName
     *            菜品分类名字
     * @return 菜品分类对象
     * @throws CategoryNotFoundException
     *             菜品分类对象不存在异常
     */
    public Category getCategoryByName(final String categoryName);

    /**
     * 获取菜品分类分页数据
     * 
     * @param curPage
     *            当前分页
     * @param pageSize
     *            分页大小
     * @return
     * @throws PageIndexOutOfBoundExcpeiton
     *             分页超限异常
     * @throws InvalidPageSizeException
     */
    public List<Category> getCategoriesByPage(int curPage, int pageSize)
            throws PageIndexOutOfBoundExcpeiton, InvalidPageSizeException;

    /**
     * 根据分页大小，获取总页数
     * 
     * @param pageSize
     *            分页大小
     * @return
     * @throws InvalidPageSizeException
     *             非法的分页大小异常，分页大小必须大于0
     */
    public int getCategoryPageCount(int pageSize)
            throws InvalidPageSizeException;

    /**
     * 获得总记录条数
     * 
     * @return 总记录条数
     */
    public Integer count();

}