package com.meng.book.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meng.book.domain.Product;
import com.meng.book.service.ProductService;
import com.meng.book.service.impl.ProductServiceImpl;

@WebServlet("/addCart")
public class AddCartServlet extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
		doPost(req, resp);
		}
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String product_id=req.getParameter("product_id");
		ProductService ps=new ProductServiceImpl();
		Product product=ps.findProducyById(product_id);
		//添加到购物车map<product,int(num)>
		Map<Product, Integer> cart=(Map<Product, Integer>) req.getSession().getAttribute("cart");
		if(cart==null){
			cart=new HashMap<Product, Integer>();             
			cart.put(product, 1);
			
		}else{
			//已经有值
			if(cart.containsKey(product)){
				cart.put(product,cart.get(product)+1);
				
			}else{
			cart.put(product, 1);
			}
			
		}
		req.getSession().setAttribute("cart", cart);
		resp.sendRedirect("cart.jsp");
		
		
		
		}
}
