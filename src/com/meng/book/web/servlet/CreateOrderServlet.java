package com.meng.book.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meng.book.domain.Order;
import com.meng.book.domain.OrderItem;
import com.meng.book.domain.Product;
import com.meng.book.domain.User;
import com.meng.book.exception.ProductException;
import com.meng.book.service.OrderService;
import com.meng.book.service.impl.OrderServiceImpl;



@WebServlet("/createOder")
public class CreateOrderServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		String receiverAddress=req.getParameter("receiverAddress");
		String receiverName=req.getParameter("receiverName");
		String receiverPhone=req.getParameter("receiverPhone");
		//判空
		if(receiverAddress==null||receiverAddress.trim().equals("")){
			req.setAttribute("adress_empty", "地址不能为空");
			req.getRequestDispatcher("order.jsp").forward(req, resp);
		}
		if(receiverName==null||receiverName.trim().equals("")){
			req.setAttribute("name_empty", "地址不能为空");
			req.getRequestDispatcher("order.jsp").forward(req, resp);
		}
		if(receiverPhone==null||receiverPhone.trim().equals("")){
			req.setAttribute("phone_empty", "地址不能为空");
			req.getRequestDispatcher("order.jsp").forward(req, resp);
		}
		
		PrintWriter out=resp.getWriter();
		//user
		User user=(User) req.getSession().getAttribute("user");
		if(user==null){
			out.print("请登录");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
			
		}
		
		
		//购物车
		Map<Product, Integer> cart=(Map<Product, Integer>) req.getSession().getAttribute("cart");
		if(cart==null||cart.size()==0){
			out.print("<scirpt type='text/javascript'>alert('购物车为空');window.location.href='login.jsp?returl=order.jsp';</scirpt>");
			
			
		}
		
		Order order=new Order();
		order.setId(UUID.randomUUID().toString());
		order.setUser(user);
		order.setReceiverAddress(receiverAddress);
		order.setReceiverName(receiverName);
		order.setReceiverPhone(receiverPhone);
		order.setPaystate(0);
		order.setOrdertime(new Date());
		
		//
		List<OrderItem> orderItems=new ArrayList<OrderItem>();
		double money=0;
		for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
			Product p=entry.getKey();
			int buynum=entry.getValue();
			OrderItem orderItem=new OrderItem(order, p, buynum);
			orderItems.add(orderItem);
			money+=(p.getPrice()*buynum);
			
		}
		try {
			OrderService os=new OrderServiceImpl();
			order.setOrderItems(orderItems);
			order.setMoney(money);
			os.addOrder(order);
			cart.clear();
			req.getRequestDispatcher("pay.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			resp.sendRedirect("index.jsp");
			e.printStackTrace();
			throw new ProductException("服务失败");
			
		}
		
		
	}
		//取得数据
		
	//调用
	
	
}
