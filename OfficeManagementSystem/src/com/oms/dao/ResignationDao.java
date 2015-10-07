package com.oms.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.oms.constants.QueryConstants;
import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.ResigantionTO;
import com.oms.util.DbUtil;


public class ResignationDao implements Resignation {
	public static final Logger LOG = Logger.getLogger("ResignationDao");
	private Connection connection;
	private PreparedStatement preparedstatement;
	
	public ResigantionTO getAmountDao(ResigantionTO rto)throws ApplicationException, DatabaseOperationException {
		LOG.info("Inside - method getAmount_Dao in ResigantionDAO class");
		try {
			connection=DbUtil.getConnection();
			preparedstatement=connection.prepareStatement(QueryConstants.SELECT_DOJ);
			preparedstatement.setLong(1,rto.getEmpId());
			ResultSet resultSet=preparedstatement.executeQuery();
			while(resultSet.next())
			rto.setDateOfJoining(resultSet.getDate("DATE_JOINING"));
			
			preparedstatement=connection.prepareStatement(QueryConstants.SELECT_BASIC);
			preparedstatement.setLong(1,rto.getEmpId());	
			resultSet=preparedstatement.executeQuery();
			while(resultSet.next())
			rto.setBasic(resultSet.getDouble("BASIC"));
			
			preparedstatement=connection.prepareStatement(QueryConstants.INSERT_RESIGNATION);
			preparedstatement.setLong(1, rto.getEmpId());
			preparedstatement.setString(2,rto.getEmpName());
			preparedstatement.setString(3, rto.getProjId());
			preparedstatement.setLong(4, rto.getMgrId());
			Date doa= new Date(rto.getDateOfApply().getTime());
			preparedstatement.setDate(5,doa);
			preparedstatement.setInt(6,rto.getNoticePeriod());
			preparedstatement.setString(7, rto.getComments());
			preparedstatement.executeQuery();
			
		}catch (ClassNotFoundException e) {
			throw new ApplicationException(e);
		} catch (IOException e) {
			throw new ApplicationException(e);
		} catch (SQLException sqlException) {
			throw new DatabaseOperationException(sqlException);
		}
		LOG.info("Exit - method getAmount_Dao in ResigantionDAO class");
		return rto;
		
	}
	public boolean validateDao(ResigantionTO rto)throws ApplicationException, DatabaseOperationException {
		// TODO Auto-generated method stub
		LOG.info("Inside - method validateDao in ResigantionDAO class");
		boolean retVal=false;
		try {
			connection=DbUtil.getConnection();
			preparedstatement=connection.prepareStatement(QueryConstants.SELECT_validate);
			preparedstatement.setLong(1, rto.getEmpId());
			preparedstatement.setString(2, rto.getPassword());
			ResultSet resultSet=preparedstatement.executeQuery();
			int count=0;
			while(resultSet.next())
			{
				count++;
			}
			if(count>0)
			{
				retVal=true;
			}
			
		} catch (ClassNotFoundException e) {
			throw new ApplicationException(e);
		} catch (IOException e) {
			throw new ApplicationException(e);
		} catch (SQLException sqlException) {
			throw new DatabaseOperationException(sqlException);
		}
		LOG.info("Exit - method validateDao in ResigantionDAO class");
		return retVal;
	}
	public ResigantionTO getDetailsDao(ResigantionTO rto)throws ApplicationException, DatabaseOperationException {
		LOG.info("Inside - method getDetails_dao in ResigantionDAO class");
		try {
			connection=DbUtil.getConnection();
			
			preparedstatement=connection.prepareStatement(QueryConstants.SELECT_empname);
			preparedstatement.setLong(1,rto.getEmpId());
			ResultSet resultSet=preparedstatement.executeQuery();
			while(resultSet.next())
			rto.setEmpName(resultSet.getString("EMP_NAME"));
			
			preparedstatement=connection.prepareStatement(QueryConstants.SELECT_emptype);
			preparedstatement.setLong(1,rto.getEmpId());
			resultSet=preparedstatement.executeQuery();
			while(resultSet.next())
			rto.setEmpType(resultSet.getString("TYPE_EMPLOYEMENT"));
			
			
			
			preparedstatement=connection.prepareStatement(QueryConstants.SELECT_pid);
			preparedstatement.setLong(1,rto.getEmpId());
			resultSet=preparedstatement.executeQuery();
			while(resultSet.next())
			rto.setProjId(resultSet.getString("PROJ_ID"));
			
			preparedstatement=connection.prepareStatement(QueryConstants.SELECT_mng);
			preparedstatement.setLong(1,rto.getEmpId());
			resultSet=preparedstatement.executeQuery();
			while(resultSet.next())
			rto.setMgrName(resultSet.getString("EMP_NAME"));
			
		} catch (ClassNotFoundException e) {
			throw new ApplicationException(e);
		} catch (IOException e) {
			throw new ApplicationException(e);
		} catch (SQLException sqlException) {
			throw new DatabaseOperationException(sqlException);
		}
		LOG.info("Exit - method getDetails_dao in ResigantionDAO class");
		return rto;
		
	}
	public void updateStatusDao(long empId)throws ApplicationException, DatabaseOperationException {
		// TODO Auto-generated method stub
		LOG.info("Inside - method updateStatusDao in ResigantionDAO class");
		try {
			connection=DbUtil.getConnection();
			preparedstatement=connection.prepareStatement(QueryConstants.UPDATE_EMP_REG);
			preparedstatement.setLong(1,empId);
			int count=preparedstatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new ApplicationException(e);
		} catch (IOException e) {
			throw new ApplicationException(e);
		} catch (SQLException sqlException) {
			throw new DatabaseOperationException(sqlException);
		}
		LOG.info("Exit - method updateStatusDao in ResigantionDAO class");	
	
	}
	public String getEmpExistsDao(long empId) throws ApplicationException, DatabaseOperationException{
		// TODO Auto-generated method stub
		LOG.info("Inside - method getEmpExistsDao in ResigantionDAO class");
		String retVal = null;
		try {
			connection=DbUtil.getConnection();
			preparedstatement=connection.prepareStatement(QueryConstants.SELECT_empExist);
			preparedstatement.setLong(1,empId);
			ResultSet resultSet=preparedstatement.executeQuery();
			
			while(resultSet.next())
			retVal=resultSet.getString("EMPLOYEE_EXIST");
			
		} catch (ClassNotFoundException e) {
			throw new ApplicationException(e);
		} catch (IOException e) {
			throw new ApplicationException(e);
		} catch (SQLException sqlException) {
			throw new DatabaseOperationException(sqlException);
		}
		LOG.info("Exit - method getEmpExistsDao in ResigantionDAO class");
		return retVal;
	}
	public Date getDOJDAO(long empId) throws ApplicationException, DatabaseOperationException{
		// TODO Auto-generated method stub
		LOG.info("Inside - method getDOJDAO in ResigantionDAO class");
		Date DOJ = null;
		try {
			connection=DbUtil.getConnection();
			preparedstatement=connection.prepareStatement(QueryConstants.SELECT_DOJ);
			preparedstatement.setLong(1,empId);
			ResultSet resultSet=preparedstatement.executeQuery();
			while(resultSet.next())
			DOJ=resultSet.getDate("DATE_JOINING");
		} catch (ClassNotFoundException e) {
			throw new ApplicationException(e);
		} catch (IOException e) {
			throw new ApplicationException(e);
		} catch (SQLException sqlException) {
			throw new DatabaseOperationException(sqlException);
		}
		LOG.info("Exit - method getDOJDAO in ResigantionDAO class");
		return DOJ;
	}
	
	
	
	
	
}
