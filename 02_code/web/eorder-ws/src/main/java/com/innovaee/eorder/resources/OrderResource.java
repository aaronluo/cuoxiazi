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

import com.innovaee.eorder.bean.Order;
import com.innovaee.eorder.bean.User;
import com.innovaee.eorder.dao.impl.OrderDaoImpl;
import com.innovaee.eorder.dao.impl.UserDaoImpl;
import com.innovaee.eorder.vo.OrderVO;

/**
 * 订单资源
 * 
 */
@Path("/orders")
public class OrderResource {
	private OrderDaoImpl orderDaoImpl = new OrderDaoImpl();

	private UserDaoImpl userDaoImpl = new UserDaoImpl();

	/**
	 * 增加
	 * 
	 * @param order
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void createOrder(Order order) {
		orderDaoImpl.createOrder(order);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@DELETE
	@Path("{id}")
	public void deleteOrder(@PathParam("id") String id) {
		orderDaoImpl.deleteOrderById(id);
	}

	/**
	 * 修改
	 * 
	 * @param order
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void updateOrder(Order order) {
		orderDaoImpl.updateOrder(order);
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
	public Order getOrderById(@PathParam("id") String id) {
		Order u = orderDaoImpl.getOrderById(id);
		return u;
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Map<String, List<Order>> getAllOrders() {
		List<Order> orders = new ArrayList<Order>();
		orders = orderDaoImpl.getAllOrders();
		Map<String, List<Order>> result = new HashMap<String, List<Order>>();
		result.put("orders", orders);
		return result;
	}

	@GET
	@Path("/myorders/{cellphone}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Map<String, List<OrderVO>> getOrderesById(
			@PathParam("cellphone") String cellphone) {
		User user = userDaoImpl.getUserByCellphone(cellphone);
		List<OrderVO> orderVOs = new ArrayList<OrderVO>();
		List<Order> orders = orderDaoImpl.getOrdersByMemberId(user.getUserId()
				.toString());
		for (Order order : orders) {
			OrderVO orderVO = new OrderVO();
			try {
				BeanUtils.copyProperties(orderVO, order);
				orderVOs.add(orderVO);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		Map<String, List<OrderVO>> result = new HashMap<String, List<OrderVO>>();
		result.put("orders", orderVOs);
		return result;
	}

}