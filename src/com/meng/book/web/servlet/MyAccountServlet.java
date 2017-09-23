package com.meng.book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meng.book.domain.User;


@WebServlet("/myaccount")
public class MyAccountServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user=(User) req.getSession().getAttribute("user");
		if(user!=null){
			//tiaodao 个人信息
			req.getRequestDispatcher("myAccount.jsp").forward(req, resp);
			
		}else {
			resp.sendRedirect("login.jsp");
		}
		
		
	}
	
}
