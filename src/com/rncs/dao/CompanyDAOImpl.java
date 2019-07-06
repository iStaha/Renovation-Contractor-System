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


public class CompanyDAOImpl implements CompanyDAO {
	
	private static final String INSERT_COMPANY_SQL = "INSERT INTO company" + "  (companyName, hdbRegistrationNo, modeOfBuisiness, buisinessRegistrationNo, buisinessRegistraionEffectiveDate, buisinessRegistraionExpiryDate) "
			+ "VALUES " + " (?, ?, ?, ?, ?, ?);";


	private static final String SELECT_COMPANY_BY_ID = "select companyName, hdbRegistrationNo , modeOfBuisiness, buisinessRegistrationNo, buisinessRegistraionEffectiveDate, buisinessRegistraionExpiryDate from company where id = ?";
	private static final String SELECT_PRODUCT_BY_USER_ID = "select id,item from  where user_id =?";
	private static final String SELECT_ALL_PERSONNEL = "select * from personnel where company_id= ?;";
	private static final String SELECT_ALL_COMPANY = "SELECT * FROM company, personnel where company.id=personnel.company_id  group by company.id;";/*"SELECT * FROM users, products where users.id=products.user_id  group by users.id";*/
	private static final String SELECT_ALL_COMPANIES = "SELECT * FROM company;";
	private static final String DELETE_COMPANY_SQL = "delete from users where id = ?;";
	private static final String UPDATE_COMPANY_SQL = "update company set companyName = ?, hdbRegistrationNo =? , modeOfBuisiness = ?, buisinessRegistrationNo =?, buisinessRegistraionEffectiveDate =?, buisinessRegistraionExpiryDate =? where id = ?;";
	private static final String UPDATE_PRODUCTS_SQL = "update products set  user_id = ? where id = ?;";
	
	

	@Override
	public List<Company> get() {
		// TODO Auto-generated method stub
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement psCompany = null, psPersonnel = null;
		ResultSet rsCompany = null, rsPersonnel = null;
		
		List<Company> list = null;
		Company company = null;
		
		try {
			
			list = new ArrayList<Company>();
			psCompany = connection.prepareStatement(SELECT_ALL_COMPANY);
			psPersonnel = connection.prepareStatement(SELECT_ALL_PERSONNEL);
			rsCompany = psCompany.executeQuery();
			while(rsCompany.next()) {
				company = new Company();
				List<Personnel> personnel = new ArrayList<>();
				
				company.setId(rsCompany.getInt("id"));
				company.setCompanyName(rsCompany.getString("companyName"));
				company.setHdbRegistrationNo( rsCompany.getString("hdbRegistrationNo"));
				company.setModeOfBuisiness(rsCompany.getString("modeOfBuisiness"));
				company.setBuisinessRegistrationNo(rsCompany.getString("buisinessRegistrationNo"));
				company.setBusinessRegistrationEffectiveDate(rsCompany.getString("buisinessRegistraionEffectiveDate"));
				company.setBusinessRegistrationExpiryDate(rsCompany.getString("buisinessRegistraionExpiryDate"));
			//	Date d = new SimpleDateFormat("dd-MM-yyyy").parse(resultSet.getString("dob"));
				
				psPersonnel.setInt(1, rsCompany.getInt("id"));
				rsPersonnel = psPersonnel.executeQuery();
				
				while (rsPersonnel.next()) {
					Personnel personel = new Personnel(); 
					personel.setId(rsPersonnel.getInt("id"));
					personel.setNameOfPersonnel(rsPersonnel.getString("name"));
					personel.setAppointment( rsPersonnel.getString("appointment"));
					personel.setStatus(rsPersonnel.getString("status"));
					
					
					personnel.add(personel);
					
				}
				
				company.setPersonnel(personnel);
				
				
		
				list.add(company);
				
				
				System.out.println(list);
				
				
				
			}
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		finally {
			DBUtil.closeResultSet(rsCompany);
			DBUtil.closeResultSet(rsPersonnel);		
			DBUtil.closeStatment(psCompany);
			DBUtil.closeStatment(psPersonnel);
			pool.freeConnection(connection);
		}
		return list;
	}
	
	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement psCompany = null;
		ResultSet rsCompany = null;
		
		List<Company> list = null;
		Company company = null;
		
		try {
			
			list = new ArrayList<Company>();
			psCompany = connection.prepareStatement(SELECT_ALL_COMPANIES);
			rsCompany = psCompany.executeQuery();
			while(rsCompany.next()) {
				company = new Company();
		
				company.setId(rsCompany.getInt("id"));
				company.setCompanyName(rsCompany.getString("companyName"));
				company.setHdbRegistrationNo( rsCompany.getString("hdbRegistrationNo"));
				company.setModeOfBuisiness(rsCompany.getString("modeOfBuisiness"));
				company.setBuisinessRegistrationNo(rsCompany.getString("buisinessRegistrationNo"));
				company.setBusinessRegistrationEffectiveDate(rsCompany.getString("buisinessRegistraionEffectiveDate"));
				company.setBusinessRegistrationExpiryDate(rsCompany.getString("buisinessRegistraionExpiryDate"));
			//	Date d = new SimpleDateFormat("dd-MM-yyyy").parse(resultSet.getString("dob"));
				
				System.out.println("Company");
				System.out.println(company);
				System.out.println("LIST");
				System.out.println(list);
		
				list.add(company);
			}
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		finally {
			DBUtil.closeResultSet(rsCompany);		
			DBUtil.closeStatment(psCompany);
			pool.freeConnection(connection);
		}
		return list;
	}

	@Override
	public Company get(int id) {
        Company company = null;
  
        ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement psCompany = null;
		ResultSet rsCompany = null;
        	
		  try {
			company= new Company();
		    psCompany = connection.prepareStatement(SELECT_COMPANY_BY_ID);
		    psCompany.setInt(1, id);
            System.out.println(psCompany);     
            rsCompany  = psCompany.executeQuery();
        
        
            while (rsCompany.next()) {
              company.setId(id);
              company.setHdbRegistrationNo(rsCompany.getString("hdbRegistrationNo"));
              company.setCompanyName(rsCompany.getString("companyName"));
              company.setBuisinessRegistrationNo(rsCompany.getString("buisinessRegistrationNo"));
              company.setBusinessRegistrationEffectiveDate(rsCompany.getString("buisinessRegistraionEffectiveDate"));
              company.setBusinessRegistrationExpiryDate(rsCompany.getString("buisinessRegistraionExpiryDate"));
              company.setModeOfBuisiness(rsCompany.getString("modeOfBuisiness"));
   
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
        finally {
			DBUtil.closeResultSet(rsCompany);
			DBUtil.closeStatment(psCompany);
			pool.freeConnection(connection);
		}
		 
        return company;
    
	}

	@Override
	public boolean save(Company company) {
		boolean flag = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement psCompany = null;
		ResultSet rsCompany= null;
		try {
			psCompany = connection.prepareStatement(INSERT_COMPANY_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
			 psCompany.setString(1, company.getCompanyName());
			 psCompany.setString(2, company.getHdbRegistrationNo());
			 psCompany.setString(3, company.getModeOfBuisiness());
			 psCompany.setString(4, company.getBuisinessRegistrationNo());
			 psCompany.setString(5, company.getBusinessRegistrationEffectiveDate());
			 psCompany.setString(6, company.getBusinessRegistrationExpiryDate());
			 psCompany.executeUpdate();
			 flag = true;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			DBUtil.closeResultSet(rsCompany);		
			DBUtil.closeStatment(psCompany);
			pool.freeConnection(connection);
		}
		return flag;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement psCompany = null;
		try {
			psCompany= connection.prepareStatement(DELETE_COMPANY_SQL);
			psCompany.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {	
			DBUtil.closeStatment(psCompany);
			pool.freeConnection(connection);
		}

		return flag;
	}

	@Override
	public boolean update(Company company) {
		// TODO Auto-generated method stub
		boolean flag = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement psCompany = null;
		ResultSet rsCompany= null;
		
		try {
			
			psCompany = connection.prepareStatement(UPDATE_COMPANY_SQL);
			 
			 psCompany.setString(1, company.getCompanyName());
			 psCompany.setString(2, company.getHdbRegistrationNo());
			 psCompany.setString(3, company.getModeOfBuisiness());
			 psCompany.setString(4, company.getBuisinessRegistrationNo());
			 psCompany.setString(5, company.getBusinessRegistrationEffectiveDate());
			 psCompany.setString(6, company.getBusinessRegistrationExpiryDate());
			 
			 psCompany.setInt(7, company.getId());

        	flag = psCompany.executeUpdate() > 0;
			psCompany.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			DBUtil.closeResultSet(rsCompany);		
			DBUtil.closeStatment(psCompany);
			pool.freeConnection(connection);
		}
		return flag;
	}

}
