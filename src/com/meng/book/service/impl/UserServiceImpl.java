package com.meng.book.service.impl;

import java.util.UUID;

import com.meng.book.dao.UserDao;
import com.meng.book.dao.impl.UserDaoImpl;
import com.meng.book.domain.User;
import com.meng.book.exception.UserException;
import com.meng.book.service.UserService;
import com.meng.book.utils.SendJmail;

public class UserServiceImpl implements UserService{

	@Override
	public void regist(User user) {
		// TODO Auto-generated method stub
		try {
			UserDao userDao=new UserDaoImpl();
			user.setActiveCode(UUID.randomUUID().toString());
			
			userDao.addUser(user);
			SendJmail.sendMail(user.getEmail(),"注册成功请<a href='http://localhost:8080/bookStore/activeuser?activeCode="+user.getActiveCode()+"'>激活</a>后登录");
			
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException(this.getClass()+"添加用户异常",e);
		}
	}

	@Override
	public void activeUser(String activeCode) {
		// TODO Auto-generated method stub
		try {
			UserDao ud=new UserDaoImpl();
			User user=ud.findUserByActiveCode(activeCode);
			if(user==null)
				throw new UserException("用户不存在");
			if(user.getState()!=0){
				throw new UserException("用户已经激活");
			}
			ud.activeUser(activeCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("激活失败",e);
		}
		
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			UserDao ud=new UserDaoImpl();
 user=	ud.findUserByUsernameAndPassword(username, password);

			if(user==null)
				throw new UserException("用户名或密码错误");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}return user;
	}

	@Override
	public void modifyUser(User user) {
		// TODO Auto-generated method stub
		try {
			UserDao ud=new UserDaoImpl();
			ud.modifyUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("更新失败",e);
		}
		
	}

}
