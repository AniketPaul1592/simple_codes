/**
 * 
 */
package com.oms.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.oms.util.DbUtil;

/**
 * @author 438777
 *
 */
public class FindIDDao implements FindID {
	public static final Logger LOG = Logger.getLogger("FindIDDao");
	@Override
	public boolean findEmpId(long empId) {
		// TODO Auto-generated method stub
		
			Connection connection=null;
			
			LOG.info("Inside - method checkEndtDate in LeaveDao class");

			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			boolean flag=true;
			try {
				connection = DbUtil.getConnection();
				
				//long managerID=leaveTo.getManagerId();
				preparedStatement = connection
				.prepareStatement("select emp_id  from login_master where emp_id=? ");
				preparedStatement.setLong(1,empId);
				rs = preparedStatement.executeQuery();
				int count=0;
				while (rs.next()) {
					count++;
					
					
				}
				
				LOG.info("Exit - method checkEndtDate in PayrollDao class");
				if(count>0)return true;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
