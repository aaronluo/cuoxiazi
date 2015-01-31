package com.innovaee.eorder.service;

import com.innovaee.eorder.model.Category;
import com.innovaee.eorder.model.Dish;

import java.util.List;

public interface CategoryService {
   public  List<Category> getAllCategories();
    
   public List<Dish> getDishesByCategory(Integer categoryId);
}
