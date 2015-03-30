/***********************************************
 * Filename       : CategoryServiceImpl.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.service.impl;

import com.innovaee.eorder.dao.CategoryDao;
import com.innovaee.eorder.dao.DishDao;
import com.innovaee.eorder.entity.Category;
import com.innovaee.eorder.entity.Dish;
import com.innovaee.eorder.exception.CategoryNotFoundException;
import com.innovaee.eorder.exception.DuplicateNameException;
import com.innovaee.eorder.exception.InvalidPageSizeException;
import com.innovaee.eorder.exception.PageIndexOutOfBoundExcpeiton;
import com.innovaee.eorder.service.CategoryService;
import com.innovaee.eorder.support.Constants;
import com.innovaee.eorder.support.MessageUtil;
import com.innovaee.eorder.vo.CategoryVO;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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

    /** 菜品数据访问实现类对象 */
    private DishDao dishDao;

    /**
     * 获取所有分类
     * 
     * @return 所有分类列表
     */
    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryDao.loadAll();
        
        for(Category category : categories) {
            if (category.getName().equals(Constants.DEFAULT_CATEGORY)) {
                categories.remove(category);
                break;
            }
        }
        
        return categories;
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
            for (Dish dish : category.getDishes()) {
                // REST服务只获取在售的菜品
                if (dish.isOnSell()) {
                    result.add(dish);
                }
            }
        }

        return result;
    }

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
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public Category addCategory(CategoryVO categoryVO)
            throws DuplicateNameException {
        Category category = null;

        // 1. 检查是否有同名菜品分类
        if (null == categoryDao.getCategoryByName(categoryVO.getName())) {
            category = new Category();
            category.setName(categoryVO.getName());
            category.setPicPath(categoryVO.getPicPath());
            category.setCreateDate(new Date());

            Long categoryId = categoryDao.save(category);
            category = categoryDao.get(categoryId);
        } else {
            throw new DuplicateNameException("categoryName:"
                    + categoryVO.getName());
        }

        return category;
    }

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
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public Category updateCategory(CategoryVO categoryVO)
            throws DuplicateNameException, CategoryNotFoundException {
        // 1. 检查需要更新的菜品分类是否存在
        Category category = this.getCategoryById(categoryVO.getId());

        Category checkedCategory = categoryDao.getCategoryByName(categoryVO
                .getName());
        // 2. 检查是否有同名菜品分类
        if (checkedCategory != null
                && checkedCategory.getId() != categoryVO.getId()) {
            throw new DuplicateNameException("categoryName:"
                    + categoryVO.getName());
        } else {
            // 3. 更新菜品分类
            category.setName(categoryVO.getName());
            category.setPicPath(categoryVO.getPicPath());
            category.setUpdateDate(new Date());

            categoryDao.update(category);
        }

        return category;
    }

    /**
     * 删除菜品分类
     * 
     * @param categoryId
     *            菜品分类id
     * @throws CategoryNotFoundException
     *             菜品分类不存在异常
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public void deleteCategory(Long categoryId)
            throws CategoryNotFoundException {
        // 1. 检查需要删除的菜品分类是否存在
        Category category = this.getCategoryById(categoryId);

        // 2. 将要删除菜品分类下的菜品重置为默认分类
        Category defaultCategory = categoryDao
                .getCategoryByName(Constants.DEFAULT_CATEGORY);

        if (null == defaultCategory) {
            throw new CategoryNotFoundException("categoryName:"
                    + Constants.DEFAULT_CATEGORY);
        } else {
            for (Dish dish : category.getDishes()) {
                dish.setCategory(defaultCategory);
                dishDao.update(dish);
            }
        }

        // 3. 删除菜品分类
        categoryDao.delete(category);
    }

    /**
     * 根据指定的菜品分类ID获取菜品分类信息
     * 
     * @param categoryId
     *            菜品分类ID
     * @return 返回菜品分类对象
     * @throws CategoryNotFoundException
     *             菜品分类对象不存在异常
     */
    @Override
    public Category getCategoryById(Long categoryId)
            throws CategoryNotFoundException {
        Category category = categoryDao.get(categoryId);

        if (null == category) {
            throw new CategoryNotFoundException(MessageUtil.getMessage(
                    "category_id", "" + categoryId));
        }
        return category;
    }

    /**
     * 根据指定的菜品分类名字查找菜品分类
     * 
     * @param categoryName
     *            菜品分类名字
     * @return 菜品分类对象
     * @throws CategoryNotFoundException
     *             菜品分类对象不存在异常
     */
    @Override
    public Category getCategoryByName(String categoryName)
            throws CategoryNotFoundException {
        Category category = categoryDao.getCategoryByName(categoryName);

        if (null == category) {
            throw new CategoryNotFoundException(MessageUtil.getMessage(
                    "category_name", categoryName));
        }

        return category;
    }

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
    @Override
    public List<Category> getCategoriesByPage(int curPage, int pageSize)
            throws PageIndexOutOfBoundExcpeiton, InvalidPageSizeException {
        List<Category> categories = new ArrayList<Category>();
        // 1. 计算总页数
        int totalPage =getCategoryPageCount(pageSize);
        // 2. 如果当前分页不是一个非法的分页， 则抛出异常
        if (curPage < 1 || curPage > totalPage) {
            throw new PageIndexOutOfBoundExcpeiton(totalPage, curPage);
        } else {
            int startIndex = (curPage - 1) * pageSize;
            categories = categoryDao.getPage(startIndex, pageSize,
                    "FROM Category");
        }

        return categories;
    }

    /**
     * 根据分页大小，获取总页数
     * 
     * @param pageSize
     *            分页大小
     * @return
     * @throws InvalidPageSizeException
     *             非法的分页大小异常，分页大小必须大于0
     */
    @Override
    public int getCategoryPageCount(int pageSize)
            throws InvalidPageSizeException {
        if (pageSize <= 0) {
            throw new InvalidPageSizeException(pageSize);
        }

        //去除不需要显示的默认分类
        int totalCategories = categoryDao.count() - 1;

        return totalCategories % pageSize == 0 ? totalCategories / pageSize
                : totalCategories / pageSize + 1;
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public DishDao getDishDao() {
        return dishDao;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }
}