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
import com.oms.model.RegistrationTO;
import com.oms.util.DbUtil;


public class RegistrationDao implements Registration {
		
		/** The Constant LOG. */
		public static final Logger LOG = Logger.getLogger("RegistrationDao");
	
	   static Connection connection=null;
	   PreparedStatement preparedStatement;
	   
		
		public int addEmployeesInDaoRegular(RegistrationTO registrationTo) throws ApplicationException, DatabaseOperationException {
			
			int flag=0;
			try {
				LOG.info("Inside - method addEmployeesInDaoRegular in RegistrationDao class");
				connection=DbUtil.getConnection();
	
				preparedStatement=connection.prepareStatement(QueryConstants.INSERT_empREGULAR);
						
				preparedStatement.setString(1,registrationTo.getEmployeeName());
				preparedStatement.setString(2,registrationTo.getEmailId());
				preparedStatement.setString(3,registrationTo.getPassword());
				preparedStatement.setLong(4,registrationTo.getContact());
				preparedStatement.setDate(5,new java.sql.Date( registrationTo.getBirthDate().getTime()));					
				preparedStatement.setString(6,registrationTo.getGender());
				preparedStatement.setString(7,registrationTo.getLocation());
				preparedStatement.setInt(8,registrationTo.getExperience());
				preparedStatement.setString(9,registrationTo.getDesignation());				
				preparedStatement.setString(10,registrationTo.getHighestQualification());
				preparedStatement.setString(11,registrationTo.getEmployeeType());
				preparedStatement.setDate(12,new java.sql.Date( registrationTo.getJoinDate().getTime()));
				preparedStatement.setString(13,registrationTo.getEmployeeExist());	
					
				flag=preparedStatement.executeUpdate();
				
				LOG.info("Exit - method addEmployeesInDaoRegular in RegistrationDao class");
			} catch (ClassNotFoundException e) {
				throw new ApplicationException(e);
			} catch (IOException e) {
				throw new ApplicationException(e);
			} catch (SQLException sqlException) {
				throw new DatabaseOperationException(sqlException);
			}
			return flag;
		}
		public int addEmployeesInDaoContractor(RegistrationTO registrationTo) throws ApplicationException, DatabaseOperationException{
			int flag=0;
			
			try {
				LOG.info("Inside - method addEmployeesInDaoContractor in RegistrationDao class");
				connection=DbUtil.getConnection();
				
				preparedStatement=connection.prepareStatement(QueryConstants.INSERT_empCONTRACTOR);
				preparedStatement.setString(1,registrationTo.getEmployeeName());
				preparedStatement.setString(2,registrationTo.getEmailId());
				preparedStatement.setString(3,registrationTo.getPassword());
				preparedStatement.setLong(4,registrationTo.getContact());
				preparedStatement.setDate(5,new java.sql.Date( registrationTo.getBirthDate().getTime()));
					
				preparedStatement.setString(6,registrationTo.getGender());
				preparedStatement.setString(7,registrationTo.getLocation());
				preparedStatement.setInt(8,registrationTo.getExperience());
				preparedStatement.setString(9,registrationTo.getDesignation());
					
				preparedStatement.setString(10,registrationTo.getHighestQualification());
				preparedStatement.setString(11,registrationTo.getEmployeeType());
				preparedStatement.setDate(12,new java.sql.Date( registrationTo.getJoinDate().getTime()));
				preparedStatement.setString(13,registrationTo.getEmployeeExist());	
					
				flag=preparedStatement.executeUpdate();
			} catch (ClassNotFoundException e) {
				throw new ApplicationException(e);
			} catch (IOException e) {
				throw new ApplicationException(e);
			}  catch (SQLException sqlException) {
				throw new DatabaseOperationException(sqlException);
			}  
			LOG.info("Exit - method addEmployeesInDaoContractor in RegistrationDao class");
			return flag;
			
			
		}
		public RegistrationTO checkEmailId(RegistrationTO registrationTo) throws ApplicationException, DatabaseOperationException
		{
			try {
				LOG.info("Inside - method checkEmailId in RegistrationDao class");
				connection=DbUtil.getConnection();
				
				preparedStatement=connection.prepareStatement(QueryConstants.SELECT_email_id);
				ResultSet rs=preparedStatement.executeQuery();
				while(rs.next())
				{
					if(registrationTo.getEmailId().equals(rs.getString("EMAIL_ID")))
					{
						registrationTo.setMessage("Email Id already exist");
					}
				}	
				LOG.info("Exit - method checkEmailId in RegistrationDao class");
			}catch (ClassNotFoundException e) {
				throw new ApplicationException(e);
			} catch (IOException e) {
				throw new ApplicationException(e);
			}  catch (SQLException sqlException) {
				throw new DatabaseOperationException(sqlException);
			}
			return registrationTo;
		}
		public void getEmployeeId(RegistrationTO registrationTo) throws ApplicationException, DatabaseOperationException
		{
			try {
				LOG.info("Inside - method getEmployeeId in RegistrationDao class");
				connection=DbUtil.getConnection();
				preparedStatement=connection.prepareStatement(QueryConstants.SELECT_empid);
				preparedStatement.setString(1,registrationTo.getEmailId());
				ResultSet rs=preparedStatement.executeQuery();			
				while(rs.next())
				{
					registrationTo.setEmpId(rs.getLong(1));
					break;		
				}
			} catch (ClassNotFoundException e) {
				throw new ApplicationException(e);
			} catch (IOException e) {
				throw new ApplicationException(e);
			}  catch (SQLException sqlException) {
				throw new DatabaseOperationException(sqlException);
			}
			LOG.info("Exit - method getEmployeeId in RegistrationDao class");
		}
}
