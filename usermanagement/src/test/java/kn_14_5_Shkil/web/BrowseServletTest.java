package kn_14_5_Shkil.web;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kn_14_5_Shkil.User;

public class BrowseServletTest extends MockServletTestCase {

	@Override
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(BrowseServlet.class);
	}

	@Test
	public void testBrowse() {
		User user = new User(1000L, "Arthur", "Shkil", new Date());
		List<User> list = Collections.singletonList(user);
		getMockUserDao().expectAndReturn("findAll", list);
		doGet();
		Collection<User> collection = (Collection<User>) getWebMockObjectFactory().getMockSession()
				.getAttribute("users");
		assertNotNull("Could not find list of users in session", collection);
		assertSame(list, collection);
	}

	@Test
	public void testEdit() {
		User user = new User(1000L, "Arhutr", "Shkil", new Date());
		List<User> list = Collections.singletonList(user);
		getMockUserDao().expectAndReturn("findAll", list);
		getMockUserDao().expectAndReturn("find", new Long(1000), user);
		addRequestParameter("editButton", "Edit");
		addRequestParameter("id", "1000");
		doPost();
		User userInSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
		assertNotNull("Could not find user in session", userInSession);
		assertSame(user, userInSession);
	}
}
