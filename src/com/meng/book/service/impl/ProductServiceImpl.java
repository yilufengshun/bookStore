package com.meng.book.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.meng.book.dao.ProductDao;
import com.meng.book.dao.impl.ProductDaoImpl;
import com.meng.book.domain.PageBean;
import com.meng.book.domain.Product;
import com.meng.book.exception.ProductException;
import com.meng.book.service.ProductService;
import com.meng.book.utils.C3p0Utils;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class ProductServiceImpl implements ProductService {

	@Override
	public PageBean findProducyByPage(int pageIndex,String category) {
		// TODO Auto-generated method stub
		ProductDao productDao=new ProductDaoImpl();
		PageBean pageBean=null;
		try {
			int totalCount=productDao.findAllcount(category);
			pageBean=new PageBean(pageIndex, totalCount, category);
			List<Product> data=productDao.findByPage(pageIndex, pageBean.getPageSize(), category);
			pageBean.setData(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ProductException("获取分页数据失败",e);
		}
		
		return pageBean;

	}



	@Override
	public PageBean findProductBySearch(int pageIndex, String search) {
		// TODO Auto-generated method stub
		//先搜索这个关键字一共多少数据
		ProductDao pd=new ProductDaoImpl();
		int totalcount=pd.findAllCountByName(search);
		PageBean pageBean=new PageBean(pageIndex,totalcount,null);
		List<Product> data=	pd.findProductBySearchWithPage(pageIndex, pageBean.getPageSize(), search);
		pageBean.setData(data);
	
		return pageBean;
	}

	@Override
	public Product findProducyById(String id) {
		// TODO Auto-generated method stub
		ProductDao pd=new ProductDaoImpl();
		
		return pd.findProducyById(id);
	}

}
