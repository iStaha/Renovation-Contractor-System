package com.rncs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rncs.dao.CompanyDAO;
import com.rncs.dao.CompanyDAOImpl;
import com.rncs.dao.PersonnelDAO;
import com.rncs.dao.PersonnelDAOImpl;
import com.rncs.model.Company;
import com.rncs.model.Personnel;

/**
 * Servlet implementation class PersonnelController
 */
@WebServlet("/personnel")
public class PersonnelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CompanyDAO companyDAO;
	PersonnelDAO personnelDAO;

	RequestDispatcher dispatcher;

	public PersonnelController() {
		companyDAO = new CompanyDAOImpl();
		personnelDAO = new PersonnelDAOImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");

		System.out.println("++++ACTION+++++ " + action);

		if (action == null) {
			action = "list";
		}

		try {
			switch (action) {
			case "add":
				System.out.println("ADD Personnel");

				dispatcher = request.getRequestDispatcher("addPersonnel.jsp");
				dispatcher.forward(request, response);

				insertPersonnel(request, response);

				break;
			case "list":
				System.out.println("List Personnel");
				listPersonel(request, response);
				break;

			case "insert":
				System.out.println("Insert Personnel");
				// dispatcher = request.getRequestDispatcher("addCompany.jsp");
				// dispatcher.forward(request, response);
				 insertPersonnel(request, response);

				break;

			case "edit":
				System.out.println("EDIT PERSONNEL");
				// dispatcher = request.getRequestDispatcher("addCompany.jsp");
				// dispatcher.forward(request, response);
				  editPersonnel(request, response);

				break;

			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	private void listPersonel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Company> theList = companyDAO.getAllCompanies();
		System.out.println("the LIst"+ theList);
		request.setAttribute("list", theList);
		System.out.println("LIST Personnel");

		dispatcher = request.getRequestDispatcher("addPersonnel.jsp");

		dispatcher.forward(request, response);
	}

	private void insertPersonnel(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		Personnel personnel = new Personnel();
		String name = request.getParameter("name");
		String appointment = request.getParameter("appointment");
		String appointmentDate = request.getParameter("appointmentDate");
		String resignationDate = request.getParameter("resignationDate");
		String status = request.getParameter("status");
		String mobile = request.getParameter("mobile");
		int companyId = Integer.parseInt(request.getParameter("company"));
		
		
		personnel.setNameOfPersonnel(name);
		personnel.setAppointment(appointment);
		personnel.setDateOfAppointment(appointmentDate);
		personnel.setDateOfResignation(resignationDate);
		personnel.setDateOfResignation(resignationDate);
		personnel.setStatus(status);
		personnel.setMobileNo(mobile);

		personnel.setCompanyId(companyId);;


		if (request.getParameter("id") == null) {
			personnelDAO.save(personnel);
		}

		else {
			personnel.setId(Integer.parseInt(request.getParameter("id")));
			System.out.println("UPdate");
			personnelDAO.update(personnel);
		}

		response.sendRedirect("company");
	}
	
	private void editPersonnel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		Personnel personnel = personnelDAO.get(Integer.parseInt(id));

	    //System.out.println(personnel.getStatus());
		
		List<Company> theList = companyDAO.getAllCompanies();
		
		request.setAttribute("list", theList);
		
		
	
		
		request.setAttribute("personnel", personnel);

		dispatcher = request.getRequestDispatcher("addPersonnel.jsp");

		dispatcher.forward(request, response);
	}

}
