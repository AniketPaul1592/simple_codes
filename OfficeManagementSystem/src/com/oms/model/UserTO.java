package com.oms.model;

public class UserTO {
	private int employeeID;
	
	/** The password. */
	private String password;	
	
	/** The role. */
	private String role;	

	private boolean flag;

	/**
	 * @param empID the empID to set
	 */
	public void setEmployeeID(int EmployeeID) {
		this.employeeID = EmployeeID;
	}

	/**
	 * @return the empID
	 */
	public int getEmployeeID() {
		return employeeID;
	}


	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isFlag() {
		return flag;
	}
	
}
