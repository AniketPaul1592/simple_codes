package com.oms.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.oms.dao.RegistrationDao;
import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.RegistrationTO;
import com.oms.parser.XmlMap;

public class RegistrationBO {
	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger("RegistrationBO");
	
	public RegistrationTO checkEmailId(RegistrationTO registrationTo) throws ApplicationException, DatabaseOperationException
	{
		LOG.info("Inside - method checkEmailId in RegistrationBO class");
		RegistrationDao registrationdao=new RegistrationDao();
		registrationTo=registrationdao.checkEmailId(registrationTo);
		LOG.info("Exit - method checkEmailId in RegistrationBO class");
		return registrationTo;
	}
	
	public int employeeEntry(RegistrationTO registrationTo) throws  ApplicationException, DatabaseOperationException 
	{
		LOG.info("Inside - method employeeEntry in RegistrationBO class");
		if(registrationTo.getExperience()==1)
		{
			registrationTo.setDesignation("TRAINEE");
		}
		else if(registrationTo.getExperience()==2)
		{
			registrationTo.setDesignation("SOFTWARE DEVELOPER");
		}
		else if(registrationTo.getExperience()==3)
		{
			registrationTo.setDesignation("SENIOR SOFTWARE DEVELOPER");
		}
		else if(registrationTo.getExperience()==4)
		{
			registrationTo.setDesignation("TEAM LEAD");
		}
		else if(registrationTo.getExperience()==5)
		{
			registrationTo.setDesignation("MANAGER");
		}
		if(registrationTo.getEmployeeType().equalsIgnoreCase("CONTRACTOR"))
		{
			registrationTo.setDesignation("SOFTWARE DEVELOPER");
		}
		
		  Date birthDate= registrationTo.getBirthDate();

		  SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
		  String BDate=sdf.format(birthDate);
		  
		  StringTokenizer st=new StringTokenizer(BDate,"-");
		  String day = null;
		  String month = null;
		  String year = null;
		  String updatedYear=null;
		  String newDate=new String();
		  int flag=0;
		  while(st.hasMoreTokens())
		  {
		      day=st.nextToken();
			  month=st.nextToken();
			  year=st.nextToken();
		  }
		  int yearUpdated=(Integer.parseInt(year))+60;
		  updatedYear=Integer.toString(yearUpdated);
		  newDate= newDate.concat(day).concat("-").concat(month).concat("-").concat(updatedYear);
			XmlMap object=new XmlMap();  
		  try {
				registrationTo.setRetirementDate(sdf.parse(newDate));
				
			} catch (ParseException e) {
				throw new ApplicationException(e);
			}
			
			  if(registrationTo.getEmployeeType().equalsIgnoreCase("Regular"))
				{
					RegistrationDao registrationDao=new RegistrationDao();
				    flag=registrationDao.addEmployeesInDaoRegular(registrationTo);
				    object.makeFile();
				}
				if(registrationTo.getEmployeeType().equalsIgnoreCase("Contractor"))
				{
					RegistrationDao registrationDao=new RegistrationDao();
				    flag=registrationDao.addEmployeesInDaoContractor(registrationTo);
				    object.makeFile();
				}
				
				RegistrationDao registrationDao=new RegistrationDao();
				registrationDao.getEmployeeId(registrationTo);	
		LOG.info("Exit - method employeeEntry in RegistrationBO class");
		return flag;
		  
		
		
	}

}
