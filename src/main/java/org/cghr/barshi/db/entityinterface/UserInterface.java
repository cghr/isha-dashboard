package org.cghr.barshi.db.entityinterface;

public interface UserInterface {
	public Integer getId();
	
	public void setUsername(String username);
	
	public String getUsername();
	
	public void setClearPassword(String clearPassword);
	
	public void setName(String name);
	
	public void setPassword(String setPassword);
	
	public String getPassword();
	
	public String getName();
	
	public void setRole(String role);
	
	public String getRole();
}
