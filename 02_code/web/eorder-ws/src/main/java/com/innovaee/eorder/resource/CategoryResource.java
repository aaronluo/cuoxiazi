/***********************************************
 * Filename        : CategoryResource.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.resource;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.beanutils.BeanUtils;

import com.innovaee.eorder.entity.Category;
import com.innovaee.eorder.entity.Dish;
import com.innovaee.eorder.service.CategoryService;
import com.innovaee.eorder.vo.CategoryVO;
import com.innovaee.eorder.vo.DishVO;

/**
 * @Title: CategoryResource
 * @Description: 菜品分类资源
 * 
 * @version V1.0
 */
@Path("/categories")
public class CategoryResource extends AbstractBaseResource {

    /** 菜品分类服务类对象 */
    private CategoryService categoryService = new CategoryService();

    /**
     * 查询所有菜品分类
     * 
     * @return 所有菜品分类
     */
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Map<String, List<CategoryVO>> getAllCategories() {
        List<CategoryVO> categorieVOs = new ArrayList<CategoryVO>();
        List<Category> categories = new ArrayList<Category>();
        categories = categoryService.getAllCategories();

        for (Category Category : categories) {
            CategoryVO categoryVO = new CategoryVO();
            try {
                BeanUtils.copyProperties(categoryVO, Category);
                categorieVOs.add(categoryVO);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        Map<String, List<CategoryVO>> result = new HashMap<String, List<CategoryVO>>();
        result.put("categories", categorieVOs);
        return result;
    }

    /**
     * 根据categoryId查询菜品列表
     * 
     * @param categoryId
     *            菜品分类ID
     * @return 菜品列表
     */
    @GET
    @Path("/{categoryId}/dishes")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Map<String, List<DishVO>> getDishesByCategoryId(
            @PathParam("categoryId") Integer categoryId) {
        List<Dish> dishes = categoryService.getDishesByCategoryId(categoryId);

        List<DishVO> dishVOs = new ArrayList<DishVO>();
        //
        for (Dish dish : dishes) {
            DishVO dishVO = new DishVO();
            try {
                // 将菜品对象的信息复制到菜品值对象中，用于返回给客户端
                BeanUtils.copyProperties(dishVO, dish);
                dishVOs.add(dishVO);
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage());
            } catch (InvocationTargetException e) {
                LOGGER.error(e.getMessage());
            }
        }

        Map<String, List<DishVO>> result = new HashMap<String, List<DishVO>>();
        result.put("dishes", dishVOs);

        return result;
    }

}