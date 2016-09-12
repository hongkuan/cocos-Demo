package com.kuange.musiconline.util;

/**
 * 工厂类 生产想要类型的dao
 * 
 * @author Administrator
 *
 */

public class DaoFactory {
	/**
	 * 根据传来的传来的类生想要的dao实例 是通过反射实现
	 * 
	 * @param type
	 * @return
	 */
	public static Object getInstance(String type) {
		// 通过type参数读取配置文件
		// 获取实现类的全限定名
		String impl = Daoconfig.getV(type);
		try {
			Class c = Class.forName(impl);
			return c.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
