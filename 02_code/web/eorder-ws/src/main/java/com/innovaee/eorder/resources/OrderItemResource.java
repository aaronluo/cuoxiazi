package com.innovaee.eorder.resources;

import java.lang.reflect.InvocationTargetException;
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

import org.apache.commons.beanutils.BeanUtils;

import com.innovaee.eorder.bean.Dish;
import com.innovaee.eorder.bean.OrderItem;
import com.innovaee.eorder.dao.impl.DishDaoImpl;
import com.innovaee.eorder.dao.impl.OrderItemDaoImpl;
import com.innovaee.eorder.vo.OrderItemVO;

/**
 * 订单资源
 * 
 */
@Path("/orderitems")
public class OrderItemResource {
	private OrderItemDaoImpl orderItemDaoImpl = new OrderItemDaoImpl();
	private DishDaoImpl dishDaoImpl = new DishDaoImpl();

	/**
	 * 增加
	 * 
	 * @param orderItem
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void createOrderItem(OrderItem orderItem) {
		orderItemDaoImpl.createOrderItem(orderItem);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@DELETE
	@Path("{id}")
	public void deleteOrderItem(@PathParam("id") String id) {
		orderItemDaoImpl.deleteOrderItemById(id);
	}

	/**
	 * 修改
	 * 
	 * @param orderItem
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void updateOrderItem(OrderItem orderItem) {
		orderItemDaoImpl.updateOrderItem(orderItem);
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public OrderItem getOrderItemById(@PathParam("id") String id) {
		OrderItem u = orderItemDaoImpl.getOrderItemById(id);
		return u;
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Map<String, List<OrderItem>> getAllOrderItems() {
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		orderItems = orderItemDaoImpl.getAllOrderItems();
		Map<String, List<OrderItem>> result = new HashMap<String, List<OrderItem>>();
		result.put("orderItems", orderItems);
		return result;
	}

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