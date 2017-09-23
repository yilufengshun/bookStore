package com.meng.book.service;

import com.meng.book.domain.User;
import com.meng.book.web.servlet.loginservlet;

public interface UserService {
		public void regist(User user);
		public void activeUser(String activeCode);
		public User login (String username,String password);
		public void modifyUser(User user);
		
}
