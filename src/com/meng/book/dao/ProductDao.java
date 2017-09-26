package com.meng.book.dao;

import java.util.List;

import com.meng.book.domain.Order;
import com.meng.book.domain.Product;

public interface ProductDao {
		public int findAllcount(String category);
		public List<Product> findByPage(int pageIndex,int pageSize,String category);
		public int findAllCountByName(String search);
		public List<Product> findProductBySearchWithPage(int pageIndex,int pageSize, String search);
		public Product findProducyById(String id);
		public void updateProductNum(Order order);
		public List<Product> findProductByManyCondition(String id
				,String category
				,String name
				,double minprice
				,double maxprice);
}
