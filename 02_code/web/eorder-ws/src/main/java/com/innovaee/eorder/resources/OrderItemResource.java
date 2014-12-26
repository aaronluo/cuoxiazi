/***********************************************
 * Filename		: OrderItemResource.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.resources;

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

import com.innovaee.eorder.bean.Dish;
import com.innovaee.eorder.bean.OrderItem;
import com.innovaee.eorder.dao.impl.DishDaoImpl;
import com.innovaee.eorder.dao.impl.OrderItemDaoImpl;
import com.innovaee.eorder.vo.OrderItemVO;

/**
 * @Title: OrderItemResource
 * @Description: 订单明细资源
 * @author coderdream@gmail.com
 * @version V1.0
 */
@Path("/orderitems")
public class OrderItemResource {
	private OrderItemDaoImpl orderItemDaoImpl = new OrderItemDaoImpl();
	private DishDaoImpl dishDaoImpl = new DishDaoImpl();

	/**
	 * 根据orderId查询
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("/myorderitems/{orderId}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Map<String, List<OrderItemVO>> getOrderItemsByOrderId(
			@PathParam("orderId") String orderId) {
		List<OrderItemVO> orderItemVOs = new ArrayList<OrderItemVO>();
		Dish dish = null;
		OrderItemVO orderItemVO = null;
		List<OrderItem> orderItems = orderItemDaoImpl
				.getOrderItemsByOrderId(orderId);
		for (OrderItem orderItem : orderItems) {

			orderItemVO = new OrderItemVO();
			try {
				BeanUtils.copyProperties(orderItemVO, orderItem);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

			if (null != orderItem) {
				dish = dishDaoImpl
						.getDishById(orderItem.getDishId().toString());
				orderItemVO.setDishName(dish.getDishName());
				orderItemVO.setDishPicture(dish.getDishPicture());
				orderItemVO.setDishPrice(dish.getDishPrice());
				orderItemVOs.add(orderItemVO);
			}
		}

		Map<String, List<OrderItemVO>> result = new HashMap<String, List<OrderItemVO>>();
		result.put("orderitems", orderItemVOs);

		return result;
	}

}