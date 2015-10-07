

package com.oms.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.oms.constants.QueryConstants;
import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.PayrollTO;
import com.oms.util.DbUtil;

public class PayrollDao implements Payroll {
	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger("PayrollDao");
	
	
	public void checkEmpID(PayrollTO payrolldetails)  throws ApplicationException, DatabaseOperationException
	{
		LOG.info("Inside - method checkEmpID in PayrollDao class");
		Connection connection=null;
		try {
			connection=DbUtil.getConnection();
			PreparedStatement preparedstatement=connection.prepareStatement(QueryConstants.SELECT_employee);
			ResultSet rs=preparedstatement.executeQuery();
			while(rs.next())
			{
				if(rs.getLong("emp_id")==payrolldetails.getEmployeeID())
				{
					payrolldetails.setMessage(null);
					break;
				}
				else
				{
					payrolldetails.setMessage("Invalid EmployeeID");
				}
			}
			LOG.info("Exit - method checkEmpID in PayrollDao class");
		} catch (ClassNotFoundException e) {
			throw new ApplicationException(e);
		} catch (IOException e) {
			throw new ApplicationException(e);
		} catch (SQLException e) {
			throw new DatabaseOperationException(e);
		}
	}
	
	public void checkDesignation(PayrollTO payrolldetails)  throws ApplicationException, DatabaseOperationException
	{
		LOG.info("Inside - method checkDesignation in PayrollDao class");
		Connection connection=null;
		try {
			connection=DbUtil.getConnection();
			PreparedStatement preparedstatement=connection.prepareStatement(QueryConstants.SELECT_designation);
			ResultSet rs=preparedstatement.executeQuery();
			while(rs.next())
			{
				if(rs.getString("designation").equalsIgnoreCase(payrolldetails.getDesignation())&&(rs.getLong("emp_id")==payrolldetails.getEmployeeID()))
				{
					payrolldetails.setMessage(null);
					break;
				}
				else
				{
					payrolldetails.setMessage("Invalid Designation");
				}
			}
			LOG.info("Exit - method checkDesignation in PayrollDao class");
		} catch (ClassNotFoundException e) {
			throw new ApplicationException(e);
		} catch (IOException e) {
			throw new ApplicationException(e);
		} catch (SQLException e) {
			throw new DatabaseOperationException(e);
		}
	}
	
	public PayrollTO selectpayrollDetails(PayrollTO payrolldetails) throws ApplicationException, DatabaseOperationException
	{
		LOG.info("Inside - method selectpayrollDetails in PayrollDao class");
		Connection connection = null;
		try {
			connection = DbUtil.getConnection();
			PreparedStatement preparedstatement=connection.prepareStatement(QueryConstants.SELECT_payroll);
			ResultSet payrollmaster=preparedstatement.executeQuery();
			while(payrollmaster.next())
			{
				if(payrollmaster.getString(2).equalsIgnoreCase(payrolldetails.getDesignation()))
				{
					payrolldetails.setPayrollID(payrollmaster.getString("payroll_id"));
					payrolldetails.setBasicSalary(Integer.parseInt(payrollmaster.getString("basic")));
					break;
				}
			}
			preparedstatement.close();
			LOG.info("Exit - method selectpayrollDetails in PayrollDao class");
		}catch (ClassNotFoundException e) {
			throw new ApplicationException(e);
		} catch (IOException e) {
			throw new ApplicationException(e);
		} catch (SQLException sqlException) {
			throw new DatabaseOperationException(sqlException);
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException sqlException) {
				new DatabaseOperationException(sqlException);
			}
		}
		return payrolldetails;
	}
	
	
	public PayrollTO selectemployeeDetails(PayrollTO payrolldetails) throws ApplicationException, DatabaseOperationException
	{
		LOG.info("Inside - method selectemployeeDetails in PayrollDao class");
		Connection connection = null;
		try {
			connection = DbUtil.getConnection();
			PreparedStatement preparedstatement=connection.prepareStatement(QueryConstants.SELECT_empdetails);
			preparedstatement.setLong(1, payrolldetails.getEmployeeID());
			ResultSet employeedetails=preparedstatement.executeQuery();
			while(employeedetails.next())
			{
				payrolldetails.setEmployeeType(employeedetails.getString("TYPE_EMPLOYEMENT"));
				payrolldetails.setEmployeeGender(employeedetails.getString("GENDER"));
			}
			preparedstatement.close();
			LOG.info("Exit - method selectemployeeDetails in PayrollDao class");
		}catch (ClassNotFoundException e) {
			throw new ApplicationException(e);
		} catch (IOException e) {
			throw new ApplicationException(e);
		} catch (SQLException sqlException) {
			throw new DatabaseOperationException(sqlException);
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException sqlException) {
				new DatabaseOperationException(sqlException);
			}
		}
		return payrolldetails;
	}
		
	public PayrollTO enrollPayroll(PayrollTO payrolldetails) throws DatabaseOperationException, ApplicationException
	{
		LOG.info("Inside - method enrollPayroll in PayrollDao class");
		Connection connection = null;
		try {
			connection = DbUtil.getConnection();
			PreparedStatement preparedstatement=connection.prepareStatement(QueryConstants.INSERT_payroll);
			preparedstatement.setString(1, payrolldetails.getPayrollID());
			preparedstatement.setLong(2, payrolldetails.getEmployeeID());
			preparedstatement.setString(3, payrolldetails.getBankName());
			preparedstatement.setLong(4, payrolldetails.getAccountNo());
			preparedstatement.setString(5, payrolldetails.getPolicyNo());
			preparedstatement.setString(6, payrolldetails.getPolicyName());
		
			int result=preparedstatement.executeUpdate();
			if(result==1)
			{
				payrolldetails.setFlag(true);
			}
			else
			{
				payrolldetails.setFlag(false);
			}
			
			preparedstatement.close();
			LOG.info("Exit - method enrollPayroll in PayrollDao class");
		} catch (ClassNotFoundException e) {
			throw new ApplicationException(e);
		} catch (IOException e) {
			throw new ApplicationException(e);
		} catch (SQLException sqlException) {
			throw new DatabaseOperationException(sqlException);
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException sqlException) {
				new DatabaseOperationException(sqlException);
			}
		}
		return payrolldetails;
	}
}
