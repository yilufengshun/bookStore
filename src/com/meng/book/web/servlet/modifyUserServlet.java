package com.meng.book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meng.book.domain.User;
import com.meng.book.exception.UserException;
import com.meng.book.service.UserService;
import com.meng.book.service.impl.UserServiceImpl;

@WebServlet("/modifyUser")
public class modifyUserServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String password=req.getParameter("password");
		String repassword=req.getParameter("repassword");
		String gender=req.getParameter("gender");
		String telephone = req.getParameter("telephone");
		if(password==null||password.trim().equals("")){
			req.setAttribute("update_info", "密码不能为空");
			req.getRequestDispatcher("modifyuserinfo.jsp").forward(req, resp);
			return;
		}
		if(repassword==null||repassword.trim().equals("")){
			req.setAttribute("update_info", "重复密码不能为空");
			req.getRequestDispatcher("modifyuserinfo.jsp").forward(req, resp);
			return;
		}
		if(!repassword.equals(password)){
			req.setAttribute("update_info", "重复密码不一致");
			req.getRequestDispatcher("modifyuserinfo.jsp").forward(req, resp);
			return;
		}
		try {
			UserService us=new UserServiceImpl();
			User user=(User)req.getSession().getAttribute("user");
			if(user.getState()==0){
				req.setAttribute("update_info", "用户没有激活");
				req.getRequestDispatcher("modifyuserinfo.jsp").forward(req, resp);
				
			}
			user.setPassword(password);
			user.setGender(gender);
			user.setTelephone(telephone);
			us.modifyUser(user);
			req.getRequestDispatcher("modifyUserInfoSuccess.jsp").forward(req, resp);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("跟新失败");
		}
		
		
	}
}
