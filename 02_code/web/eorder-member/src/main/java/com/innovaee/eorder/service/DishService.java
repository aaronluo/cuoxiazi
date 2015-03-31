/***********************************************
 * Filename       : DishService.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.service;

import com.innovaee.eorder.entity.Dish;
import com.innovaee.eorder.exception.CategoryNotFoundException;
import com.innovaee.eorder.exception.DishNotFoundException;
import com.innovaee.eorder.exception.DuplicateNameException;
import com.innovaee.eorder.exception.InvalidPageSizeException;
import com.innovaee.eorder.exception.PageIndexOutOfBoundExcpeiton;
import com.innovaee.eorder.vo.DishVO;

import java.util.List;

/**
 * @Title: DishService
 * @Description: 菜品服务
 * @version V1.0
 */
public interface DishService {

    /**
     * 根据菜品ID查找菜品
     * 
     * @param dishId
     *            菜品ID
     * @return 菜品实体
     * @throws DishNotFoundException
     */
    public Dish getDishById(Long dishId) throws DishNotFoundException;

    /**
     * 添加新菜品
     * 
     * @param dishVO
     *            菜品信息
     * @return 添加成功的菜品
     * @throws DuplicateNameException
     *             菜品重名异常
     * @throws CategoryNotFoundException
     *             菜品分类不存在异常
     */
    public Dish addDish(DishVO dishVO) throws DuplicateNameException,
            CategoryNotFoundException;

    /**
     * 更新菜品信息
     * 
     * @param dishVO
     *            需要更新的菜品信息
     * @return 更新后的菜品
     * @throws DishNotFoundException
     *             菜品不存在异常
     * @throws CategoryNotFoundException
     *             菜品分类不存在异常
     * @throws DuplicateNameException
     *             命名重复异常
     */
    public Dish updateDish(DishVO dishVO) throws DishNotFoundException,
            CategoryNotFoundException, DuplicateNameException;

    /**
     * 删除指定id的菜品
     * 
     * @param dishId
     *            菜品id
     * @throws DishNotFoundException
     *             菜品不存在异常
     */
    public void deleteDish(Long dishId) throws DishNotFoundException;

    /**
     * 根据菜品名称获取菜品信息
     * 
     * @param dishName
     *            菜品名称
     * @return 菜品信息对象
     * @throws DishNotFoundException
     *             菜品不存在异常
     */
    public Dish getDishByName(String dishName) throws DishNotFoundException;

    /**
     * 获取菜品分页数据
     * 
     * @param curPage
     *            当前分页
     * @param pageSize
     *            分页大小
     * @param categoryId
     *            菜品分类id
     * @return
     * @throws PageIndexOutOfBoundExcpeiton
     *             分页超限异常
     * @throws CategoryNotFoundException
     *             菜品分类不存在异常
     * @throws InvalidPageSizeException 
     */
    public List<Dish> getDishesByPage(int curPage, int pageSize, Long categoryId)
            throws PageIndexOutOfBoundExcpeiton, CategoryNotFoundException, InvalidPageSizeException;

    /**
     * 获得某一菜品分类下的菜品分页总数
     * @param pageSize 分页大小 
     * @param categoryId 菜品分类id
     * @return
     * @throws InvalidPageSizeException 非法分页大小异常
     * @throws CategoryNotFoundException 菜品分类未找到异常
     */
    public int getDishPageCount(int pageSize, Long categoryId)
            throws InvalidPageSizeException, CategoryNotFoundException;
}