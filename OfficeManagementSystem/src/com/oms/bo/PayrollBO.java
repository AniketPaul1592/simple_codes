


package com.oms.bo;

import org.apache.log4j.Logger;

import com.oms.constants.PayrollConstants;
import com.oms.dao.PayrollDao;
import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.PayrollTO;


public class PayrollBO {
	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger("PayrollBO");
	
	public PayrollTO checkEmpId(PayrollTO payrolldetails)throws DatabaseOperationException, ApplicationException
	{
		LOG.info("Inside - method checkEmpId in PayrollBO class");
		PayrollDao payrolldao=new PayrollDao();
		payrolldao.checkEmpID(payrolldetails);
		LOG.info("Exit - method checkEmpId in PayrollBO class");
		return payrolldetails;
	}
	
	public PayrollTO checkDesignation(PayrollTO payrolldetails)throws DatabaseOperationException, ApplicationException
	{
		LOG.info("Inside - method checkDesignation in PayrollBO class");
		PayrollDao payrolldao=new PayrollDao();
		payrolldao.checkDesignation(payrolldetails);
		LOG.info("Exit - method checkDesignation in PayrollBO class");
		return payrolldetails;
	}
	
	public PayrollTO selectDetails(PayrollTO payrolldetails) throws DatabaseOperationException, ApplicationException
	{
		LOG.info("Inside - method selectDetailsin PayrollBO class");
		PayrollDao payrolldao =new PayrollDao();
		payrolldetails=payrolldao.selectpayrollDetails(payrolldetails);
		payrolldetails=payrolldao.selectemployeeDetails(payrolldetails);
		LOG.info("Exit - method selectDetailsin PayrollBO class");
		return payrolldetails;
	}
	
	public PayrollTO enrollpayroll(PayrollTO payrolldetails) throws DatabaseOperationException, ApplicationException
	{
		LOG.info("Inside - method enrollpayroll in PayrollBO class");
		PayrollDao payrolldao =new PayrollDao();
		payrolldetails=payrolldao.enrollPayroll(payrolldetails);
		LOG.info("Exit - method enrollpayroll in PayrollBO class");
		return payrolldetails;
	}
	
	public PayrollTO calculateNetSalary(PayrollTO payrolldetails)
	{
		LOG.info("Inside - method calculateNetSalary in PayrollBO class");
		int salary=0;
		if(payrolldetails.getEmployeeType().equalsIgnoreCase("regular"))
		{
			payrolldetails.setGrossSalary(payrolldetails.getBasicSalary()+PayrollConstants.REGULAR_HRA
														+PayrollConstants.REGULAR_CONVEYANCE);
			payrolldetails.setDeduction(PayrollConstants.REGULAR_MEDICAL+PayrollConstants.REGULAR_EPF
													+PayrollConstants.REGULAR_ESI
													+PayrollConstants.CONTRACTOR_PROFESSIONALTAX);
		}
		else if(payrolldetails.getEmployeeType().equalsIgnoreCase("contractor"))
		{
			payrolldetails.setGrossSalary(payrolldetails.getBasicSalary()+PayrollConstants.CONTRACTOR_HRA
														+PayrollConstants.CONTRACTOR_VARIABLEALLOWANCE
														+PayrollConstants.CONTRACTOR_DA
														+PayrollConstants.CONTRACTOR_TA);
			payrolldetails.setDeduction(PayrollConstants.CONTRACTOR_PROFESSIONALTAX);
		}
		salary=payrolldetails.getGrossSalary()-payrolldetails.getDeduction();
		payrolldetails.setTax(calculateTax(payrolldetails,salary));
		payrolldetails.setNetSalary(salary-(payrolldetails.getTax()/100)*salary);
		LOG.info("Exit - method calculateNetSalary in PayrollBO class");
		return payrolldetails;
	}
	
	public int calculateTax(PayrollTO payrolldetails,int salary)
	{
		LOG.info("Inside - method calculateTax in PayrollBO class");
		int annualSalary=salary*12;
		int tax=0;
		if(payrolldetails.getEmployeeGender().equalsIgnoreCase("m"))
		{
			if(annualSalary<=180000)
			{
				tax=0;
			}
			else if(annualSalary>180000&&annualSalary<500000)
			{
				tax=10;
			}
			else if(annualSalary>500000&&annualSalary<800000)
			{
				tax=20;
			}
			else
			{
				tax=30;
			}
		}
		else if(payrolldetails.getEmployeeGender().equalsIgnoreCase("f"))
		{
			if(annualSalary<=190000)
			{
				tax=0;
			}
			else if(annualSalary>190000&&annualSalary<500000)
			{
				tax=10;
			}
			else if(annualSalary>500000&&annualSalary<800000)
			{
				tax=20;
			}
			else
			{
				tax=30;
			}
		}
		LOG.info("Exit - method calculateTax in PayrollBO class");
		return tax;
	}
	
}
