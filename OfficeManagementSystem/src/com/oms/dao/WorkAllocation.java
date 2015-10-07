package com.oms.dao;

import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.DisplayWorkAllocationTO;
import com.oms.model.WorkAllocationTO;

public interface WorkAllocation {
	public void insertWorkAllocationDetails(WorkAllocationTO objectTO) throws ApplicationException, DatabaseOperationException;
	public void updateEmployeeRegistration(WorkAllocationTO objectTO) throws DatabaseOperationException, ApplicationException;
	public DisplayWorkAllocationTO getUserDetails(WorkAllocationTO objectTO) throws DatabaseOperationException;
	public WorkAllocationTO validationData(WorkAllocationTO objectTO) throws ApplicationException, DatabaseOperationException;
	public WorkAllocationTO validateType(WorkAllocationTO objectTO) throws ApplicationException, DatabaseOperationException;
	public int validateEmployee(WorkAllocationTO objectTO) throws ApplicationException, DatabaseOperationException;
}
