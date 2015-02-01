package com.innovaee.eorder.service.impl;

import com.innovaee.eorder.dao.CategoryDao;
import com.innovaee.eorder.model.Category;
import com.innovaee.eorder.model.Dish;
import com.innovaee.eorder.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;
    
    @Override
    public List<Category> getAllCategories() {
        return categoryDao.loadAll();
    }

    @Override
    public List<Dish> getDishesByCategory(Integer categoryId) {
        List<Dish> result = new ArrayList<Dish>();
        
        Category category = categoryDao.get(categoryId);
        
        if(null != category) {
            result.addAll(category.getDishes());
        }
        
        return result;
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }
    
}