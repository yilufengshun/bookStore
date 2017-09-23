package com.meng.book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meng.book.exception.UserException;
import com.meng.book.service.UserService;
import com.meng.book.service.impl.UserServiceImpl;


@WebServlet("/activeuser")
public class ActiveUserServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String activeCode = req.getParameter("activeCode");
			UserService us = new UserServiceImpl();
			System.out.println(activeCode);
			if (activeCode != null){
				
				us.activeUser(activeCode);
				req.getRequestDispatcher("activesuccess.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("激活失败");
		}

	}
}
