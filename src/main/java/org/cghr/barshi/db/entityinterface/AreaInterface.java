package org.cghr.barshi.db.entityinterface;

public interface AreaInterface {
	public Integer getId();
	
	public String getName();

	public void setName(String name);

	public String getLandmark();

	public void setLandmark(String landmark);

	public String getPincode();

	public void setPincode(String pincode);
	
	public String getDistrictId();
	
	public void setDistrictId(String districtId);
}
