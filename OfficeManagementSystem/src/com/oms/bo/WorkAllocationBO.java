package com.oms.bo;

import org.apache.log4j.Logger;

import com.oms.dao.WorkAllocationDao;
import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.DisplayWorkAllocationTO;
import com.oms.model.WorkAllocationTO;

public class WorkAllocationBO {
	public static final Logger LOG = Logger.getLogger("WorkAllocationBO");
	public boolean employeeIDValidation(WorkAllocationTO objectTO) throws ApplicationException, DatabaseOperationException
	{
		LOG.info("Inside - method employeeIDValidation in WorkAllocationBO class");
		boolean response=true;
		WorkAllocationDao objectDao=new WorkAllocationDao();
		int result=0;
		result=objectDao.validateEmployee(objectTO);
		
		if(result==1){
			objectTO.setMessage("Employee");
			response=false;
		}
		else if(result==2){
			objectTO.setMessage("Manager");
			response=false;
		}else{
			response=true;
			if((objectTO.getShift().equalsIgnoreCase("Select")))
			{
				objectTO=objectDao.validationData(objectTO);
			}
			objectTO=objectDao.validateType(objectTO);
			
			objectDao.insertWorkAllocationDetails(objectTO);
			objectDao.updateEmployeeRegistration(objectTO);
		}
		
		LOG.info("Exit - method employeeIDValidation in WorkAllocationBO class");
		return response;
	}
	public DisplayWorkAllocationTO validateUser(WorkAllocationTO objectTO) throws ApplicationException, DatabaseOperationException
	{
		LOG.info("Inside - method DisplayWorkAllocationTOin WorkAllocationBO class");
		WorkAllocationDao objectDao=new WorkAllocationDao();
		objectDao.validateType(objectTO);
		
	    DisplayWorkAllocationTO objectDTO=new DisplayWorkAllocationTO();		
		objectDTO=objectDao.getUserDetails(objectTO);
		LOG.info("Exit - method DisplayWorkAllocationTO in WorkAllocationBO class");
		return objectDTO;
	}

}
