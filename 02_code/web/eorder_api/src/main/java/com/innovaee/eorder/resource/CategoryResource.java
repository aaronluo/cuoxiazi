package com.innovaee.eorder.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import com.innovaee.eorder.entity.Category;
import com.innovaee.eorder.entity.Dish;
import com.innovaee.eorder.service.CategoryService;
import com.innovaee.eorder.vo.CategoryVO;
import com.innovaee.eorder.vo.DishVO;

@Path("/categories")
public class CategoryResource {
    private Logger logger = Logger.getLogger(this.getClass());
    private CategoryService categoryService;

    @GET
    @Scope("request")
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, List<CategoryVO>> getAllCategories() {
        logger.info("[REST_CALL= getAllCategories]");

        List<CategoryVO> categoryVOs = new ArrayList<CategoryVO>();
        List<Category> categories = categoryService.getAllCategories();

        for (Category category : categories) {
            categoryVOs.add(new CategoryVO(category.getId(), category.getName(),
                    category.getPicPath()));
        }

        Map<String, List<CategoryVO>> result = new HashMap<String, List<CategoryVO>>();
        
        result.put("categories", categoryVOs);
        
        return result;
    }

    @GET
    @Path("/{categoryId}/dishes")
    @Scope("request")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, List<DishVO>> getDishesByCategory(
            @PathParam("categoryId") Integer categoryId) {

        logger.info("[REST_CALL= getDishesByCategory, categoryId=" + categoryId
                + "]");
        
        List<Dish> dishes = categoryService.getDishesByCategory(categoryId);
        List<DishVO> dishVOs = new ArrayList<DishVO>();
        
        for (Dish dish : dishes) {
            dishVOs.add(new DishVO(dish.getId(), dish.getName(), dish.getPrice(), dish.getPicPath(), dish.isOnSell()));
        }
        
        Map<String, List<DishVO>> result = new HashMap<String, List<DishVO>>();
        
        result.put("dishes", dishVOs);
        
        return result;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
