package com.meng.book.dao;

import java.util.List;

import com.meng.book.domain.Order;
import com.meng.book.domain.User;

public interface OrderDao {
	public void addOrder(Order order);

	public List<Order> findOrderByUser(User user);

	public Order findOrderById(String order_id);
}
