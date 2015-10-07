package com.oms.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;


import com.oms.constants.QueryConstants;
import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.DisplayWorkAllocationTO;
import com.oms.model.WorkAllocationTO;
import com.oms.util.DbUtil;

public class WorkAllocationDao implements WorkAllocation {
	public static final Logger LOG = Logger.getLogger("WorkAllocationDao");
	private Connection connection;
	// Variable for storing the response value
	ResultSet result = null;
	PreparedStatement statement = null;
	int update_WA=0;
	int update_ER=0;
	int flag=0;
	SimpleDateFormat sd=new SimpleDateFormat("dd-MMM-yyyy");
	
	public void insertWorkAllocationDetails(WorkAllocationTO objectTO) throws ApplicationException, DatabaseOperationException{
		
		LOG.info("Inside - method insertWorkAllocationDetails in WorkAllocationDao class");

		try {
			
			connection=DbUtil.getConnection();
			statement=connection.prepareStatement(QueryConstants.INSERT_WorkAllocation);
			statement.setInt(1, objectTO.getEmployeeId());
			statement.setString(2,objectTO.getProjectId());
			statement.setString(3, objectTO.getProjectType());
			statement.setDate(4, new java.sql.Date(objectTO.getAssignDate().getTime()));
			statement.setString(5, objectTO.getShift());
			update_WA=statement.executeUpdate();
			
		}  catch (ClassNotFoundException e) {
			throw new ApplicationException(e);
		} catch (IOException e) {
			throw new ApplicationException(e);
		} catch (SQLException sqlException) {
			
			//throw new DatabaseOperationException(sqlException);
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException sqlException) {
				
				//new DatabaseOperationException(sqlException);
			}
		}
		LOG.info("Exit - method insertWorkAllocationDetails in WorkAllocationDao class");
	}
	public void updateEmployeeRegistration(WorkAllocationTO objectTO) throws DatabaseOperationException, ApplicationException{
		LOG.info("Inside - updateEmployeeRegistration in WorkAllocationDao class");
		try {
			
			connection=DbUtil.getConnection();
			statement=connection.prepareStatement(QueryConstants.UPDATE_Employee_Registration);
			statement.setInt(1, objectTO.getManagerId());
			statement.setInt(2, objectTO.getEmployeeId());
			update_ER=statement.executeUpdate();
			
		}  catch (ClassNotFoundException e) {
			throw new ApplicationException(e);
		} catch (IOException e) {
			throw new ApplicationException(e);
		} catch (SQLException sqlException) {
			throw new DatabaseOperationException(sqlException);
		} 
		LOG.info("Exit - updateEmployeeRegistration in WorkAllocationDao class");
	}
	
	public DisplayWorkAllocationTO getUserDetails(WorkAllocationTO objectTO) throws DatabaseOperationException {
		LOG.info("Inside - getUserDetails in WorkAllocationDao class");
		DisplayWorkAllocationTO objectDTO=new DisplayWorkAllocationTO();
		
		try {
		    
		    if(objectTO.getManagerId()!=0)
			{   
		    	statement=connection.prepareStatement(QueryConstants.SELECT_Details);
		    	statement.setInt(1, objectTO.getManagerId());
		    	result=statement.executeQuery();
				while(result.next())
				{
					objectDTO.setEmployeeId(result.getInt(1));
					objectDTO.setEmployeeName(result.getString(2));
					objectDTO.setManagerId(result.getInt(3));
					objectDTO.setManagerName(result.getString(4));
					objectDTO.setProjectId(result.getString(5));
					objectDTO.setProjectType(result.getString(6));
					objectDTO.setAssignDate(new java.util.Date(result.getDate(7).getTime()));
					objectDTO.setShift(result.getString(8));
				}
			}
		    else
		    {
		    	statement=connection.prepareStatement(QueryConstants.SELECT_DetailsNoManager);
		    	statement.setInt(1, objectTO.getEmployeeId());
		    	result=statement.executeQuery();
				while(result.next())
				{
					objectDTO.setEmployeeId(result.getInt(1));
					objectDTO.setEmployeeName(result.getString(2));
					objectDTO.setManagerId(0);
					objectDTO.setManagerName("N/A");
					objectDTO.setProjectId(result.getString(3));
					objectDTO.setProjectType(result.getString(4));
					objectDTO.setAssignDate(new java.util.Date(result.getDate(5).getTime()));
					objectDTO.setShift(result.getString(6));
				}
		    }
			statement=connection.prepareStatement(QueryConstants.SELECT_Duration);
			statement.setInt(1, objectTO.getEmployeeId());
			result=statement.executeQuery();
			while(result.next())
			{
				objectDTO.setDuration(result.getInt(1));
			}
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(objectTO.getAssignDate());
			calendar.add(Calendar.DAY_OF_YEAR, (objectDTO.getDuration()/8));
			objectDTO.setExpCompletionDate(calendar.getTime());
		}  catch (SQLException sqlException) {
			throw new DatabaseOperationException(sqlException);
		}
		LOG.info("Exit - getUserDetails in WorkAllocationDao class");
		return objectDTO;
	}
	
	
	
	public WorkAllocationTO validationData(WorkAllocationTO objectTO) throws ApplicationException, DatabaseOperationException
	{
		LOG.info("Inside - validationData in WorkAllocationDao class");
		try {
			connection=DbUtil.getConnection();
			statement=connection.prepareStatement(QueryConstants.SELECT_Gender);
			statement.setInt(1, objectTO.getEmployeeId());
			result=statement.executeQuery();
			while(result.next())
			{
				if(result.getString("GENDER").equalsIgnoreCase("f")&&objectTO.getEmployeeId()%2==0)
				{
					objectTO.setShift("Morning");
				}
				else if(result.getString("GENDER").equalsIgnoreCase("f")&&objectTO.getEmployeeId()%2!=0)
				{
					objectTO.setShift("Evening");
				}
				if(result.getString("GENDER").equalsIgnoreCase("m")&&objectTO.getEmployeeId()%2==0)
				{
					objectTO.setShift("Evening");
				}
				else if(result.getString("GENDER").equalsIgnoreCase("m")&&objectTO.getEmployeeId()%2!=0)
				{
					objectTO.setShift("Night");
				}
				
			}
			
		}  catch (ClassNotFoundException e) {
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
		LOG.info("Exit - validationData in WorkAllocationDao class");
		return objectTO;

  }

	public WorkAllocationTO validateType(WorkAllocationTO objectTO) throws ApplicationException, DatabaseOperationException {
		// TODO Auto-generated method stub
		LOG.info("Inside - validateType in WorkAllocationDao class");
		try {
			connection=DbUtil.getConnection();
			statement=connection.prepareStatement(QueryConstants.SELECT_TypeEmploy);
			statement.setInt(1, objectTO.getEmployeeId());
			result=statement.executeQuery();
			while(result.next())
			{
				if(result.getString("type_employement").equalsIgnoreCase("Contractor"))
				{
					objectTO.setProjectType("Onshore");
				}
			}
		}
		 catch (ClassNotFoundException e) {
				throw new ApplicationException(e);
			} catch (IOException e) {
				throw new ApplicationException(e);
			} catch (SQLException sqlException) {
				throw new DatabaseOperationException(sqlException);
			}
			LOG.info("Exit - validateType in WorkAllocationDao class");
		return objectTO;

	}
	@Override
	public int validateEmployee(WorkAllocationTO objectTO)
			throws ApplicationException, DatabaseOperationException {
		// TODO Auto-generated method stub
		LOG.info("Inside - validateEmployee in WorkAllocationDao class");
		int res=0;
		try {
			connection=DbUtil.getConnection();
			statement=connection.prepareStatement(QueryConstants.SELECT_ValidateEmployee);
			
			statement.setInt(1, objectTO.getEmployeeId());
			result=statement.executeQuery();
			int count=0;
			while(result.next())
			{
				count++;
			}
			if(count>0){
				statement=connection.prepareStatement(QueryConstants.SELECT_ValidateEmployee);
				
				statement.setInt(1, objectTO.getManagerId());
				result=statement.executeQuery();
				while(result.next())
				{
					res++;
				}
				if(res<=0){
					return 2;
				}
			}
			else{
				return 1;
			}
			
		}
		 catch (ClassNotFoundException e) {
				throw new ApplicationException(e);
			} catch (IOException e) {
				throw new ApplicationException(e);
			} catch (SQLException sqlException) {
				throw new DatabaseOperationException(sqlException);
			}
			LOG.info("Exit - validateEmployee in WorkAllocationDao class");
		
		
		return 3;
	}

}
