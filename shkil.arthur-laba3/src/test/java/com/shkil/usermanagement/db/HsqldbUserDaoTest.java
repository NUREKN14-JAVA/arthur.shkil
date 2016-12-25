package com.shkil.usermanagement.db;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Before;
import org.junit.Test;

import com.shkil.usermanagement.User;

import junit.framework.TestCase;

public class HsqldbUserDaoTest extends DatabaseTestCase {

	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		dao = new HsqldbUserDao(connectionFactory);
	}

	@Test
	public void testCreate() {
		try {
			User user = new User();
			user.setFirstName("arhur");
			user.setLastName("shkil");
			user.setDateOfBirthd(new Date());
			assertNull(user.getId());
			user = dao.create(user);
			assertNotNull(user);
			assertNotNull(user.getId());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	public void testFindAll() {
		try {
			Collection collection = dao.findAll();
			assertNotNull("Collection is null", collection);
			assertEquals("Collection size.", 2, collection.size());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}

	}
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl("org.hsqldb.jdbcDriver",
				"jdbc:hsqldb:file:db/usermanagement","sa","");
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader()
				.getResourceAsStream("userDataSet.xml"));

		return dataSet;
	}
	public void testFind(){
		Long testing_id = new Long(1000);

		Calendar calendar = Calendar.getInstance();
		calendar.set(1968, Calendar.APRIL, 26);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

		try
		{
			User user = dao.find(testing_id);
			
			assertNotNull("testFind failed - no user 1000", user.getId());
			
			assertEquals("testFind failed - no user fullname", "arhur, shkil", user.getFullName());
			
			assertEquals("testFind failed - no user.getID", testing_id, user.getId());
			
			assertEquals("testFind failed - DoB doesnt match ",format1.format(calendar.getTime()),format1.format(user.getDateOfBirthd().getTime()));
		}
		catch(DatabaseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}
    
    public void testUpdate() {
    	Long testing_id = new Long(1000);
    	
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(1969, Calendar.DECEMBER, 28);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			User user = new User();
			user.setFirstName("Linus");
			user.setLastName("Torvalds");
			user.setDateOfBirthd(calendar.getTime());
			user.setId(testing_id);
			
			dao.update(user);
			User updated_user = dao.find(testing_id);

			assertNotNull("testUpdate failed - no user 1000", updated_user.getId());
			
			assertEquals("testUpdate failed - full name doesnt match", user.getFullName(), updated_user.getFullName());
			
			assertEquals("testFind failed - DoB doesnt match ",format1.format(calendar.getTime()),format1.format(updated_user.getDateOfBirthd().getTime()));
		}
		catch(DatabaseException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

    
    public void testDelete() {
    		try
    		{
    			User user = dao.find(new Long(1000));
    			dao.delete(user);
    			user = dao.find(new Long(1000));
    			
    			assertNull("testDelete failed - user 1000 wasnt deleted", user.getId());
    		}
    		catch(DatabaseException e)
    		{
    			e.printStackTrace();
    			fail(e.toString());
    		}
    	}
 
}
