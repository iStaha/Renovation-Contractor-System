package com.rncs.dao;

import java.util.List;

import com.rncs.model.Personnel;



public interface PersonnelDAO {

	List<Personnel> get();
	
	Personnel get(int id);
	
	boolean save(Personnel personnel);
	
	boolean delete(int id);
	
	boolean update(Personnel personnel);
	
	
}
