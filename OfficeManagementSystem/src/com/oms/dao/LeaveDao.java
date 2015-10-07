package com.oms.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;


import com.oms.constants.QueryConstants;
import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.LeaveTO;
import com.oms.util.DbUtil;

public class LeaveDao implements Leave{

	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger("LeaveDao");
	
	/** The connection. */
	private Connection connection;
	@Override
	public boolean checkManagerStatus(LeaveTO leaveTo) throws ApplicationException, DatabaseOperationException {
		// TODO Auto-generated method stub
		LOG.info("Inside - method checkManagerStatus in LeaveDao class");
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		boolean flag=true;
		try {
			connection = DbUtil.getConnection();
			
			long managerID=leaveTo.getManagerId();
			preparedStatement = connection
			.prepareStatement(QueryConstants.CHECK_MANAGER);
			preparedStatement.setLong(1, managerID);
			rs = preparedStatement.executeQuery();
			Date d=new Date();
			long curr_time=d.getTime();
			while (rs.next()) {
				long stDate=rs.getDate(1).getTime();
				long lsDate=rs.getDate(2).getTime();
				if((curr_time>stDate)&&(curr_time<lsDate)){
					flag=false;
					return false;
				}
				
			}
			LOG.info("Exit - method checkManagerStatus in PayrollDao class");

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
		return flag;
	}

	private int calculateLeave(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		LOG.info("Inside - method calculateLeave in LeaveDao class");
		Calendar c1=Calendar.getInstance();
		Calendar c2=Calendar.getInstance();
		c1.setTime(startDate);
		c2.setTime(endDate);
		long day1=c1.getTimeInMillis();
		long day2=c2.getTimeInMillis();
		double d=((double)((double)day2-(double)day1)/(3600000*24));
		LOG.info("Exit - method calculateLeave in PayrollDao class");

		return (int)d+1;
	}
	@Override
	public void insertLeaveDetails(LeaveTO leaveTo) throws ApplicationException, DatabaseOperationException {
		// TODO Auto-generated method stub
		LOG.info("Inside - method insertLeaveDetails in LeaveDao class");

		PreparedStatement preparedStatement = null;
		try {
			connection = DbUtil.getConnection();
			
			String kt=new String();
			preparedStatement = connection
			.prepareStatement(QueryConstants.INSERT_LEAVE);
			preparedStatement.setLong(1, leaveTo.getEmpId());
			preparedStatement.setString(2, leaveTo.getLeaveType());
			preparedStatement.setDate(3, new java.sql.Date(leaveTo.getStartDate().getTime()));
			preparedStatement.setDate(4, new java.sql.Date(leaveTo.getEndDate().getTime()));
			preparedStatement.setString(5, leaveTo.getReason());
			preparedStatement.setLong(6, leaveTo.getKnowledgeTransition());
			preparedStatement.setInt(7, calculateLeave(leaveTo.getStartDate(),leaveTo.getEndDate()));
			
			preparedStatement.executeUpdate();	
			LOG.info("Exit - method insertLeaveDetails in PayrollDao class");

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
		
	}

	@Override
	public boolean checkJoinDate(LeaveTO leaveTo) throws ApplicationException, DatabaseOperationException {
		// TODO Auto-generated method stub
		LOG.info("Inside - method checkJoinDate in LeaveDao class");

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		boolean flag=true;
		try {
			connection = DbUtil.getConnection();

			preparedStatement = connection
			.prepareStatement(QueryConstants.SELECT_DATE);
			preparedStatement.setLong(1,leaveTo.getEmpId());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {				
				if(rs.getDate(1).getTime()>(leaveTo.getStartDate().getTime())){
					return false;
				}
				
			}
			LOG.info("Exit - method checkJoinDate in PayrollDao class");

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
		return true;
		
		
	}

	@Override
	public boolean checkStartDate(LeaveTO leaveTo) throws ApplicationException, DatabaseOperationException {
		// TODO Auto-generated method stub
		LOG.info("Inside - method checkStartDate in LeaveDao class");

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		boolean flag=true;
		try {
			connection = DbUtil.getConnection();
			
			//long managerID=leaveTo.getManagerId();
			preparedStatement = connection
			.prepareStatement(QueryConstants.SELECT_SEDATE);
			preparedStatement.setLong(1,leaveTo.getEmpId());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				long d1=rs.getDate(1).getTime();
				long d2=rs.getDate(2).getTime();
				long sDate=leaveTo.getStartDate().getTime();
				if(sDate>=d1&&sDate<=d2){
					return false;
				}
				
				
			}
			LOG.info("Exit - method checkStartDate in PayrollDao class");

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
		return true;
		
	}

	@Override
	public boolean checkEndtDate(LeaveTO leaveTo) throws ApplicationException, DatabaseOperationException  {
		
		// TODO Auto-generated method stub
		LOG.info("Inside - method checkEndtDate in LeaveDao class");

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		boolean flag=true;
		try {
			connection = DbUtil.getConnection();
			
			//long managerID=leaveTo.getManagerId();
			preparedStatement = connection
			.prepareStatement("select start_date,end_date  from leave_details where emp_id=? ");
			preparedStatement.setLong(1,leaveTo.getEmpId());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				long d1=rs.getDate(1).getTime();
				long d2=rs.getDate(2).getTime();
				long endDate=leaveTo.getEndDate().getTime();
				if(endDate>=d1&&endDate<=d2){
					return false;
				}
				
				
			}
			LOG.info("Exit - method checkEndtDate in PayrollDao class");

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
		return true;
		
	}

	@Override
	public int getLeave(LeaveTO leaveTo) throws ApplicationException, DatabaseOperationException{
		// TODO Auto-generated method stub
		LOG.info("Inside - method getLeave in LeaveDao class");

		int days=0;
		String employeeType=leaveTo.getEmployeeType();
		try {
			connection = DbUtil.getConnection();
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			String leaveType=leaveTo.getLeaveType();
			if(employeeType.equalsIgnoreCase("contractor")){
				leaveType=leaveType.concat("_CON");
			}
			else{
				
			}
			preparedStatement = connection
			.prepareStatement(QueryConstants.CHECK_LEAVE);
			preparedStatement.setString(1, leaveType);
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				days=rs.getInt("NO_OF_DAYS");
			}
			LOG.info("Exit - method getLeave in PayrollDao class");

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
		return days;
	}

	@Override
	public int takenLeave(LeaveTO leaveTo) throws ApplicationException, DatabaseOperationException{
		// TODO Auto-generated method stub
		LOG.info("Inside - method takenLeave in LeaveDao class");

		int days=0;
		try {
			connection = DbUtil.getConnection();
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			
			preparedStatement = connection
			.prepareStatement(QueryConstants.TAKEN_LEAVE);
			preparedStatement.setLong(1, leaveTo.getEmpId());
			preparedStatement.setString(2, leaveTo.getLeaveType());
			
			rs = preparedStatement.executeQuery();
			days=0;
			while (rs.next()) {
				days=days+rs.getInt("NO_LEAVE_TAKEN");
			}
			LOG.info("Exit - method takenLeave in PayrollDao class");

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
		
		return days;
	}

	

}
