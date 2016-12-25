package com.shkil.usermanagement;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class UserTest extends TestCase {
	private User user;
	private Date dateOfBirthd;
	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
		Calendar calendar = Calendar.getInstance();
		calendar.set(1997, Calendar.JULY, 27);
		dateOfBirthd = calendar.getTime();
	}
	public void testGetFullName(){
		user.setFirstName("Arhtur");
		user.setLastName("Shkil");
		assertEquals("Shkil, Arhtur", user.getFullName());
	}
	public void testGetAge(){
		user.setDateOfBirthd(dateOfBirthd);
		assertEquals(2016-1997, user.getAge());
	}