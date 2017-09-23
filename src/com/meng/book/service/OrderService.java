package com.meng.book.service;

import java.util.List;

import com.meng.book.domain.Order;
import com.meng.book.domain.User;

public interface OrderService {
		public void addOrder(Order order);
		public List<Order> findOrderByUser(User user);
		public Order findOrderById(String order_id);
}
