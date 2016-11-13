package kn_14_5_Shkil;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class UserTest extends TestCase {

	private User user;
	private Date dateOfBirthd;

	@Before
	protected void setUp() throws Exception {
		super.setUp();

		user = new User();
		Calendar calendar = Calendar.getInstance();
		calendar.set(1996, Calendar.NOVEMBER, 30);
		dateOfBirthd = calendar.getTime();
	}

	@Test
	public void testGetFullName() {
		user.setFirstName("Arhur");
		user.setLastName("Shkil");
		assertEquals("Shkil, Arhur", user.getFullName());
	}

	@Test
	public void testGetAge() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int currentYear = calendar.get(Calendar.YEAR);
		user.setDateOfBirthd(dateOfBirthd);
		assertEquals(currentYear - 1996, user.getAge());
	}
}
