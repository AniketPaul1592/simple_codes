/**
 * 
 */
package com.oms.dao;

import java.sql.Date;

import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.ResigantionTO;

/**
 * @author 439292
 *
 */
public interface Resignation {
	
	public ResigantionTO getAmountDao(ResigantionTO rto) throws ApplicationException, DatabaseOperationException;
	public boolean validateDao(ResigantionTO rto)throws ApplicationException, DatabaseOperationException;
	public ResigantionTO getDetailsDao(ResigantionTO rto)throws ApplicationException, DatabaseOperationException;
	public void updateStatusDao(long empId) throws ApplicationException, DatabaseOperationException;
	public String getEmpExistsDao(long empId)throws ApplicationException, DatabaseOperationException ;
	public Date getDOJDAO(long empId)throws ApplicationException, DatabaseOperationException ;
	
}
