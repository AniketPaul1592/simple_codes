package com.oms.dao;

import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.UserTO;

public interface User {

	public UserTO getUserDetails(UserTO userTO) throws ApplicationException, DatabaseOperationException ;
	
}
