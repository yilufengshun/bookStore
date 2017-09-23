package com.meng.book.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meng.book.domain.Order;
import com.meng.book.domain.User;
import com.meng.book.service.OrderService;
import com.meng.book.service.impl.OrderServiceImpl;

@WebServlet("/findOrderByUser")
public class FindOrderByUserServlet extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doPost(req, resp);
		
		}
		
		
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
			User user=(User) req.getSession().getAttribute("user");
			PrintWriter out =resp.getWriter();
			if(user==null){
				out.print("<script type='text/javascript'>alert('请登录');window.location.href='login.jsp'</script>");
			return;
			}
			try {
				OrderService os=new OrderServiceImpl();
				List<Order> orders = os.findOrderByUser(user);
				req.setAttribute("ordersize", orders.size());
				req.setAttribute("orders", orders);
				req.getRequestDispatcher("orderlist.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				req.setAttribute("msg", "查询订单异常");
				req.getRequestDispatcher("orderlist.jsp").forward(req, resp);
			}
		}
		
}
