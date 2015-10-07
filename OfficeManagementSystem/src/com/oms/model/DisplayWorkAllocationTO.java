package com.oms.model;

import java.util.Date;

public class DisplayWorkAllocationTO {
	private int employeeId=0;
	private String employeeName=null;
	private int managerId=0;
	private String managerName=null;
	private String projectId=null;
	private String projectType=null;
	private Date assignDate=new Date();	
	private String shift=null;
	private Date expCompletionDate=new Date();
	private int duration=0;
	String managerUnAvail=new String();
	
	public String getManagerUnAvail() {
		return managerUnAvail;
	}
	public void setManagerUnAvail(String managerUnAvail) {
		this.managerUnAvail = managerUnAvail;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public Date getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public Date getExpCompletionDate() {
		return expCompletionDate;
	}
	public void setExpCompletionDate(Date expCompletionDate) {
		this.expCompletionDate = expCompletionDate;
	}
	
}
