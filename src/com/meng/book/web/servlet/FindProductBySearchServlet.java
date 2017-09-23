package com.meng.book.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meng.book.domain.PageBean;
import com.meng.book.domain.Product;
import com.meng.book.service.ProductService;
import com.meng.book.service.impl.ProductServiceImpl;
@WebServlet("/findProductBySearch")
public class FindProductBySearchServlet extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doPost(req, resp);
		}
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
				String search=req.getParameter("search");
				PrintWriter out=resp.getWriter();
				if(search==null||search.trim().equals("")){
					out.println("<script type='text/javascript'>alert('请输入搜索关键字');window.location.href='index.jsp'</script>");
					return;
				}
				//查询数据
				String pageIndex=req.getParameter("searchpageIndex");
				//panduan
				
				try {
					if(pageIndex==null)
						pageIndex="1";
					ProductService ps=new ProductServiceImpl();
					PageBean pageBean=ps.findProductBySearch(Integer.parseInt(pageIndex),search);
					req.setAttribute("pageBean", pageBean);
					req.setAttribute("search", search);
					req.getRequestDispatcher("search_list.jsp").forward(req, resp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					req.setAttribute("msg", "搜索失败");
					req.setAttribute("search", search);
					req.getRequestDispatcher("search_list.jsp").forward(req, resp);
				}
		}
}
