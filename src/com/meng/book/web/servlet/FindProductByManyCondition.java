package com.meng.book.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meng.book.domain.Product;
import com.meng.book.service.ProductService;
import com.meng.book.service.impl.ProductServiceImpl;

@WebServlet("/findProductByManyCondition")
public class FindProductByManyCondition extends HttpServlet{
	//根据多重条件查询，类别，价格区间，编号。
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=req.getParameter("id");
		String category=req.getParameter("category");
		String name=req.getParameter("name");
		String minprice=req.getParameter("minprice");
		String maxprice=req.getParameter("maxprice");
		double min =-1;
		 double max=-1;
		if(!minprice.trim().equals(""))
		 min=Double.parseDouble(minprice);
		if(!maxprice.trim().equals(""))
		  max = Double.parseDouble(maxprice);
		
		ProductService ps=new ProductServiceImpl();
		List<Product> list = ps.findProductByManyCondition(id, category, name, min, max);
		
		req.getSession().setAttribute("list", list);
		req.getRequestDispatcher("admin/products/list.jsp").forward(req, resp);
	}
}
