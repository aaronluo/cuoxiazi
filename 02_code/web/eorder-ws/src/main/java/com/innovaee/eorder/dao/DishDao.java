package com.innovaee.eorder.dao;

import java.util.List;

import com.innovaee.eorder.bean.Dish;

public interface DishDao {

	public Dish getDishById(String id);

	public boolean deleteDishById(String id);

	public boolean createDish(Dish dish);

	public boolean updateDish(Dish dish);

	public List<Dish> getAllDishes();

	public List<Dish> getDishesByCategoryId(String categoryId);
}