//2.	If Employee is Regular
//Amount=    PF + Gratuity (if applicable)
//(i)	PF calculated as follows
//PF            = 1850*2*(years of Experience in months)
//
//(ii)	 Gratuity will be applicable if Experience is >5         
//Gratuity   = Basic * 4.8*( years of Experience in moths) 


package com.oms.bo;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.oms.dao.ResignationDao;
import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.ResigantionTO;

public class ResignationBO {
	public static final Logger LOG = Logger.getLogger("ResignationBO");
	public ResigantionTO getAmount(ResigantionTO rto) throws ApplicationException, DatabaseOperationException {
		LOG.info("Inside - method getAmount in ResigantionBO class");
		double amount = 0;
		ResignationDao rdo=new ResignationDao();
		ResigantionTO detailRto=rdo.getAmountDao(rto);
		Date DOJ=detailRto.getDateOfJoining();
		Date DOA=detailRto.getDateOfApply();
		if(DOA.after(DOJ))
		{
		if(detailRto.getEmpType().equalsIgnoreCase("contractor"))
		{
			amount=detailRto.getBasic()*1.50;
		}
		else if(detailRto.getEmpType().equalsIgnoreCase("regular"))
		{
			
			int noticePeriod=detailRto.getNoticePeriod();
			int experience=calculateExperience(DOJ, DOA,noticePeriod);
			double provFund=1850*2*(experience);
			double gratuity=0;
			if((experience/12)>5)
			{
				gratuity=detailRto.getBasic()*4.8*experience;
			}
			amount=gratuity+provFund;
			
		}
		detailRto.setAmount(amount);
		}
		else
		{
			detailRto.setDateValidation(false);
		}
		LOG.info("Exit - method getAmount in ResigantionBO class");
		return detailRto;
}
	
	public ResigantionTO getDetails(ResigantionTO rto) throws ApplicationException, DatabaseOperationException
	{
		LOG.info("Inside - method getDetails in ResigantionBO class");
		ResignationDao rdo=new ResignationDao();
		ResigantionTO rtoRes=rdo.getDetailsDao(rto);
		LOG.info("Exit - method getDetails in ResigantionBO class");
		return rtoRes;
	}
	
	
	  private int calculateExperience(Date DOJ, Date DOA,int noticePeriod) {
			// TODO Auto-generated method stub
		  LOG.info("Inside - method calculateExperience in ResigantionBO class");
			Calendar c1=Calendar.getInstance();
			Calendar c2=Calendar.getInstance();
			c1.setTime(DOJ);
			c2.setTime(DOA);
			long day1=c1.getTimeInMillis();
			long day2=c2.getTimeInMillis()+(24*3600*1000*noticePeriod);
			double d=((double)((double)day1-(double)day2))/(3600000*24*30);
			LOG.info("Exit - method calculateExperience in ResigantionBO class");
			return (int)d;
		}

	public boolean validateUser(ResigantionTO rto) throws ApplicationException, DatabaseOperationException {
		// TODO Auto-generated method stub
		LOG.info("Inside - method validateUser in ResigantionBO class");
		ResignationDao rdo=new ResignationDao();
		boolean validateUser=rdo.validateDao(rto);
		LOG.info("Exit - method validateUser in ResigantionBO class");
		return validateUser;
	}

	public void updateStatus(long empId) throws ApplicationException, DatabaseOperationException {
		// TODO Auto-generated method stub
		LOG.info("Inside - method updateStatus in ResigantionBO class");
		ResignationDao rdo=new ResignationDao();
		rdo.updateStatusDao(empId);
		LOG.info("Exit - method updateStatus in ResigantionBO class");
	}

	public boolean getEmpExists(long empId) throws ApplicationException, DatabaseOperationException {
		// TODO Auto-generated method stub
		LOG.info("Inside - method getEmpExists in ResigantionBO class");
		ResignationDao rdo=new ResignationDao();
		String empExists=rdo.getEmpExistsDao(empId);
		boolean retVal=false;
		if(empExists.equalsIgnoreCase("Y"))
		{
			retVal=true;
		}
		LOG.info("Exit - method getEmpExists in ResigantionBO class");
		return retVal;
	}
	public Date getDOJ(long empId) throws ApplicationException, DatabaseOperationException
	{
		LOG.info("Inside - method getDOJ in ResigantionBO class");
		ResignationDao rd=new ResignationDao();
		Date DOJ=rd.getDOJDAO(empId);
		LOG.info("Exit - method getDOJ in ResigantionBO class");
		return DOJ;
	}
}
