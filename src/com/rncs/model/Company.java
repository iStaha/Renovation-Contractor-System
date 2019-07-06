package com.rncs.model;

import java.util.List;

public class Company {
	 
	private int id;
	private String companyName;
	private String hdbRegistrationNo;
	private String modeOfBuisiness;
	private String buisinessRegistrationNo;
	private String businessRegistrationEffectiveDate;
	private String businessRegistrationExpiryDate;
	
	private List<Address> addresses; 
	
	private List<Personnel> personnel; 
	
	
	
	public Company() {
		super();
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getCompanyName() {
		return companyName;
	}



	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



	public String getHdbRegistrationNo() {
		return hdbRegistrationNo;
	}



	public void setHdbRegistrationNo(String hdbRegistrationNo) {
		this.hdbRegistrationNo = hdbRegistrationNo;
	}



	public String getModeOfBuisiness() {
		return modeOfBuisiness;
	}



	public void setModeOfBuisiness(String modeOfBuisiness) {
		this.modeOfBuisiness = modeOfBuisiness;
	}



	public String getBuisinessRegistrationNo() {
		return buisinessRegistrationNo;
	}



	public void setBuisinessRegistrationNo(String buisinessRegistrationNo) {
		this.buisinessRegistrationNo = buisinessRegistrationNo;
	}



	public String getBusinessRegistrationEffectiveDate() {
		return businessRegistrationEffectiveDate;
	}



	public void setBusinessRegistrationEffectiveDate(String businessRegistrationEffectiveDate) {
		this.businessRegistrationEffectiveDate = businessRegistrationEffectiveDate;
	}



	public String getBusinessRegistrationExpiryDate() {
		return businessRegistrationExpiryDate;
	}



	public void setBusinessRegistrationExpiryDate(String businessRegistrationExpiryDate) {
		this.businessRegistrationExpiryDate = businessRegistrationExpiryDate;
	}



	public List<Address> getAddresses() {
		return addresses;
	}



	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}



	public List<Personnel> getPersonnel() {
		return personnel;
	}



	public void setPersonnel(List<Personnel> personnel) {
		this.personnel = personnel;
	}



	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName + ", hdbRegistrationNo=" + hdbRegistrationNo
				+ ", modeOfBuisiness=" + modeOfBuisiness + ", buisinessRegistrationNo=" + buisinessRegistrationNo
				+ ", businessRegistrationEffectiveDate=" + businessRegistrationEffectiveDate
				+ ", businessRegistrationExpiryDate=" + businessRegistrationExpiryDate + ", addresses=" + addresses
				+ ", personnel=" + personnel + "]";
	}

    
	

	
	
	
	
}
