package com.oms.dao;

import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.LeaveTO;

public interface Leave {

	boolean checkManagerStatus(LeaveTO leaveTo) throws ApplicationException, DatabaseOperationException;

	
	/*int takenSickLeave(LeaveTO leaveTo);

	int takenCasualLeave(LeaveTO leaveTo);

	int takenEarnLeave(LeaveTO leaveTo);


	int getSickLeave(LeaveTO leaveTo);


	int getCasualLeave(LeaveTO leaveTo);


	int getEarnLeave(LeaveTO leaveTo);
*/

	void insertLeaveDetails(LeaveTO leaveTo)throws ApplicationException, DatabaseOperationException ;


	boolean checkJoinDate(LeaveTO leaveTo)throws ApplicationException, DatabaseOperationException;


	boolean checkStartDate(LeaveTO leaveTo) throws ApplicationException, DatabaseOperationException;


	boolean checkEndtDate(LeaveTO leaveTo) throws ApplicationException, DatabaseOperationException ;


	int getLeave(LeaveTO leaveTo)throws ApplicationException, DatabaseOperationException;


	int takenLeave(LeaveTO leaveTo)throws ApplicationException, DatabaseOperationException;

}
