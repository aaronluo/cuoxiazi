/***********************************************
 * Filename        : DishResource.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.innovaee.eorder.entity.Dish;
import com.innovaee.eorder.service.DishService;

/**
 * @Title: DishResource
 * @Description: 菜品资源
 * @version V1.0
 */
@Path("/dishes")
public class DishResource extends AbstractBaseResource {

    /** 菜品服务类对象 */
    private DishService dishService = new DishService();

    /**
     * 根据categoryId查询
     * 
     * @param categoryId
     *            菜品分类ID
     * @return 菜品列表
     */
    @GET
    @Path("/mydishes/{categoryId}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Map<String, List<Dish>> getDishesById(
            @PathParam("categoryId") Integer categoryId) {
        List<Dish> dishes = dishService.getDishesByCategoryId(categoryId);

        Map<String, List<Dish>> result = new HashMap<String, List<Dish>>();
        result.put("dishes", dishes);

        return result;
    }

}