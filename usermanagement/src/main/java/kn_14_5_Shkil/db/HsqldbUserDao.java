package kn_14_5_Shkil.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import kn_14_5_Shkil.User;

class HsqldbUserDao implements UserDao {
	private static final String DELETE_QUERY = "DELETE FROM users WHERE id=?";
	private static final String FIND_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE id=?";
	private static final String UPDATE_QUERY = "UPDATE users SET firstname=?, lastname=?, dateofbirth=? WHERE id=?";
	private static final String SELECT_ALL_OUERY = "SELECT id, firstname, lastname, dateofbirth FROM users";
	private static final String INSERT_QUERY = "INSERT INTO users(firstname, lastname,dateofbirth) VALUES (?,?,?)";
	private ConnectionFactory connectionFactory;
	int t = 0;

	public HsqldbUserDao() {
	}

	public HsqldbUserDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Override
	public User create(User user) throws DatabaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDateOfBirthd().getTime()));
			int n = statement.executeUpdate();
			if (n != 1) {
				throw new DatabaseException("Number of the insered rows: " + n);
			}
			CallableStatement callableStatement = connection.prepareCall("call IDENTITY()");
			ResultSet keys = callableStatement.executeQuery();
			if (keys.next()) {
				user.setId(new Long(keys.getLong(1)));
			}
			keys.close();
			callableStatement.close();
			statement.close();
			connection.close();
			return user;
		} catch (DatabaseException e) {
			throw e;

		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	public User update(User user) throws DatabaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDateOfBirthd().getTime()));
			statement.setLong(4, user.getId());
			int n = statement.executeUpdate();
			if (n != 1) {
				throw new DatabaseException("Number of the updated rows: " + n);
			}
			statement.close();
			connection.close();
			return user;
		} catch (DatabaseException e) {
			throw e;

		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	public User delete(User user) throws DatabaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
			statement.setLong(1, user.getId());
			int n = statement.executeUpdate();
			if (n != 1) {
				throw new DatabaseException("Number of the changed rows: " + n);
			}
			statement.close();
			connection.close();
			return user;
		} catch (DatabaseException e) {
			throw e;

		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	public User find(Long id) throws DatabaseException {
		User user = null;
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(FIND_QUERY);
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			int n = 0;
			while (resultSet.next()) {
				++n;
				user = new User();
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(new String(resultSet.getString(2)));
				user.setLastName(new String(resultSet.getString(3)));
				user.setDateOfBirthd(new java.util.Date(resultSet.getDate(4).getTime()));
			}
			if (n > 1) {
				throw new DatabaseException("Number of the selected rows: " + n);
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException(e);
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
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(new String(resultSet.getString(2)));
				user.setLastName(new String(resultSet.getString(3)));
				user.setDateOfBirthd(resultSet.getDate(4));
				result.add(user);
			}

		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		return result;
	}

	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	@Override
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

}
