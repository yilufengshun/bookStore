package com.meng.book.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meng.book.domain.User;
import com.meng.book.service.UserService;
import com.meng.book.service.impl.UserServiceImpl;


@WebFilter("/login.jsp")
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		Cookie[] cookies = request.getCookies();
		String username="";
		String password="";
		for (int i = 0; cookies!=null&&i < cookies.length; i++) {
			if ("user".equals(cookies[i].getName())){
				String value = cookies[i].getValue();
				String[] userStrings = value.split("[#]");
				username=userStrings[0];
				password=userStrings[1];
			}
		}
		if ((!"".equals(username))&&(!"".equals(password))) {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			UserService us=new UserServiceImpl();
			try {
				User u = us.login(username,password);
				if (u!=null) {
					request.getSession().setAttribute("user", u);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
		chain.doFilter(request, response);
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
