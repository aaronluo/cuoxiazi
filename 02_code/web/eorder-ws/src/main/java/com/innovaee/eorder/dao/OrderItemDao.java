package com.innovaee.eorder.dao;

import java.util.List;

import com.innovaee.eorder.bean.OrderItem;

public interface OrderItemDao {

	public OrderItem getOrderItemById(String id);

	public boolean deleteOrderItemById(String id);

	public boolean createOrderItem(OrderItem orderItem);

	public boolean updateOrderItem(OrderItem orderItem);

	public List<OrderItem> getAllOrderItems();

	public List<OrderItem> getOrderItemsByOrderId(String orderId);
}