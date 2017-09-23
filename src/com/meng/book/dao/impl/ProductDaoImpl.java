package com.meng.book.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.meng.book.dao.ProductDao;
import com.meng.book.domain.Order;
import com.meng.book.domain.OrderItem;
import com.meng.book.domain.Product;
import com.meng.book.exception.ProductException;
import com.meng.book.utils.C3p0Utils;
import com.sun.org.apache.bcel.internal.generic.NEW;


public class ProductDaoImpl implements ProductDao{

	@Override
	public int findAllcount(String category) {
		String sql="select count(*) from products";
		if(!category.equals(""))
			sql+=" where category = '"+category+"'";
		QueryRunner qr=new QueryRunner(C3p0Utils.getDataSource());
		try {
		long count=(long)	qr.query(sql, new ScalarHandler(1));
		return (int)count;
		} catch (SQLException e) {
		
			e.printStackTrace();
		}//取第一列的数据
		return 0;
		
		
	}

	@Override
	public List<Product> findByPage(int pageIndex,int pageSize, String category) {
		// TODO Auto-generated method stub
		String sql="select * from products where 1=1";
		if(!category.equals("")){
			sql+=" and category='"+category+"'";
		}
		sql+=" limit ?,?";
		QueryRunner qr=new QueryRunner(C3p0Utils.getDataSource());
		int pos=(pageIndex-1)*pageSize;
		
		
		
		try {
			return qr.query(sql,new BeanListHandler<Product>(Product.class),pos,pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ProductException("获取数据失败",e);
		}
	}

	@Override
	public int findAllCountByName(String search) {
		String sql="select count(*) from products where name like ?";
		
		QueryRunner qr=new QueryRunner(C3p0Utils.getDataSource());
		try {
		long count=(long)	qr.query(sql, new ScalarHandler(1),"%"+search+"%");
		return (int)count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//取第一列的数据
		return 0;
	}
	@Override
	public List<Product> findProductBySearchWithPage(int pageIndex,int pageSize, String search) {
		// TODO Auto-generated method stub
		String sql="select * from products where name like ? limit ?,?";
		QueryRunner qr=new QueryRunner(C3p0Utils.getDataSource());
		try {
			return	qr.query(sql,new BeanListHandler<Product>(Product.class),"%"+search+"%",(pageIndex-1)*pageSize,pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ProductException("获取数据失败",e);
		}
	}

	@Override
	public Product findProducyById(String id) {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(C3p0Utils.getDataSource());
		try {
			return qr.query("select * from products where id = ?",new BeanHandler<Product>(Product.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ProductException("查询商品失败",e);
		}
		
		
	}

	@Override
	public void updateProductNum(Order order) {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(C3p0Utils.getDataSource());
		List<OrderItem> orderitems = order.getOrderItems();
		Object[][] params=new Object[orderitems.size()][2];
		for(int i=0;i<orderitems.size();i++){
			Product p=orderitems.get(i).getP();
			OrderItem orderItem=orderitems.get(i);
			params[i][0]=orderItem.getP().getPnum()-orderItem.getBuynum();
			params[i][1]=orderItem.getP().getId();
		}
			
		try {
			qr.batch("update  products set pnum=? where id = ?", params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ProductException("",e);
		}
		
		
		
	}
	
	
	
	
	

}
