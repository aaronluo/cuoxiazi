package com.innovaee.eorder.dao;

import java.util.List;

import com.innovaee.eorder.bean.Order;

public interface OrderDao {

	public Order getOrderById(String id);

	public boolean deleteOrderById(String id);

	public boolean createOrder(Order order);

	public boolean updateOrder(Order order);

	public List<Order> getAllOrders();
	
	public List<Order> getOrdersByMemberId(String memberId);

}