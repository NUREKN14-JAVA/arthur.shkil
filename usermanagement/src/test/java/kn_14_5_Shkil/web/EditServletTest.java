package kn_14_5_Shkil.web;

import java.text.DateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import kn_14_5_Shkil.User;

public class EditServletTest extends MockServletTestCase {

	@Override
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(EditServlet.class);
	}

	@Test
	public void testEdit() {
		Date date = new Date();
		User user = new User(1000L, "Arthur", "Shkil", date);
		getMockUserDao().expectAndReturn("update", user, user);
		addRequestParameter("id", "1000");
		addRequestParameter("firstName", "Arthur");
		addRequestParameter("lastName", "Shkil");
		addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
	}

	@Test
	public void testEditEmptyFirstName() {
		Date date = new Date();
		addRequestParameter("id", "1000");
		addRequestParameter("lastName", "Shkil");
		addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

	@Test
	public void testEditEmptyLastName() {
		Date date = new Date();
		addRequestParameter("id", "1000");
		addRequestParameter("firstName", "Arthur");
		addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

	@Test
	public void testEditEmptyDateOfBirth() {
		Date date = new Date();
		addRequestParameter("id", "1000");
		addRequestParameter("firstName", "Arthur");
		addRequestParameter("lastName", "Shkil");
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

	@Test
	public void testEditEmptyDateIncorrect() {
		Date date = new Date();
		addRequestParameter("id", "1000");
		addRequestParameter("firstName", "Arthur");
		addRequestParameter("lastName", "Shkil");
		addRequestParameter("dateOfBirth", "sda");
		addRequestParameter("okButton", "Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
}
