package com.meng.book.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.meng.book.domain.User;
import com.meng.book.exception.UserException;
import com.meng.book.service.UserService;
import com.meng.book.service.impl.UserServiceImpl;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String sessionCode=(String)req.getSession().getAttribute("checkcode_session");
		String code=req.getParameter("ckcode");
		if(!code.equals(sessionCode)){
			req.setAttribute("msg", "验证错误");
			req.getRequestDispatcher("register.jsp").forward(req, resp);
			
		}
		
		
		PrintWriter out=resp.getWriter();
		User user=new User(); 
		try {
			BeanUtils.populate(user, req.getParameterMap());
			
			UserService us=new UserServiceImpl();
			us.regist(user);
			req.getRequestDispatcher("registersuccess.jsp").forward(req, resp);
			
		} catch (UserException e) {
			// TODO Auto-generated catch block
			req.setAttribute("msg", e.getMessage());
			req.getRequestDispatcher("register.jsp").forward(req, resp);
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
