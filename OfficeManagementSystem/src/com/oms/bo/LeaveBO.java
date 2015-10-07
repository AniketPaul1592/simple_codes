package com.oms.bo;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.oms.dao.Leave;
import com.oms.dao.LeaveDao;
import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.LeaveTO;

public class LeaveBO {
	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger("LeaveBO");
	
	private int calculateLeave(Date startDate, Date endDate) {
		LOG.info("Inside - method calculateLeave in LeaveBO class");

		// TODO Auto-generated method stub
		Calendar c1=Calendar.getInstance();
		Calendar c2=Calendar.getInstance();
		c1.setTime(startDate);
		c2.setTime(endDate);
		long day1=c1.getTimeInMillis();
		long day2=c2.getTimeInMillis();
		double d=((double)((double)day2-(double)day1)/(3600000*24));
		LOG.info("Exit - method calculateLeave in LeaveBO class");

		return (int)d;
	}
	public boolean validateLeave(LeaveTO leaveTo) throws ApplicationException, DatabaseOperationException{
		LOG.info("Inside - method validateLeave in LeaveBO class");

		Leave leaveDao=new LeaveDao();
		boolean flag=true;
		String message=new String();
		
		/*
		 * Check manager details
		 */
		flag= leaveDao.checkManagerStatus(leaveTo);
		if(flag==false){
			message="Manager is on leave..sorry leave cannot be approved";
			leaveTo.setErrorMessage(message);
			
			return flag;
		}
		int day_leaves=calculateLeave(leaveTo.getStartDate(),leaveTo.getEndDate())+1;
		
		boolean f=leaveDao.checkJoinDate(leaveTo);
		boolean f1=leaveDao.checkStartDate(leaveTo);
		boolean f2=leaveDao.checkEndtDate(leaveTo);
		if((f==false)){
			flag=false;
			message="Leave Date is before joining date";
			leaveTo.setErrorMessage(message);
			
			return flag;
		}
		else if((f1==false)||(f2==false)){
			flag=false;
			message="Leave Date is already occupied by you";
			leaveTo.setErrorMessage(message);
			
			return flag;
		}
		
		if(flag!=false){
			
			if(leaveTo.getEmployeeType().equalsIgnoreCase("Regular")){
				String leaveType=leaveTo.getLeaveType();
				if(leaveType.equalsIgnoreCase("casual")){
					if(day_leaves>5){
						flag=false;
						message="Regular Employee Casual Leave should not be greater than 5 at a streach";
						leaveTo.setErrorMessage(message);
						
						return flag;
					}			
				}
				else if(leaveType.equalsIgnoreCase("earn")){
					if(day_leaves>3){
						message="Regular Employee Earn Leave should not be greater than 3 at a streach";
						leaveTo.setErrorMessage(message);
						
						flag=false;
						return flag;
					}
				}	
			}
			else if(leaveTo.getEmployeeType().equalsIgnoreCase("Contractor")){
				if(day_leaves>4){
					message="Contractor Leave should not be greater than 4 at a strach";
					leaveTo.setErrorMessage(message);
					
					flag=false;
					return flag;
				}
			}
		}
			
		if(leaveTo.getEmployeeType().equalsIgnoreCase("Regular")){

			int defaultLeave=leaveDao.getLeave(leaveTo);
			int takenLeave=leaveDao.takenLeave(leaveTo);
			
			if((flag==false)||(defaultLeave-takenLeave<day_leaves)){
				flag=false;
				message="You do not have enough leave balance";
				leaveTo.setErrorMessage(message);
				
				return flag;
			}
			else{
				
				leaveDao.insertLeaveDetails(leaveTo);
			}
		}
		else{
			int takenCasualLeave=leaveDao.takenLeave(leaveTo);
			int defaultCasualkLeave=leaveDao.getLeave(leaveTo);
			if((flag==false)||(defaultCasualkLeave-takenCasualLeave)<day_leaves){
				
				flag=false;
				message="You do not have enough leave balance";
				leaveTo.setErrorMessage(message);
				
				return flag;
			}
			else{
				leaveDao.insertLeaveDetails(leaveTo);		
			}
		}

		LOG.info("Exit - method validateLeave in LeaveBO class");

		return flag;
	}

	
}
