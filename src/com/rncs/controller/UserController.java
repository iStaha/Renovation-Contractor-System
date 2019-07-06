package com.rncs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.Set;
import javax.validation.ConstraintViolation;

import com.rncs.dao.CompanyDAO;
import com.rncs.dao.CompanyDAOImpl;
import com.rncs.dao.UserDAO;
import com.rncs.dao.UserDAOImpl;
import com.rncs.model.Company;
import com.rncs.model.User;

@WebServlet("/")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	UserDAO userDAO;
	CompanyDAO companyDAO;
	RequestDispatcher dispatcher;

	public UserController() {
		userDAO = new UserDAOImpl();
		companyDAO = new CompanyDAOImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/register":
				System.out.println("Registration Submit Form");

				signUp(request, response);

				break;
			case "/login":
				System.out.println("Login Submit   Form");
				login(request, response);

				break;

			// default: listUser(request, response); break;

			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/register":
				System.out.println("Registration Form");
				dispatcher = request.getRequestDispatcher("register.jsp");
				dispatcher.forward(request, response);
				// showRegisrationForm(request, response);

				break;
			case "/login":
				System.out.println("Login Form");
				dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
				// showLoginForm(request, response);

				break;
				
			case "/logout":
				System.out.println("Logout");
				/*
				 * HttpSession ses = request.getSession(false); ses.invalidate();
				 */
				dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
				// showLoginForm(request, response);

				break;

			default:
				dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
				break;

			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	private void signUp(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = new User(username, name, email, password);

		System.out.println("----UERS------" + user);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<User>> violations = validator.validate(user);

		for (ConstraintViolation<User> violation : violations) {
			System.err.println(violation.getMessage());
		}

		if (!violations.isEmpty()) {
			request.setAttribute("violations", violations);
			request.getRequestDispatcher("register.jsp").forward(request, response);
		} else {
			userDAO.save(user);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

		// userDAO.Create(newUser);
		// response.sendRedirect("register");

	}

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = new User();
		user.setUserName(username);
		user.setPassword(password);

		// System.out.println("===UERS====" + user);

		boolean bool = userDAO.authenticate(user);
		
		System.out.println("===BOOL====" + bool);

		if (bool) {
			System.out.println("Inside Bool");
			HttpSession ses = request.getSession();
			ses.setAttribute("username", username );
			
			ses.setMaxInactiveInterval(30*60);
			Cookie userName = new Cookie("username",username);
			userName.setMaxAge(30*60);
			response.addCookie(userName);
			
			
			
			
			System.out.println("the session attribute that was set "+ ses.getAttribute("username"));
			System.out.println("Session Id User Controller " + ses.getId());

			List<Company> theList = companyDAO.get();
			request.setAttribute("list", theList);
			request.getRequestDispatcher("/company").forward(request, response);
		//	response.sendRedirect("company");
			/*
			 * response.encodeURL("company"); response.sendRedirect("company");
			 */
		} else {
			String fail = "Username or Paswoord is Wrong!";
			request.setAttribute("fail", fail);
			request.getRequestDispatcher("/login.jsp").forward(request, response);// forwarding the request
		}

	}

}
