package com.meng.book.service;

import java.util.List;

import com.meng.book.domain.PageBean;
import com.meng.book.domain.Product;

public interface ProductService {
		public PageBean findProducyByPage(int pageIndex,String category);


		public PageBean findProductBySearch(int pageIndex, String search);
		//findProducyById xunzhao

		public Product findProducyById(String id);
		public List<Product> findProductByManyCondition(String id, String category, String name, double minprice,
				double maxprice);
		
}
