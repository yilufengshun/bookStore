package com.meng.book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meng.book.domain.PageBean;
import com.meng.book.service.ProductService;
import com.meng.book.service.impl.ProductServiceImpl;

@WebServlet("/showProductByPage")
public class ShowProductByPage extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String category=req.getParameter("category");
		
		String pageIndex=req.getParameter("pageIndex");
		int pageindex=0;
		if(category==null){
			category="";
		}
		if(pageIndex==null||pageIndex.trim().equals(""))
			pageindex=1;
		else{
			pageindex=Integer.parseInt(pageIndex);
		}
		ProductService productService=new ProductServiceImpl();
		PageBean pageBean=productService.findProducyByPage(pageindex, category);
		req.setAttribute("pageBean", pageBean);
		req.getRequestDispatcher("product_list.jsp").forward(req, resp);
		
	}
	
	
}
