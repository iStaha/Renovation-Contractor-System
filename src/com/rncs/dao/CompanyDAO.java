package com.rncs.dao;

import java.util.List;

import com.rncs.model.Company;

public interface CompanyDAO {

	List<Company> get();
	
	Company get(int id);
	
	boolean save(Company company);
	
	boolean delete(int id);
	
	boolean update(Company company);

	List<Company> getAllCompanies();
	
	
}
