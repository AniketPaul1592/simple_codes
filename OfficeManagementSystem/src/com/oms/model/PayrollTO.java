package com.oms.model;

public class PayrollTO {
	private String payrollID;
	private long employeeID;
	private String designation;
	private String bankName;
	private long accountNo;
	private String policyNo;
	private String policyName;
	private int basicSalary;
	private String employeeType;
	private String employeeGender;
	private int netSalary;
	private int grossSalary;
	private int deduction;
	private int tax;
	private boolean flag;
	private String message=null;
	
	public void setPayrollID(String payrollID) {
		this.payrollID = payrollID;
	}
	public String getPayrollID() {
		return payrollID;
	}
	public long getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(long employeeID) {
		this.employeeID = employeeID;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public void setBasicSalary(int basicSalary) {
		this.basicSalary = basicSalary;
	}
	public int getBasicSalary() {
		return basicSalary;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeGender(String employeeGender) {
		this.employeeGender = employeeGender;
	}
	public String getEmployeeGender() {
		return employeeGender;
	}
	public void setNetSalary(int netSalary) {
		this.netSalary = netSalary;
	}
	public int getNetSalary() {
		return netSalary;
	}
	public void setGrossSalary(int grossSalary) {
		this.grossSalary = grossSalary;
	}
	public int getGrossSalary() {
		return grossSalary;
	}
	public void setDeduction(int deduction) {
		this.deduction = deduction;
	}
	public int getDeduction() {
		return deduction;
	}
	public void setTax(int tax) {
		this.tax = tax;
	}
	public int getTax() {
		return tax;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public boolean isFlag() {
		return flag;
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
	
}
