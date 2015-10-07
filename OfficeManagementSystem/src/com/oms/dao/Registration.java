/**
 * 
 */
package com.oms.dao;

import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.RegistrationTO;


/**
 * @author 438879
 *
 */
public interface Registration {
	public int addEmployeesInDaoRegular(RegistrationTO registrationTo) throws ApplicationException, DatabaseOperationException ;
	public int addEmployeesInDaoContractor(RegistrationTO registrationTo) throws ApplicationException, DatabaseOperationException;
		
	public RegistrationTO checkEmailId(RegistrationTO registrationTo) throws ApplicationException, DatabaseOperationException;
	
	public void getEmployeeId(RegistrationTO registrationTo) throws ApplicationException, DatabaseOperationException;
}
