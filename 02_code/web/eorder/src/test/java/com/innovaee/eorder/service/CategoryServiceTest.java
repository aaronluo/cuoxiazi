/***********************************************
 * Filename        : CategoryServiceTest.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created         : 03/17/2015
 ************************************************/

package com.innovaee.eorder.service;

import static org.junit.Assert.assertEquals;

import com.innovaee.eorder.entity.Category;
import com.innovaee.eorder.entity.Dish;
import com.innovaee.eorder.exception.CategoryNotFoundException;
import com.innovaee.eorder.exception.DishNotFoundException;
import com.innovaee.eorder.exception.DuplicateNameException;
import com.innovaee.eorder.exception.InvalidPageSizeException;
import com.innovaee.eorder.exception.PageIndexOutOfBoundExcpeiton;
import com.innovaee.eorder.utils.Constants;
import com.innovaee.eorder.vo.CategoryVO;

import org.junit.Test;

import java.util.List;

import javax.annotation.Resource;

/**
 * @Title: CategoryServiceTest
 * @Description: CategoryService 单元测试类
 * 
 * @version V1.0
 */
public class CategoryServiceTest extends BaseSpringTestCase {

    @Resource
    private CategoryService categoryService;
    @Resource
    private DishService dishService;

    /**
     * 获取所有菜品分类单元测试
     */
    @Test
    public void getAllCategoriesTest() {
        assertEquals(11, categoryService.getAllCategoriesWithDefault().size());
    }

    /**
     * 添加重名菜品分类单元测试
     */
    @Test
    public void addCategoryWithDuplicateNameTest() {
        String name = "湘菜";

        CategoryVO categoryVO = new CategoryVO();
        categoryVO.setName(name);
        categoryVO.setPicPath(Constants.DEFAULT_CATEGORY_PIC);
        try {
            categoryService.addCategory(categoryVO);
        } catch (Exception exception) {
            assertEquals(exception.getClass(), DuplicateNameException.class);
        }
    }

    /**
     * 添加菜品分类单元测试
     */
    @Test
    public void addValidCategoryTest() {
        String name = "汤水";

        CategoryVO categoryVO = new CategoryVO();
        categoryVO.setName(name);
        categoryVO.setPicPath(Constants.DEFAULT_CATEGORY_PIC);

        try {
            Category category = categoryService.addCategory(categoryVO);

            assert (category.getId() > 0L);
        } catch (Exception exception) {
            assertEquals(exception.getClass(), DuplicateNameException.class);
        }
    }

    /**
     * 更新菜品分类名为已存在菜品分类名单元测试
     * 
     * @throws CategoryNotFoundException
     */
    @Test
    public void updateWithInvalidNameTest() throws CategoryNotFoundException {
        Category category = categoryService.getCategoryById(1L);
        String invalidName = "川菜";

        CategoryVO categoryVO = new CategoryVO();
        categoryVO.setName(invalidName);
        categoryVO.setId(category.getId());
        categoryVO.setPicPath(category.getPicPath());

        try {
            categoryService.updateCategory(categoryVO);
        } catch (Exception exception) {
            assertEquals(exception.getClass(), DuplicateNameException.class);
        }
    }

    /**
     * 更新菜品分类单元测试
     * 
     * @throws DuplicateNameException
     * @throws CategoryNotFoundException
     */
    @Test
    public void updateWithRightNameTest() throws DuplicateNameException,
            CategoryNotFoundException {
        Category category = categoryService.getCategoryById(1L);
        String invalidName = "湘菜";

        CategoryVO categoryVO = new CategoryVO();
        categoryVO.setName(invalidName);
        categoryVO.setId(category.getId());
        categoryVO.setPicPath(category.getPicPath());

        categoryService.updateCategory(categoryVO);

    }

    /**
     * 删除菜品分类单元测试
     * 
     * @throws CategoryNotFoundException
     * @throws DishNotFoundException
     */
    @Test
    public void deleteCategoryTest() throws CategoryNotFoundException,
            DishNotFoundException {
        Category category = categoryService.getCategoryById(1L);
        Dish dish = category.getDishes().iterator().next();
        categoryService.deleteCategory(1L);

        dish = dishService.getDishById(dish.getId());
        category = dish.getCategory();

        assertEquals(Constants.DEFAULT_CATEGORY, category.getName());

        try {
            categoryService.deleteCategory(1L);
        } catch (Exception exception) {
            assertEquals(exception.getClass(), CategoryNotFoundException.class);
        }
    }

    /**
     * 单元测试 - 使用非法分页大小获取分页总数
     */
    @Test
    public void getCategoryPageCountWithInvalidPageSizeTest() {
        try {
            categoryService.getCategoryPageCount(0);
        } catch (Exception exception) {
            assertEquals(exception.getClass(), InvalidPageSizeException.class);
        }
    }

    /**
     * 单元测试 - 使用合法分页大小获取分页总数
     * 
     * @throws InvalidPageSizeException
     */
    @Test
    public void getCategoryPageCountWithValidPageSizeTest()
            throws InvalidPageSizeException {
        int pageSize = categoryService.getCategoryPageCount(5);

        assertEquals(3, pageSize);
    }

    /**
     * 单元测试 - 获取菜品分类分页
     * 
     * @throws PageIndexOutOfBoundExcpeiton
     * @throws InvalidPageSizeException
     */
    @Test
    public void getCategoriesPageTest() throws PageIndexOutOfBoundExcpeiton,
            InvalidPageSizeException {
        int currentPage = 2;
        int pageSize = 5;

        List<Category> categories = categoryService
                .getCategoriesByPageWithDefault(currentPage, pageSize);

        assertEquals(categories.size(), pageSize);

    }

    /**
     * 单元测试 - 使用非法页数获取菜品分类分页
     */
    @Test
    public void getCategoriesPageWithInvalidPageTest() {
        int currentPage = 5;
        int pageSize = 5;

        try {
            categoryService.getCategoriesByPageWithDefault(currentPage,
                    pageSize);
        } catch (Exception exception) {
            assertEquals(exception.getClass(),
                    PageIndexOutOfBoundExcpeiton.class);
        }
    }
}
