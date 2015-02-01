package com.innovaee.eorder.service;

import com.innovaee.eorder.entity.Category;
import com.innovaee.eorder.entity.Dish;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();

    public List<Dish> getDishesByCategory(Integer categoryId);
}
