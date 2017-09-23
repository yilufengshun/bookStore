package com.meng.book.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * threadlocal管理器
 * 
 * */
public class ThreadLocalManager {
	private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();
	
	public static Connection getConnection() throws SQLException{
		//先判断又没有conn
		Connection conn=tl.get();
		if(conn==null)
		{
			conn=C3p0Utils.getConnection();
			tl.set(conn);
		}
			
		return conn;
		
	}
	public static void setAutoCommit() throws SQLException{
		getConnection().setAutoCommit(false);
		
	}
	
	
	public static void commit() throws SQLException{
		
		getConnection().commit();

	}
	public static void rollback() throws SQLException{
		getConnection().rollback();
	}
	
	
}
