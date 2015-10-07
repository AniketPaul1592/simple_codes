/**
 * 
 */
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
import com.oms.model.UserTO;
import com.oms.util.DbUtil;

/**
 * @author 438879
 *
 */
public class UserDao implements User {

	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger("UserDao");
	
	private Connection connection;
	@Override
	public UserTO getUserDetails(UserTO userTO) throws ApplicationException, DatabaseOperationException {
		// TODO Auto-generated method stub
		try {
			LOG.info("Inside - method getUserDetails in UserDao class");
			
			connection=DbUtil.getConnection();
			PreparedStatement preparedstatement=connection.prepareStatement(QueryConstants.SELECT_userdetails);
			ResultSet userdetails=preparedstatement.executeQuery();
			while(userdetails.next())
			{
				if(userdetails.getInt("emp_id")==userTO.getEmployeeID()&&userdetails.getString("password").equals(userTO.getPassword()))
				{
					userTO.setRole(userdetails.getString("role"));
					userTO.setFlag(true);
					break;
				}
				else
				{
					userTO.setFlag(false);
				}
			}
			LOG.info("Exit - method getUserDetails in UserDao class");
		} catch (ClassNotFoundException e) {
			throw new ApplicationException(e);
		} catch (IOException e) {
			throw new ApplicationException(e);
		} catch (SQLException e) {
			throw new DatabaseOperationException("SQL Exception happened", e);
		}
		return userTO;
		
	}
	
	public RegistrationTO getEmployeeDetails(UserTO user)
	{
		RegistrationTO employeedetails=new RegistrationTO();
		try {
			connection=DbUtil.getConnection();
			PreparedStatement preparedstatement=connection.prepareStatement(QueryConstants.SELECT_employeedetails);
			preparedstatement.setLong(1, user.getEmployeeID());
			ResultSet rs=preparedstatement.executeQuery();
			
			while(rs.next())
			{
				employeedetails.setEmpId(rs.getLong(1));
				employeedetails.setEmployeeName(rs.getString(2));
				employeedetails.setEmailId(rs.getString(3));
				employeedetails.setPassword(rs.getString(4));
				employeedetails.setContact(rs.getLong(5));
				employeedetails.setBirthDate(rs.getDate(6));
				employeedetails.setGender(rs.getString(7));
				employeedetails.setLocation(rs.getString(8));
				employeedetails.setExperience(rs.getInt(9));
				employeedetails.setDesignation(rs.getString(10));
				employeedetails.setHighestQualification(rs.getString(11));
				employeedetails.setEmployeeType(rs.getString(12));
				employeedetails.setJoinDate(rs.getDate(13));
				employeedetails.setEmployeeExist(rs.getString(14));
				employeedetails.setManagerId(rs.getLong(15));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeedetails;
	}
	
	

}
