package com.oms.bo;


import org.apache.log4j.Logger;

import com.oms.dao.User;
import com.oms.dao.UserDao;
import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.RegistrationTO;
import com.oms.model.UserTO;


public class LoginBO {
	
	public static final Logger LOG = Logger.getLogger("LoginBO");
	
	public UserTO validateUser(UserTO userTO) throws ApplicationException, DatabaseOperationException {
		
		LOG.info("Method Validate User Invoked" + userTO);
		User userdao = new UserDao();		
		return userdao.getUserDetails(userTO);
	}
	
	public RegistrationTO getEmployeeDetails(UserTO userTO)
	{
		LOG.info("Method getEmployeeDetails Invoked");
		UserDao userdao = new UserDao();
		return userdao.getEmployeeDetails(userTO);
	}
}
