package com.meng.book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meng.book.domain.Order;
import com.meng.book.service.OrderService;
import com.meng.book.service.impl.OrderServiceImpl;


@WebServlet("/findOrderInfoById")
public class findOrderInfoById extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doPost(req, resp);
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
			//接受orderid
			String order_id=req.getParameter("order_id");
			//调用业务
			try {
				OrderService os=new OrderServiceImpl();
				Order order=os.findOrderById(order_id);
				req.setAttribute("order", order);
				req.getRequestDispatcher("orderInfo.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				req.setAttribute("msg", "查询失败");
				req.getRequestDispatcher("orderInfo.jsp").forward(req, resp);
				e.printStackTrace();
			}
			
		}
		
}
