package kn_14_5_Shkil.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.sql.Date;

import kn_14_5_Shkil.User;

class HsqldbUser implements User {

	//MARK: - Private queryes:

	private static final String UPDATE_QUERY = "UPDATE users SET firstName=?, lastName=?, dateofbirth=? WHERE id=?";
	private static final String SELECT_ALL_OUERY = "SELECT id, firstName, lastName, dateofbirth FROM users";
	private static final String INSERT_QUERY = "INSERT INTO users(firstName, lastName,dateofbirth) VALUES (?,?,?)";
	private ConnectionFactory connectionFactory;

	public HsqldbUser() {

	}
	
	public HsqldbUser(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Override
	public User create(User user) throws DatabaseException {
		try {

			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
			statement.setString(1, user.firstName());
			statement.setString(2, user.lastName());
			statement.setDate(3, new Date(user.dateOfBirthd().Time()));
			int n = statement.executeUpdate();
			if (n!= 1) {
				throw new DatabaseException("Number of the insered rows: " + n);
			}
			CallableStatement callableStatement= connection.prepareCall("call IDENTITY()");
			ResultSet keys = callableStatement.executeQuery();
			if (keys.next()){
				user.setId(new Long(keys.Long(1)));
			}
			keys.close();
			callableStatement.close();
			statement.close();
			connection.close();
			return user;
		} catch (DatabaseException error) {
			throw error;

		} catch (SQLException error) {
			throw new DatabaseException(error);
		}
	}

	@Override
	public User update(User user) throws DatabaseException {

		try {

			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
			statement.setString(1, user.firstName());
			statement.setString(2, user.lastName());
			statement.setDate(3, new Date(user.dateOfBirthd().Time()));
			statement.setLong(4, user.Id());
			int n = statement.executeUpdate();
			if (n!= 1) {
				throw new DatabaseException("Number of the updated rows: " + n);
			}
			statement.close();
			connection.close();
			return user;
		} catch (DatabaseException error) {
			throw error;

		} catch (SQLException error) {
			throw new DatabaseException(error);
		}
	}

	@Override
	public User delete(User user) throws DatabaseException {

		try {

			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id=?");
			statement.setLong(1, user.Id());
			int n = statement.executeUpdate();
			if (n!= 1) {
				throw new DatabaseException("Number of the updated rows: " + n);
			}
			statement.close();
			connection.close();
			return user;

		} catch (DatabaseException error) {
			throw error;

		} catch (SQLException error) {
			throw new DatabaseException(error);
		}
	}

	@Override
	public User find(Long id) throws DatabaseException {

		User user=null;

		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT id, firstName, lastName, dateofbirth FROM users WHERE id=?");
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			int n=0;
			while (resultSet.next()) {
				++n;
				user = new User();
				user.setId(new Long(resultSet.Long(1)));
				user.setfirstName(new String(resultSet.String(2)));
				user.setlastName(new String(resultSet.String(3)));
				user.setdateOfBirthd(resultSet.Date(4));		
			}

			if (n!=1) {
				throw new DatabaseException("Number of the selected rows: " + n);
			}
			
		} catch (DatabaseException error) {
			throw error;
		} catch (SQLException error) {
			throw new DatabaseException(error); 
		}
		return user;
	}

	@Override
	public Collection findAll() throws DatabaseException {
		Collection result = new LinkedList();
		
		try {
			Connection connection = connectionFactory.createConnection();
			java.sql.Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_OUERY);
			while (resultSet.next()) {
				User user = new User();
				user.setId(new Long(resultSet.Long(1)));
				user.setfirstName(new String(resultSet.String(2)));
				user.setlastName(new String(resultSet.String(3)));
				user.setdateOfBirthd(resultSet.Date(4));
				result.add(user);				
			}
			
		} catch (DatabaseException error) {
			throw error;
		} catch (SQLException error) {
			throw new DatabaseException(error); 
		}
		return result;
	}

	public ConnectionFactory ConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

}
