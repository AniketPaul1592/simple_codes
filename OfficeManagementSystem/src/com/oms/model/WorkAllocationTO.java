package com.oms.model;

import java.util.Date;

public class WorkAllocationTO {
	private int employeeId=0;
	private int managerId=0;
	private String projectId=null;
	private String projectType=null;
	private Date assignDate=new Date();	
	private String shift=null;
		private String managerMessage=new String();
		private String message=new String();
		
		public String getManagerMessage() {
			return managerMessage;
		}
		public void setManagerMessage(String managerMessage) {
			this.managerMessage = managerMessage;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public int getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(int employeeId) {
			this.employeeId = employeeId;
		}
		public int getManagerId() {
			return managerId;
		}
		public void setManagerId(int managerId) {
			this.managerId = managerId;
		}
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
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
		
}
