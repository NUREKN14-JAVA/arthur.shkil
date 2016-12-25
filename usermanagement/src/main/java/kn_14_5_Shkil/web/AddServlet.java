package kn_14_5_Shkil.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kn_14_5_Shkil.User;
import kn_14_5_Shkil.db.DaoFactory;
import kn_14_5_Shkil.db.DatabaseException;

public class AddServlet extends EditServlet {

	@Override
	protected void processUser(User user) throws DatabaseException {
		DaoFactory.getInstance().getUserDao().create(user);
	}
	
	@Override
	protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/add.jsp").forward(req, resp);
	}

}
