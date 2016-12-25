package com.shkil.usermanagement.db;

public class DaoFactoryImpl extends DaoFactory {
	public UserDao getUserDao(){
		UserDao result = null;
		try {
		
			Class clazz = Class.forName(properties.getProperty(USER_DAO));
			System.out.println("22");
			result = (UserDao) clazz.newInstance();
			result.setConnectionFactory(getConnectionFactory());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
}