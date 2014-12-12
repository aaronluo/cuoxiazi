package com.innovaee.eorder.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.innovaee.eorder.bean.Dish;
import com.innovaee.eorder.dao.impl.DishDaoImpl;

/**
 * 用户资源
 * 
 * @author waylau.com 2014-3-19
 */
@Path("/dishes")
public class DishResource {
	private DishDaoImpl dishDaoImpl = new DishDaoImpl();

	/**
	 * 增加
	 * 
	 * @param dish
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void createDish(Dish dish) {
		dishDaoImpl.createDish(dish);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@DELETE
	@Path("{id}")
	public void deleteDish(@PathParam("id") String id) {
		dishDaoImpl.deleteDishById(id);
	}

	/**
	 * 修改
	 * 
	 * @param dish
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void updateDish(Dish dish) {
		dishDaoImpl.updateDish(dish);
	}

	/**
	 * 根据categoryId查询
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("/mydishes/{categoryId}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Map<String, List<Dish>> getDishesById(
			@PathParam("categoryId") String categoryId) {
		List<Dish> dishes = dishDaoImpl.getDishesByCategoryId(categoryId);

		Map<String, List<Dish>> result = new HashMap<String, List<Dish>>();
		result.put("dishes", dishes);
		return result;
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("{dishId}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Map<String, Dish> getDishById(@PathParam("dishId") String dishId) {
		Dish dish = dishDaoImpl.getDishById(dishId);
		Map<String, Dish> result = new HashMap<String, Dish>();
		result.put("dish", dish);

		return result;
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Map<String, List<Dish>> getAllDishs() {
		List<Dish> dishes = new ArrayList<Dish>();
		dishes = dishDaoImpl.getAllDishes();
		Map<String, List<Dish>> result = new HashMap<String, List<Dish>>();
		result.put("dishes", dishes);
		return result;
	}

}