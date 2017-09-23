package com.meng.book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meng.book.domain.Product;
import com.meng.book.service.ProductService;
import com.meng.book.service.impl.ProductServiceImpl;


@WebServlet("/findProductById")
public class FindProductById extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doPost(req, resp);
		}
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=req.getParameter("product_id");
		if(id==null||id.trim().equals("")){
			resp.sendRedirect("index.jsp");
			return;
		}
		try {
			ProductService ps=new ProductServiceImpl();
			Product pt=ps.findProducyById(id);
			req.setAttribute("product", pt);
			req.getRequestDispatcher("product_info.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.setAttribute("msg", "显示商品信息失败");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
		
}
}
