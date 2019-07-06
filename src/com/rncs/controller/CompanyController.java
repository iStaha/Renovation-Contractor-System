package com.rncs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rncs.dao.CompanyDAO;
import com.rncs.dao.CompanyDAOImpl;

import com.rncs.model.Company;

/**
 * Servlet implementation class CompanyController
 */
@WebServlet("/company")
public class CompanyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CompanyDAO companyDAO;
	RequestDispatcher dispatcher;

	public CompanyController() {
		companyDAO = new CompanyDAOImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		System.out.println("++++ACTION+++++ " + action);

		if (action == null) {
			action = "list";
		}

		try {
			switch (action) {
			case "add":
				System.out.println("ADD Company");
				dispatcher = request.getRequestDispatcher("addCompany.jsp");
				dispatcher.forward(request, response);
				// showRegisrationForm(request, response);

				break;
			case "list":
				System.out.println("Company List");
				/*
				 * dispatcher = request.getRequestDispatcher("addCompany.jsp");
				 * dispatcher.forward(request, response);
				 */
				listCompanies(request, response);
				break;

			case "insert":
				System.out.println("INSERT COMPANY");
				// dispatcher = request.getRequestDispatcher("addCompany.jsp");
				// dispatcher.forward(request, response);
				insertCompany(request, response);

				break;

			case "EDIT":
				System.out.println("EDIT COMPANY");
				// dispatcher = request.getRequestDispatcher("addCompany.jsp");
				// dispatcher.forward(request, response);
				editCompany(request, response);

				break;

			/*
			 * default: System.out.println("LIst Default"); listCompanies(request,
			 * response); break;
			 */

			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	private void insertCompany(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		Company company = new Company();
		String regNo = request.getParameter("hdbRegisNo");
		String companyName = request.getParameter("companyName");
		String businessMode = request.getParameter("buisinessMide");
		String buisinessRegisNo = request.getParameter("buisRegisNo");
		String brNoEffectiveDate = request.getParameter("buisRegisNoEffectiveDate");
		String brNoExpiryDate = request.getParameter("buisRegisNoExpiryeDate");

		company.setHdbRegistrationNo(regNo);
		company.setCompanyName(companyName);
		company.setModeOfBuisiness(businessMode);
		company.setBuisinessRegistrationNo(buisinessRegisNo);
		company.setBusinessRegistrationEffectiveDate(brNoEffectiveDate);
		company.setBusinessRegistrationExpiryDate(brNoExpiryDate);

		if (request.getParameter("id") == null) {
			companyDAO.save(company);
		}

		else {
			company.setId(Integer.parseInt(request.getParameter("id")));
			System.out.println("UPdate");
			 companyDAO.update(company);
		}
		
	
		response.sendRedirect("company");
	}

	private void listCompanies(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession ses = request.getSession(false);
		System.out.println("Guzaa "+ses.getAttribute("username" ));
		

		List<Company> theList = companyDAO.get();
		request.setAttribute("list", theList);
		System.out.println("LIST COMPANY");

		dispatcher = request.getRequestDispatcher("company.jsp");

		dispatcher.forward(request, response);
	}

	private void editCompany(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		Company company = companyDAO.get(Integer.parseInt(id));

		request.setAttribute("company", company);

		dispatcher = request.getRequestDispatcher("addCompany.jsp");

		dispatcher.forward(request, response);
	}
}
