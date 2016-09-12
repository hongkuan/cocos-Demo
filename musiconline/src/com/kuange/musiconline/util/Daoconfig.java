package com.kuange.musiconline.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 用来读取daoconfig.properties配置文件 
 * 将读取到的值返回
 * @author Administrator
 *
 */
public class Daoconfig {
	private static String KEY_MUSIC_DB_URL = "musicDBUrl";
	private static String KEY_DB_USER_NAME = "DBUserName";
	private static String KEY_DB_USER_PASSWORD = "DBUserPassword";
	
	
	private static Properties prop=new Properties();
	static {
		try {
			prop.load(Daoconfig.class.getClassLoader().getResourceAsStream("com/kuange/musiconline/util/daoconfig.properties"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public static String getV(String key){
		return prop.getProperty(key);
	}
	
	public static String getMusicDBUrl(){
		return getV(KEY_MUSIC_DB_URL);
	}
	
	public static String getDBUserName(){
		return getV(KEY_DB_USER_NAME);
	}
	
	public static String getDBUserPassword(){
		return getV(KEY_DB_USER_PASSWORD);
	}
	
	public static void main(String[]args){
		System.out.println(getV("musicDao"));
	}
}
