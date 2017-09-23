package com.meng.book.dao;

import com.meng.book.domain.User;

public interface UserDao {
	public void addUser(User user);
	public User findUserByActiveCode(String activeCode);
	public void activeUser(String activeCode);
	public User findUserByUsernameAndPassword(String username,String password);
	public void modifyUser (User user);
}
