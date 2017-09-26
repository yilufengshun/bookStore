package com.meng.book.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.meng.book.dao.OrderDao;
import com.meng.book.domain.Order;
import com.meng.book.domain.OrderItem;
import com.meng.book.domain.Product;
import com.meng.book.domain.User;
import com.meng.book.exception.OrderException;
import com.meng.book.utils.C3p0Utils;


public class OrderDaoImpl implements OrderDao{

	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		//向order表中添加一条数据
		QueryRunner qr=new QueryRunner(C3p0Utils.getDataSource());
		
		try {
			qr.update("insert into orders(id,money,receiverAddress,receiverName,receiverPhone,paystate,ordertime,user_id)values(?,?,?,?,?,?,?,?)",order.getId(),order.getMoney(),order.getReceiverAddress(),order.getReceiverName(),order.getReceiverPhone(),order.getPaystate(),order.getOrdertime(),order.getUser().getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new OrderException("创建订单失败",e);
		}
	}

	@Override
	public List<Order> findOrderByUser(User user) {
		// TODO Auto-generated method stub
		QueryRunner qRunner=new QueryRunner(C3p0Utils.getDataSource());
		try {
		
			List<Order> orders = qRunner.query("select * from orders where user_id = ?",new BeanListHandler<Order>(Order.class),user.getId());
			return orders;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new OrderException("订单查询失败",e);
		}
		
		
	}

	public Order findOrderById(String order_id) {
		// TODO Auto-generated method stub
		try {
			//1查询order
			QueryRunner qr=new QueryRunner(C3p0Utils.getDataSource());
			final Order order=qr.query("select * from orders where id=?", new BeanHandler<Order>(Order.class),order_id);
			
			//2查询orderItem
			qr.query("SELECT * FROM orderitem,products WHERE orderitem.`product_id`=products.`id`  AND order_id=?;", new ResultSetHandler<Order>() {
				@Override
				public Order handle(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					if(rs!=null){
						ArrayList<OrderItem> orderItems=new ArrayList<OrderItem>();
						double money=0;
						while(rs.next()){
							int buynum=rs.getInt("buynum");
							String product_id=rs.getString("product_id");
							String name=rs.getString("name");
							double price=rs.getDouble("price");
							String category=rs.getString("category");
							int pnum=rs.getInt("pnum");
							String imgurl=rs.getString("imgurl");
							String description=rs.getString("description");
							
							Product product=new Product(product_id, name, price, category, pnum, imgurl, description);
							OrderItem orderItem=new OrderItem(order, product, buynum);
							orderItems.add(orderItem);
							money+=buynum*price;
						}
						
						order.setOrderItems(orderItems);
						order.setMoney(money);
					}
					return order;
				}
			},order_id);
			
			return order;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new OrderException("查询订单信息失败",e);
		}
	}



}
