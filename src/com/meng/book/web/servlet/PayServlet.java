package com.meng.book.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/pay")
public class PayServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String order_id=req.getParameter("order_id");
		PrintWriter out = resp.getWriter();
		if(order_id==null||order_id.trim().equals("")){
			req.getRequestDispatcher("findOrderByUser").forward(req, resp);
			return;}
		req.setAttribute("order_id", order_id);
		req.getRequestDispatcher("pay.jsp").forward(req, resp);
		
	}
	
	
	
}
