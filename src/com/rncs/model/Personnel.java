package com.rncs.model;

public class Personnel {
	
	private int id;
	private String nameOfPersonnel;
	private String appointment;
	private String dateOfAppointment;
	private String dateOfResignation;
	private String status;
	private String mobileNo;
	private int companyId;
	
	
	
	public Personnel() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameOfPersonnel() {
		return nameOfPersonnel;
	}
	public void setNameOfPersonnel(String nameOfPersonnel) {
		this.nameOfPersonnel = nameOfPersonnel;
	}
	public String getAppointment() {
		return appointment;
	}
	public void setAppointment(String appointment) {
		this.appointment = appointment;
	}
	public String getDateOfAppointment() {
		return dateOfAppointment;
	}
	public void setDateOfAppointment(String dateOfAppointment) {
		this.dateOfAppointment = dateOfAppointment;
	}
	public String getDateOfResignation() {
		return dateOfResignation;
	}
	public void setDateOfResignation(String dateOfResignation) {
		this.dateOfResignation = dateOfResignation;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	@Override
	public String toString() {
		return "Personnel [id=" + id + ", nameOfPersonnel=" + nameOfPersonnel + ", appointment=" + appointment
				+ ", dateOfAppointment=" + dateOfAppointment + ", dateOfResignation=" + dateOfResignation + ", status="
				+ status + ", mobileNo=" + mobileNo + ", companyId=" + companyId + "]";
	}
	
	
	
	
	

}
