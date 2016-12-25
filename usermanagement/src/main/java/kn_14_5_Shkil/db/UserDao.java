package kn_14_5_Shkil.db;

import java.util.Collection;

import kn_14_5_Shkil.User;

public interface UserDao {
	User create(User user) throws DatabaseException;

	User update(User user) throws DatabaseException;

	User delete(User user) throws DatabaseException;

	User find(Long id) throws DatabaseException;

	Collection findAll() throws DatabaseException;

	void setConnectionFactory(ConnectionFactory connectionFactory);
}
