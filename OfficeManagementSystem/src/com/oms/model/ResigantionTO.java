package com.oms.model;

import java.util.Date;

public class ResigantionTO {
private boolean dateValidation=true;
public boolean isDateValidation() {
	return dateValidation;
}
public void setDateValidation(boolean dateValidation) {
	this.dateValidation = dateValidation;
}
private long empId;
private String password;
private String empName;
private String empType;
private String projId;
private String mgrName;
private Date dateOfApply;
private int noticePeriod;
private String comments;
private long mgrId;
private Date dateOfJoining;
private double basic;
private double amount;
public long getEmpId() {
	return empId;
}
public void setEmpId(long empId) {
	this.empId = empId;
}
public String getEmpName() {
	return empName;
}
public void setEmpName(String empName) {
	this.empName = empName;
}
public String getEmpType() {
	return empType;
}
public void setEmpType(String empType) {
	this.empType = empType;
}
public String getProjId() {
	return projId;
}
public void setProjId(String projId) {
	this.projId = projId;
}
public String getMgrName() {
	return mgrName;
}
public void setMgrName(String mgrName) {
	this.mgrName = mgrName;
}
public Date getDateOfApply() {
	return dateOfApply;
}
public void setDateOfApply(Date dateOfApply) {
	this.dateOfApply = dateOfApply;
}
public int getNoticePeriod() {
	return noticePeriod;
}
public void setNoticePeriod(int noticePeriod) {
	this.noticePeriod = noticePeriod;
}
public String getComments() {
	return comments;
}
public void setComments(String comments) {
	this.comments = comments;
}
public long getMgrId() {
	return mgrId;
}
public void setMgrId(long mgrId) {
	this.mgrId = mgrId;
}
public Date getDateOfJoining() {
	return dateOfJoining;
}
public void setDateOfJoining(Date dateOfJoining) {
	this.dateOfJoining = dateOfJoining;
}
public double getBasic() {
	return basic;
}
public void setBasic(double basic) {
	this.basic = basic;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
