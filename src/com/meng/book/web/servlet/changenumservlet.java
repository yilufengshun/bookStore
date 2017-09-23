package com.meng.book.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meng.book.domain.Product;
import com.meng.book.service.ProductService;
import com.meng.book.service.impl.ProductServiceImpl;


@WebServlet("/changenum")
public class changenumservlet extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doPost(req, resp);
		}
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
			String id=req.getParameter("id");
			String value=req.getParameter("value");
			String pnum=req.getParameter("pnum");
			Map<Product, Integer> cart=(Map<Product, Integer>) req.getSession().getAttribute("cart");
			ProductService ps=new ProductServiceImpl();
			Product product=ps.findProducyById(id);
			int v=Integer.parseInt(value);
			//
			if(v<0||v>Integer.parseInt(pnum)){
				return;
			}
			
			cart.put(product,v );
			req.getSession().setAttribute("cart", cart);
			resp.getWriter().write(value);
			return;
			//name=cart
			
			
		}
		
}
