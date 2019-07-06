package com.rncs.model;

public class Address {
	
	private int id;
	private int blockNo;
	private String unitLevel;
	private int unitNo;
	private String buildingName;
	private String streetName;
	private int companyId;
	
	
	
	public Address() {
		super();
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getBlockNo() {
		return blockNo;
	}



	public void setBlockNo(int blockNo) {
		this.blockNo = blockNo;
	}



	public String getUnitLevel() {
		return unitLevel;
	}



	public void setUnitLevel(String unitLevel) {
		this.unitLevel = unitLevel;
	}



	public int getUnitNo() {
		return unitNo;
	}



	public void setUnitNo(int unitNo) {
		this.unitNo = unitNo;
	}



	public String getBuildingName() {
		return buildingName;
	}



	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}



	public String getStreetName() {
		return streetName;
	}



	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}



	public int getCompanyId() {
		return companyId;
	}



	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}



	@Override
	public String toString() {
		return "Address [id=" + id + ", blockNo=" + blockNo + ", unitLevel=" + unitLevel + ", unitNo=" + unitNo
				+ ", buildingName=" + buildingName + ", streetName=" + streetName + ", companyId=" + companyId + "]";
	}


	

	



	
	

}
