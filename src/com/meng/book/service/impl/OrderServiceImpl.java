package com.meng.book.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.meng.book.dao.OrderDao;
import com.meng.book.dao.OrderItemDao;
import com.meng.book.dao.ProductDao;
import com.meng.book.dao.impl.OrderDaoImpl;
import com.meng.book.dao.impl.OrderItemDaoImpl;
import com.meng.book.dao.impl.ProductDaoImpl;
import com.meng.book.domain.Order;
import com.meng.book.domain.Product;
import com.meng.book.domain.User;
import com.meng.book.exception.OrderException;
import com.meng.book.service.OrderService;
import com.meng.book.utils.ThreadLocalManager;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class OrderServiceImpl implements OrderService{
	OrderDao orderDao=new OrderDaoImpl();
	OrderItemDao orderItemdao=new OrderItemDaoImpl();
	ProductDao productDao=new ProductDaoImpl();
	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		
		try {
			//开启事务
			ThreadLocalManager.setAutoCommit();
		
			orderDao.addOrder(order);//想order中添加数据
			orderItemdao.addOrderItem(order);//想item中添加数据
			productDao.updateProductNum(order);//修改库存
			ThreadLocalManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			
			try {
				ThreadLocalManager.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			throw new OrderException("创建订单失败",e);
		}
		
	}
	@Override
	public List<Order> findOrderByUser(User user) {
		// TODO Auto-generated method stub
		OrderDao od=new OrderDaoImpl();
	List<Order> orders = od.findOrderByUser(user);
		
		return orders;
	}
	@Override
	public Order findOrderById(String order_id) {
		// TODO Auto-generated method stub
		OrderDao od=new OrderDaoImpl();
		Order order=od.findOrderById(order_id);
		
		
		return order;
	}

}
