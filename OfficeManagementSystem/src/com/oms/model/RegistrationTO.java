package com.oms.model;

import java.util.Date;

public class RegistrationTO {
	private String employeeName;
	private String emailId;
	private long contact;
	private Date birthDate;
	private String password;
	private String location;
	private String gender;
	private int experience;
	private String highestQualification;
	private String employeeType;
	private Date joinDate;
	private Date retirementDate;
	private String designation;
	private String message;
	private long empId;
	private long ManagerId;
	private String employeeExist;
	private String role;
	
	
	public String getEmployeeExist() {
		return employeeExist;
	}
	public void setEmployeeExist(String employeeExist) {
		this.employeeExist = employeeExist;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(java.util.Date birthDate2) {
		this.birthDate = (Date) birthDate2;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getHighestQualification() {
		return highestQualification;
	}
	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate2) {
		this.joinDate =  joinDate2;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getRetirementDate() {
		return retirementDate;
	}
	public void setRetirementDate(Date retirementDate) {
		this.retirementDate = retirementDate;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	/**
	 * @return the empId
	 */
	public long getEmpId() {
		return empId;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param managerId the managerId to set
	 */
	public void setManagerId(long managerId) {
		ManagerId = managerId;
	}
	/**
	 * @return the managerId
	 */
	public long getManagerId() {
		return ManagerId;
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
	
}
