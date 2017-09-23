package com.meng.book.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.meng.book.dao.UserDao;
import com.meng.book.domain.User;
import com.meng.book.exception.UserException;
import com.meng.book.utils.C3p0Utils;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class UserDaoImpl implements UserDao{

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		//直接将连接池赋给它
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="INSERT INTO USER(username,PASSWORD,gender,email,telephone,introduce,activeCode,state)VALUES(?,?,?,?,?,?,?,?)";
		try {
			runner.update(sql,user.getUsername(),user.getPassword(),user.getGender(),user.getEmail(),user.getTelephone(),user.getIntroduce(),user.getActiveCode(),user.getState()  );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			throw new UserException(this.getClass()+"添加用户异常",e);
		}
	}

	@Override
	public User findUserByActiveCode(String activeCode) {
		// TODO Auto-generated method stub
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		User user=null;
		try {
		user=	runner.query("select * from user where activeCode = ?", new BeanHandler<User>(User.class),activeCode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("激活失败",e);
		}
		
		return user;
	}

	@Override
	public void activeUser(String activeCode) {
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
	
		try {
			runner.update("update user set state=1 where activeCode=?",activeCode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("激活失败",e);
		}
		
		
	}

	@Override
	public User findUserByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		User user=null;
		try {
		user=	runner.query("select * from user where username = ? and password= ?", new BeanHandler<User>(User.class),username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("登录失败",e);
		}
		
		return user;
	}

	@Override
	public void modifyUser(User user) {
		// TODO Auto-generated method stub
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		try {
			runner.update("update user set password=?,telephone=? ,gender=? where id=?",user.getPassword(),user.getTelephone(),user.getGender(),user.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("更新失败",e);
		}
		
	}

}
