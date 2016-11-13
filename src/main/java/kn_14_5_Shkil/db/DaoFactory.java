package kn_14_5_Shkil.db;

import java.io.IOException;
import java.util.Properties;

public class DaoFactory {
	
	private static final String USER_DAO = "dao.kn_14_5_Shkil.usermanagement.db.User";
	private final Properties properties;
	private final static DaoFactory INSTANCE = new DaoFactory();
	    
	public static DaoFactory getInstance() {
		return INSTANCE;
	}

	public Factory() {

		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
		} catch (IOException error) {
			throw new RuntimeException(error);
		}
	}
	
	private ConnectionFactory getConnectionFactory() {

		String user = properties.getProperty("connection.user");
		String driver = properties.getProperty("connection.driver");
		String url = properties.getProperty("connection.url");
		String password = properties.getProperty("connection.password");
		return new ConnectionFactoryImpl(driver, url, user, password);
	}
	
	public User user() {

		User result = null;

		try {
			Class userClass	= Class.forName(properties.getProperty(USER_DAO));
			result = (User) userClass.newInstance();
			result.setConnectionFactory(getConnectionFactory());;
		} catch (Exception error) {
			throw new RuntimeException(error); 
		}
		
		return result;
	}

}
