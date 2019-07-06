package com.rncs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rncs.model.Company;
import com.rncs.model.Personnel;
import com.rncs.util.ConnectionPool;
import com.rncs.util.DBUtil;

public class PersonnelDAOImpl implements PersonnelDAO {

	private static final String INSERT_PERSONNEL_SQL = "INSERT INTO personnel"
			+ "  (name, appointment, dateOfAppointment, dateOfResignation, status, mobileNo, company_id) " + "VALUES "
			+ " (?, ?, ?, ?, ?, ?, ?);";

	private static final String SELECT_PERSONNEL_BY_ID = "select * from personnel where id = ?";
	private static final String SELECT_PRODUCT_BY_USER_ID = "select id,item from products where user_id =?";
	private static final String SELECT_ALL_PRODUCTS = "select products.item, products.id from products where user_id= ?;";
	private static final String SELECT_ALL_PERSONNEL = "SELECT * FROM company";/*
																				 * "SELECT * FROM users, products where users.id=products.user_id  group by users.id"
																				 * ;
																				 */
	private static final String DELETE_PERSONNEL_SQL = "delete from users where id = ?;";
	private static final String UPDATE_PERSONNEL_SQL = "update personnel set name = ?, appointment =? , dateOfAppointment = ?, dateOfResignation =?, status =?, mobileNo =?, company_id =? where id = ?;";
	private static final String UPDATE_PRODUCTS_SQL = "update products set  user_id = ? where id = ?;";

	@Override
	public List<Personnel> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personnel get(int id) {
		// TODO Auto-generated method stub
		Personnel personnel = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement psPersonnel = null;
		ResultSet rsPersonnel = null;

		try {
			personnel = new Personnel();
			psPersonnel = connection.prepareStatement(SELECT_PERSONNEL_BY_ID);
			psPersonnel.setInt(1, id);
			System.out.println(psPersonnel);
			rsPersonnel = psPersonnel.executeQuery();

			while (rsPersonnel.next()) {
				personnel.setId(id);
				personnel.setNameOfPersonnel(rsPersonnel.getString("name"));
				personnel.setAppointment(rsPersonnel.getString("appointment"));
				personnel.setDateOfAppointment(rsPersonnel.getString("dateOfAppointment"));
				personnel.setDateOfResignation(rsPersonnel.getString("dateOfResignation"));
				personnel.setStatus(rsPersonnel.getString("status"));
				personnel.setMobileNo(rsPersonnel.getString("mobileNo"));
				personnel.setCompanyId(Integer.parseInt(rsPersonnel.getString("company_id")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			DBUtil.closeResultSet(rsPersonnel);
			DBUtil.closeStatment(psPersonnel);
			pool.freeConnection(connection);
		}

		return personnel;

	}

	@Override
	public boolean save(Personnel personnel) {
		boolean flag = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement psPersonnel = null;
		try {
			psPersonnel = connection.prepareStatement(INSERT_PERSONNEL_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
			psPersonnel.setString(1, personnel.getNameOfPersonnel());
			psPersonnel.setString(2, personnel.getAppointment());
			psPersonnel.setString(3, personnel.getDateOfAppointment());
			psPersonnel.setString(4, personnel.getDateOfResignation());
			psPersonnel.setString(5, personnel.getStatus());
			psPersonnel.setString(6, personnel.getMobileNo());
			psPersonnel.setInt(7, personnel.getCompanyId());
			psPersonnel.executeUpdate();
			flag = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeStatment(psPersonnel);
			pool.freeConnection(connection);
		}
		return flag;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Personnel personnel) {

		boolean flag = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement psPersonnel = null;
		ResultSet rsPersonnel = null;

		try {

			psPersonnel = connection.prepareStatement(UPDATE_PERSONNEL_SQL);

			psPersonnel.setString(1, personnel.getNameOfPersonnel());
			psPersonnel.setString(2, personnel.getAppointment());
			psPersonnel.setString(3, personnel.getDateOfAppointment());
			psPersonnel.setString(4, personnel.getDateOfResignation());
			psPersonnel.setString(5, personnel.getStatus());
			psPersonnel.setString(6, personnel.getMobileNo());
			psPersonnel.setInt(7, personnel.getCompanyId());
			psPersonnel.setInt(8, personnel.getId());

			flag = psPersonnel.executeUpdate() > 0;
			psPersonnel.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			DBUtil.closeResultSet(rsPersonnel);
			DBUtil.closeStatment(psPersonnel);
			pool.freeConnection(connection);
		}
		return flag;
	}

}
