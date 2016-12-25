package kn_14_5_Shkil.db;

import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Before;
import org.junit.Test;

import kn_14_5_Shkil.User;

public class HsqldbUserDaoTest extends DatabaseTestCase {

	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;

	@Override
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		dao = new HsqldbUserDao(connectionFactory);
	}

	@Test
	public void testCreate() {
		try {
			User user = new User();
			user.setFirstName("Arhutr");
			user.setLastName("Shkil");
			user.setDateOfBirthd(new Date());
			assertNull(user.getId());
			user = dao.create(user);
			assertNotNull(user);
			assertNotNull(user.getId());
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl("org.hsqldb.jdbcDriver", "jdbc:hsqldb:file:db/usermanagement",
				"sa", "");
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testFindAll() {
		try {
			java.util.Collection collection = dao.findAll();
			assertNotNull("Collection is null!", collection);
			assertEquals("Collection size!", 2, collection.size());

		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testFind() {
		try {
			User user = dao.find(1001L);
			assertNotNull(user);
			assertEquals(new Long(1001), user.getId());
			assertEquals("George", user.getFirstName());
			assertEquals("Bush", user.getLastName());

		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}

	}

	@Test
	public void testUpdate() {
		try {
			User user = new User();
			user.setFirstName("Arhutr");
			user.setLastName("Shkil");
			user.setDateOfBirthd(new Date());
			user.setId(1001L);
			user = dao.update(user);
			assertNotNull(user);
			User test = dao.find(1001L);
			assertEquals(user.getFirstName(), test.getFirstName());
			assertEquals(user.getLastName(), test.getLastName());
			assertEquals(user.getDateOfBirthd(), test.getDateOfBirthd());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testDelete() {
		try {
			User user = dao.find(1001L);
			user = dao.delete(user);
			User test = dao.find(user.getId());
			assertNull(test);
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}

	}
}
