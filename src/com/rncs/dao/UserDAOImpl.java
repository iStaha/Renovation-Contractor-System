package com.rncs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rncs.util.DBConnectionUtil;
import com.rncs.util.DBUtil;
import com.rncs.util.Password;
import com.rncs.util.ConnectionPool;
import com.rncs.model.*;
import com.rncs.util.Password;

 
public class UserDAOImpl implements UserDAO {
	
	private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (username, name, email, password) VALUES "
			+ " (?, ?, ?, ?);";
	private static final String AUTHNTICATE_USER = "select * from users where username = ? AND password = ?";
	private static final String INSERT_PRODUCTS_SQL = "INSERT INTO products" + "  (item, user_id) VALUES " + " (?, ?);";

	private static final String SELECT_USER_BY_ID = "select id,username,name,email from users where id =?";
	private static final String SELECT_PRODUCT_BY_USER_ID = "select id,item from products where user_id =?";
	private static final String SELECT_ALL_PRODUCTS = "select products.item, products.id from products where user_id= ?;";
	private static final String SELECT_ALL_USERS = "SELECT * FROM users";/*"SELECT * FROM users, products where users.id=products.user_id  group by users.id";*/
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set username = ?,name = ?,email= ?, password =? where id = ?;";
	private static final String UPDATE_PRODUCTS_SQL = "update products set  user_id = ? where id = ?;";
	
	
	
	@Override
	public List<User> get()  {
		
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement psUser = null;
		ResultSet rsUser = null;
		
		List<User> list = null;
		User user = null;
		
		try {
			
			list = new ArrayList<User>();
			psUser = connection.prepareStatement(SELECT_ALL_USERS);
			rsUser = psUser.executeQuery();
			while(rsUser.next()) {
				user = new User();
				//user.setId(rsUser.getInt("id"));
				user.setUserName( rsUser.getString("username"));
				user.setName( rsUser.getString("name"));
				user.setEmail(rsUser.getString("email"));
				user.setPassword( rsUser.getString("password"));
			//	Date d = new SimpleDateFormat("dd-MM-yyyy").parse(resultSet.getString("dob"));
		
				list.add(user);
			}
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		
		finally {
			DBUtil.closeResultSet(rsUser);		
			DBUtil.closeStatment(psUser);
			pool.freeConnection(connection);
		}
		return list;
	}
 
	@Override
	public User get(int id) {
		User user = null;
		  ConnectionPool pool = ConnectionPool.getInstance();
		  Connection connection = pool.getConnection();
		  PreparedStatement psUser = null;
		  ResultSet rsUsers = null;
		try {
		      user = new User();
		      psUser = connection.prepareStatement(SELECT_USER_BY_ID);
	          psUser.setInt(1, id);		
	          
	          rsUsers   = psUser.executeQuery();
	          
			  if(rsUsers.next()) {
				user.setId(rsUsers.getInt("id"));
				user.setUserName(rsUsers.getString("username"));
				user.setName(rsUsers.getString("name"));
				user.setEmail(rsUsers.getString("email"));
				user.setPassword(rsUsers.getString("password"));
			//	Date d = new SimpleDateFormat("dd-MM-yyyy").parse(resultSet.getString("dob"));
			
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			DBUtil.closeResultSet(rsUsers);		
			DBUtil.closeStatment(psUser);
			pool.freeConnection(connection);
		}
		return user;
	}
 
	@Override
	public boolean save(User u) {
		boolean flag = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement psUser = null;
		ResultSet rsUser= null;
		try {
			 psUser = connection.prepareStatement(INSERT_USERS_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
			 
			    psUser.setString(1, u.getUserName());
				psUser.setString(2, u.getName());
				psUser.setString(3, u.getEmail());
				psUser.setString(4, u.getPassword());
			    psUser.executeUpdate();
			 flag = true;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			DBUtil.closeResultSet(rsUser);		
			DBUtil.closeStatment(psUser);
			pool.freeConnection(connection);
		}
		return flag;
	}
 
	@Override
	public boolean delete(int id) {
		boolean flag = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement psUser = null;
		try {
			psUser = connection.prepareStatement(DELETE_USERS_SQL);
			psUser.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.closeStatment(psUser);
			pool.freeConnection(connection);
		}
		return flag;
	}
 
	@Override
	public boolean update(User user) {
		boolean flag = false;
		  ConnectionPool pool = ConnectionPool.getInstance();
		  Connection connection = pool.getConnection();
		  PreparedStatement psUser = null;
		try {
			psUser = connection.prepareStatement( UPDATE_USERS_SQL);
			psUser.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public boolean authenticate(User user) {
		 boolean flag = false;
		  ConnectionPool pool = ConnectionPool.getInstance();
		  Connection connection = pool.getConnection();
		  PreparedStatement psUser = null;
		  ResultSet rsUser= null;
		try {
			psUser = connection.prepareStatement(AUTHNTICATE_USER);
		//	System.out.println(x);
		    psUser.setString(1, user.getUserName());
		    psUser.setString(2, user.getPassword());
			rsUser= psUser.executeQuery();
	
			
			flag = rsUser.next();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.closeStatment(psUser);
			pool.freeConnection(connection);
		}
		return flag;
	}
 
}


/*
 *
 * // Hash a password for the first time
String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

// gensalt's log_rounds parameter determines the complexity
// the work factor is 2**log_rounds, and the default is 10
String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));

// Check that an unencrypted password matches one that has
// previously been hashed
if (BCrypt.checkpw(candidate, hashed))
	System.out.println("It matches");
else
	System.out.println("It does not match");
 *
 * */
