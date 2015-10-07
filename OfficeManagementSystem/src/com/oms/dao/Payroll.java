package com.oms.dao;


import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.PayrollTO;

public interface Payroll {
	
	public void checkEmpID(PayrollTO payrolldetails)  throws ApplicationException, DatabaseOperationException;
	public void checkDesignation(PayrollTO payrolldetails)  throws ApplicationException, DatabaseOperationException;
	
	public PayrollTO selectpayrollDetails(PayrollTO payrolldetails) throws ApplicationException, DatabaseOperationException;
	public PayrollTO selectemployeeDetails(PayrollTO payrolldetails) throws ApplicationException, DatabaseOperationException;
	
	public PayrollTO enrollPayroll(PayrollTO payrolldetails) throws DatabaseOperationException, ApplicationException ;
}