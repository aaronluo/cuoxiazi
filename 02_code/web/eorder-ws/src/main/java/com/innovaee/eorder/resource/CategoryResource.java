/***********************************************
 * Filename        : CategoryResource.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.innovaee.eorder.entity.Category;
import com.innovaee.eorder.service.CategoryService;

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
    public Map<String, List<Category>> getAllCategories() {
        List<Category> categories = new ArrayList<Category>();
        categories = categoryService.getAllCategories();
        Map<String, List<Category>> result = new HashMap<String, List<Category>>();
        result.put("categories", categories);
        return result;
    }

}