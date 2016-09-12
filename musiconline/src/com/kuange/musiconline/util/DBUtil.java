package com.kuange.musiconline.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	/**
	 * 
	 */
	private static ThreadLocal<Connection> thl=new ThreadLocal<Connection>();
	/**
	 * 数据库url
	 */
	private static String url = null;
	/**
	 * 数据库用户名
	 */
	private static String DBUserName = null;
	/**
	 * 数据库用户密码
	 */
	private static String DBUserPassWord = null;
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception{
		if(url == null){
			url = Daoconfig.getMusicDBUrl();
		}
		
		if(DBUserName == null){
			DBUserName = Daoconfig.getDBUserName();
		}
		
		if(DBUserPassWord == null){
			DBUserPassWord = Daoconfig.getDBUserPassword();
		}
		
		Connection conn = thl.get();
		if(conn==null){
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url, DBUserName, DBUserPassWord);
			thl.set(conn);
			
		}
		return conn;
	} 
	
	public static void close()throws Exception{
		Connection conn=thl.get();
		if(conn!=null){
			conn.close();
			thl.set(null);
		}
	}
	
	//开启事物
	public static void openTransaction() throws Exception{
		Connection conn = getConnection();
		conn.setAutoCommit(false);
	}
	//提交事物
	public static void commit()throws Exception{
		Connection conn = thl.get();
		if(conn!=null){
			conn.commit();
		}
	}
}
