package com.meng.book.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Utils {
		
	private static ComboPooledDataSource cpds;
	static{
		cpds=new ComboPooledDataSource("mysql");
	}
	public static Connection getConnection() throws SQLException{
		
		return cpds.getConnection();
	}
	public static ComboPooledDataSource getDataSource(){
		return cpds;
	}
	
	public static void release(ResultSet rs,Statement stat,Connection conn){
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(stat!=null)
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
