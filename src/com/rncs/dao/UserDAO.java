package com.rncs.dao;

import java.util.List;

import com.rncs.model.User;

 
public interface UserDAO {
	
	List<User> get();
	
	User get(int id);
	
	boolean save(User user);
	
	boolean delete(int id);
	
	boolean update(User user);
	
	boolean authenticate(User user);
}