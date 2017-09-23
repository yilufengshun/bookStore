package com.meng.book.service;

import com.meng.book.domain.PageBean;
import com.meng.book.domain.Product;

public interface ProductService {
		public PageBean findProducyByPage(int pageIndex,String category);


		public PageBean findProductBySearch(int pageIndex, String search);
		//findProducyById xunzhao

		public Product findProducyById(String id);
		
}
