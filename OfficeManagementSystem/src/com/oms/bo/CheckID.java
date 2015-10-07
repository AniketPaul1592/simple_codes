package com.oms.bo;

import com.oms.dao.FindID;
import com.oms.dao.FindIDDao;

public class CheckID {
	
	FindID findIdDao=new FindIDDao();
	public boolean checkID(long empId){
		return findIdDao.findEmpId(empId);
	}
}
