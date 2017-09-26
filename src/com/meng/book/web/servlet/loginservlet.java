package com.meng.book.web.servlet;

import java.beans.Encoder;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meng.book.domain.User;
import com.meng.book.service.UserService;
import com.meng.book.service.impl.UserServiceImpl;


@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doPost(req, resp);
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取用户名密码
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		
		
		if(username==null||username.trim().equals("")){
			req.setAttribute("register_message", "用户名不能为空");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			
		}
		if(password==null||password.trim().equals("")){
			req.setAttribute("register_message", "密码不能为空");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			
		}
		//记录应户名，自动冷露cookie
		String remusername=req.getParameter("remusername");
		String autologin=req.getParameter("autologin");
		
		String returl=req.getParameter("returl");
		
		
		try {
			UserService us=new UserServiceImpl();
			User user=	us.login(username, password);
			
			if(user!=null){
				
				
				Cookie cookie=new Cookie("remusername",URLEncoder.encode(username, "utf-8"));
				Cookie cookie2=new Cookie("autologin",URLEncoder.encode(username, "utf-8")+"#"+password);
				if(remusername!=null){
				
				cookie.setMaxAge(7*24*3600);
				
				}else{
					cookie.setMaxAge(0);
				}
				resp.addCookie(cookie);
				if(autologin!=null){
					cookie2.setMaxAge(7*24*3600);
					
					
				}else{
					resp.addCookie(cookie2);
				}
				resp.addCookie(cookie2);
				req.getSession().setAttribute("user", user);
				if(username.equals("admin")){
					resp.sendRedirect("admin/login/home.jsp");
					return;
				}
				
				//判断又没有要返回的url
				if(returl==null&&returl.trim().equals("")){
					resp.sendRedirect(returl);
					return;
				}
				req.getRequestDispatcher("myAccount.jsp").forward(req, resp);
				
			}else{
				req.setAttribute("register_message", "账号密码错误");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		
		
		
	}
}
