/***********************************************
 * Filename        : DishServiceTest.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created         : 03/17/2015
 ************************************************/

package com.innovaee.eorder.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.innovaee.eorder.entity.Dish;
import com.innovaee.eorder.exception.CategoryNotFoundException;
import com.innovaee.eorder.exception.DishNotFoundException;
import com.innovaee.eorder.exception.DuplicateNameException;
import com.innovaee.eorder.exception.InvalidPageSizeException;
import com.innovaee.eorder.vo.DishVO;

import org.junit.Test;

import javax.annotation.Resource;

/**
 * @Title: DishServiceTest
 * @Description: DishService 单元测试类
 * 
 * @version V1.0
 */
public class DishServiceTest extends BaseSpringTestCase {

    @Resource
    private DishService dishService;

    /**
     * 单元测试 - 通过错误的菜品ID获取菜品
     */
    @Test
    public void getDishByInvalidIdTest() {
        try {
            dishService.getDishById(1000L);
        } catch (Exception exception) {
            assertEquals(exception.getClass(), DishNotFoundException.class);
        }
    }

    /**
     * 单元测试 - 通过正确的菜品ID获取菜品
     */
    @Test
    public void getDishByIdTest() throws DishNotFoundException {
        Dish dish = dishService.getDishById(1L);

        assertEquals(dish.getName(), "手撕包菜");
    }

    /**
     * 单元测试 - 通过错误的菜品名获取菜品
     */
    @Test
    public void getDishByInvalidNameTest() {
        try {
            dishService.getDishByName("手撕包菜1");
        } catch (Exception exception) {
            assertEquals(exception.getClass(), DishNotFoundException.class);
        }
    }

    /**
     * 单元测试 - 通过正确的菜品名获取菜品
     */
    @Test
    public void getDishByNameTest() throws DishNotFoundException {
        Dish dish = dishService.getDishByName("手撕包菜");

        assertNotNull(dish);
    }

    /**
     * 单元测试 - 添加重名菜品
     */
    @Test
    public void addDishWithDuplicateNameTest() {
        DishVO dishVO = new DishVO();

        dishVO.setCategoryId(1L);
        dishVO.setMisc("misc");
        dishVO.setName("手撕包菜");
        dishVO.setPrice(150.0f);
        dishVO.setPicPath("");

        try {
            dishService.addDish(dishVO);
        } catch (Exception exception) {
            assertEquals(exception.getClass(), DuplicateNameException.class);
        }
    }

    /**
     * 添加错误分类的菜品信息
     */
    @Test
    public void addDishWithInvalidCategoryTest() {
        DishVO dishVO = new DishVO();

        dishVO.setCategoryId(1L);
        dishVO.setMisc("misc");
        dishVO.setName("手撕包菜1");
        dishVO.setPrice(150.0f);
        dishVO.setPicPath("");

        try {
            dishService.addDish(dishVO);
        } catch (Exception exception) {
            assertEquals(exception.getClass(), CategoryNotFoundException.class);
        }
    }

    /**
     * 单元测试 - 添加菜品信息
     * 
     * @throws CategoryNotFoundException
     * @throws DuplicateNameException
     */
    @Test
    public void addDishTest() throws DuplicateNameException,
            CategoryNotFoundException {
        DishVO dishVO = new DishVO();

        dishVO.setCategoryId(1L);
        dishVO.setMisc("misc");
        dishVO.setName("手撕包菜1");
        dishVO.setPrice(150.0f);
        dishVO.setPicPath("");

        Dish dish = dishService.addDish(dishVO);

        assertNotNull(dish);
        assertTrue(dish.getId() > 0L);
    }

    /**
     * 单元测试 - 使用已存在的菜品名更新菜品
     * 
     * @throws DishNotFoundException
     */
    @Test
    public void updateDishWithDuplicateNameTest() throws DishNotFoundException {
        String duplicateName = "无油版左公鸡";
        Dish dish = dishService.getDishById(1L);
        DishVO dishVO = new DishVO();

        dishVO.setId(dish.getId());
        dishVO.setCategoryId(dish.getCategory().getId());
        dishVO.setMisc(dish.getMisc());
        dishVO.setName(duplicateName);
        dishVO.setPicPath(dish.getPicPath());
        dishVO.setPrice(dish.getPrice());

        try {
            dishService.updateDish(dishVO);
        } catch (Exception exception) {
            assertEquals(exception.getClass(), DuplicateNameException.class);
        }
    }

    /**
     * 单元测试 - 使用错误的菜品分类信息更新菜品
     * 
     * @throws DishNotFoundException
     */
    @Test
    public void updateDishWithInvalidCategoryTest()
            throws DishNotFoundException {
        Dish dish = dishService.getDishById(1L);
        DishVO dishVO = new DishVO();

        dishVO.setId(dish.getId());
        dishVO.setCategoryId(1000L);
        dishVO.setMisc(dish.getMisc());
        dishVO.setName(dish.getName());
        dishVO.setPicPath(dish.getPicPath());
        dishVO.setPrice(dish.getPrice());

        try {
            dishService.updateDish(dishVO);
        } catch (Exception exception) {
            assertEquals(exception.getClass(), CategoryNotFoundException.class);
        }
    }

    /**
     * 单元测试 - 更新菜品信息
     * @throws DishNotFoundException
     * @throws CategoryNotFoundException
     * @throws DuplicateNameException
     */
    @Test
    public void updateDishTest() throws DishNotFoundException,
            CategoryNotFoundException, DuplicateNameException {
        Dish dish = dishService.getDishById(1L);
        DishVO dishVO = new DishVO();

        dishVO.setId(dish.getId());
        dishVO.setCategoryId(dish.getCategory().getId());
        dishVO.setMisc(dish.getMisc());
        dishVO.setName(dish.getName());
        dishVO.setPicPath(dish.getPicPath());
        dishVO.setPrice(dish.getPrice());

        dishService.updateDish(dishVO);
    }
    
    /**
     * 单元测试 - 删除非法ID指定的菜品信息
     */
    @Test
    public void deleteDishWithInvalidIdTest() {
        try {
            dishService.deleteDish(1000L);
        } catch (Exception exception) {
            assertEquals(exception.getClass(), DishNotFoundException.class);
        }
    }
    
    /**
     * 单元测试 - 删除菜品信息
     */
    @Test
    public void deleteDishTest() throws DishNotFoundException {   
        dishService.deleteDish(1L);
    }
    
    /**
     * 单元测试 - 菜品分页总数
     * @throws InvalidPageSizeException
     * @throws CategoryNotFoundException
     */
    @Test
    public void getDishPageCountTest() throws InvalidPageSizeException, CategoryNotFoundException {
         Long categoryId = 1L;
         
         int pageCount = dishService.getDishPageCount(5, categoryId);
         
         assertEquals(2, pageCount);
    }
}
