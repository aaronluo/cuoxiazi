/***********************************************
 * Filename       : DishServiceImpl.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.innovaee.eorder.dao.CategoryDao;
import com.innovaee.eorder.dao.DishDao;
import com.innovaee.eorder.entity.Category;
import com.innovaee.eorder.entity.Dish;
import com.innovaee.eorder.exception.CategoryNotFoundException;
import com.innovaee.eorder.exception.DishNotFoundException;
import com.innovaee.eorder.exception.DuplicateNameException;
import com.innovaee.eorder.exception.InvalidPageSizeException;
import com.innovaee.eorder.exception.PageIndexOutOfBoundExcpeiton;
import com.innovaee.eorder.service.DishService;
import com.innovaee.eorder.utils.MessageUtil;
import com.innovaee.eorder.vo.DishVO;

/**
 * @Title: DishServiceImpl
 * @Description: 菜品服务实现类
 * @version V1.0
 */
public class DishServiceImpl implements DishService {

    /** 菜品数据访问实现类对象 */
    @Resource
    private DishDao dishDao;

    /** 菜品分类数据访问实现类对象 */
    @Resource
    private CategoryDao categoryDao;

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

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public Dish addDish(DishVO dishVO) throws DuplicateNameException,
            CategoryNotFoundException {
        // 1. 检查是否有同名菜品
        Dish dish = dishDao.getDishByName(dishVO.getDishName());

        if (null == dish) {
            // 2. 检查菜品添加的所属分类是否存在
            Category category = categoryDao.get(dishVO.getCategoryId());
            if (null == category) {
                // 菜品分类不存在，抛出异常
                throw new CategoryNotFoundException(MessageUtil.getMessage(
                        "category_id", dishVO.getCategoryId() + ""));
            } else {
                dish = new Dish();
                dish.setCategory(category);
                dish.setDishName(dishVO.getDishName());
                dish.setOnSell(true);
                dish.setDishPicture(dishVO.getDishPicture());
                dish.setDishPrice(dishVO.getDishPrice());
                dish.setCreateDate(new Date());

                dishDao.save(dish);
            }
        } else {
            // 有重名菜品，抛出异常
            throw new DuplicateNameException(dishVO.getDishName());
        }

        return dish;
    }

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

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public Dish updateDish(DishVO dishVO) throws DishNotFoundException,
            CategoryNotFoundException, DuplicateNameException {
        // 1. 检查要更新的菜品是否存在
        Dish dish = getDishById(dishVO.getId());

        // 2. 检查是否有重名菜品
        dish = dishDao.getDishByName(dishVO.getDishName());

        if (null != dish && dish.getId() != dishVO.getId()) {
            throw new DuplicateNameException(dishVO.getDishName());
        } else {
            // 3. 检查待更新菜品中的菜品分类信息是否存在
            Category category = categoryDao.get(dishVO.getCategoryId());

            if (null == category) {
                throw new CategoryNotFoundException(MessageUtil.getMessage(
                        "category_id", dishVO.getCategoryId() + ""));
            } else {
                dish = dishDao.get(dishVO.getId());
                dish.setCategory(category);
                dish.setMisc(dishVO.getMisc());
                dish.setDishName(dishVO.getDishName());
                dish.setDishPicture(dishVO.getDishPicture());
                dish.setDishPrice(dishVO.getDishPrice());
                dish.setUpdateDate(new Date());

                dishDao.update(dish);
            }
        }

        return dish;
    }

    /**
     * 删除指定id的菜品
     * 
     * @param dishId
     *            菜品id
     * @throws DishNotFoundException
     *             菜品不存在异常
     */

    public void deleteDish(Long dishId) throws DishNotFoundException {
        Dish dish = getDishById(dishId);

        // 删除菜品操作不会将菜品从数据库中删除，而是将onSell设置为false
        dish.setOnSell(false);
        dishDao.update(dish);
    }

    /**
     * 根据菜品ID查找菜品
     * 
     * @param dishId
     *            菜品ID
     * @return 菜品实体
     */

    public Dish getDishById(Long dishId) throws DishNotFoundException {
        Dish dish = dishDao.get(dishId);

        if (null == dish) {
            throw new DishNotFoundException(MessageUtil.getMessage("dish_id",
                    "" + dishId));
        }

        return dish;
    }

    /**
     * 根据菜品名称获取菜品信息
     * 
     * @param dishName
     *            菜品名称
     * @return 菜品信息对象
     * @throws DishNotFoundException
     *             菜品不存在异常
     */

    public Dish getDishByName(String dishName) throws DishNotFoundException {
        Dish dish = dishDao.getDishByName(dishName);

//        if (null == dish) {
//            throw new DishNotFoundException(MessageUtil.getMessage("dish_name",
//                    dishName));
//        }

        return dish;
    }

    /**
     * 获取菜品分页数据
     * 
     * @param curPage
     *            当前分页
     * @param pageSize
     *            分页大小
     * @return
     * @throws PageIndexOutOfBoundExcpeiton
     *             分页超限异常
     * @throws CategoryNotFoundException
     * @throws InvalidPageSizeException
     */
    public List<Dish> getDishesByPage(int curPage, int pageSize, Long categoryId)
            throws PageIndexOutOfBoundExcpeiton, CategoryNotFoundException,
            InvalidPageSizeException {
        List<Dish> dishes = new ArrayList<Dish>();
        // 1. 计算总页数
        int totalPage = this.getDishPageCount(pageSize, categoryId);
        // 2. 如果当前分页不是一个非法的分页， 则抛出异常
        if (curPage < 1 || curPage > totalPage) {
            throw new PageIndexOutOfBoundExcpeiton(totalPage, curPage);
        } else {
            if (null == categoryDao.get(categoryId)) {
                throw new CategoryNotFoundException(MessageUtil.getMessage(
                        "category_id", "" + categoryId));
            } else {
                int startIndex = (curPage - 1) * pageSize;
                String sql = "FROM Dish as d WHERE d.category.id=? AND d.onSell=1 order by d.id desc";
                dishes = dishDao.getPage(startIndex, pageSize, sql, categoryId);
            }
        }

        return dishes;
    }

    /**
     * 获得总记录条数
     * 
     * @return 总记录条数
     */
    public Integer count() {
        return dishDao.count();
    }

    /**
     * 获得某一菜品分类下的菜品分页总数
     * 
     * @param pageSize
     *            分页大小
     * @param categoryId
     *            菜品分类id
     * @return
     * @throws InvalidPageSizeException
     *             非法分页大小异常
     * @throws CategoryNotFoundException
     *             菜品分类未找到异常
     */
    public int getDishPageCount(int pageSize, Long categoryId)
            throws InvalidPageSizeException, CategoryNotFoundException {
        if (pageSize <= 0) {
            throw new InvalidPageSizeException(pageSize);
        }

        Category category = categoryDao.get(categoryId);
        int totalDishes = category.getDishes().size();

        return totalDishes % pageSize == 0 ? totalDishes / pageSize
                : totalDishes / pageSize + 1;
    }

}