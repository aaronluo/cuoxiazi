package com.innovaee.eorder.dao;

import java.util.List;

import com.innovaee.eorder.bean.Category;

public interface CategoryDao {

	public Category getCategoryById(String id);

	public boolean deleteCategoryById(String id);

	public boolean createCategory(Category category);

	public boolean updateCategory(Category category);

	public List<Category> getAllCategorys();
}
