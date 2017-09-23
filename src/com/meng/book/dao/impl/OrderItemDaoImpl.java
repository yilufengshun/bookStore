package com.meng.book.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.meng.book.dao.OrderItemDao;
import com.meng.book.domain.Order;
import com.meng.book.domain.OrderItem;
import com.meng.book.exception.OrderException;
import com.meng.book.utils.C3p0Utils;


public class OrderItemDaoImpl implements OrderItemDao{

	@Override
	public void addOrderItem(Order order) {
		// TODO Auto-generated method stub
		List<OrderItem> orderItems = order.getOrderItems();
		
		
		QueryRunner qr=new QueryRunner(C3p0Utils.getDataSource());
		Object[][] params=new Object[orderItems.size()][3];
		for(int i=0;i<orderItems.size();i++){
			OrderItem orderItem = orderItems.get(i);
			
			params[i][0]=orderItem.getOrder().getId();//orderId
			params[i][1]=orderItem.getP().getId();
			params[i][2]=orderItem.getBuynum();	
		}
		try {
			qr.batch("insert into orderitem(order_id,product_id,buynum) values(?,?,?)", params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new OrderException("添加订单失败",e);
		}//参数是一个二维数组
		
		
		
		
	}

	

}
