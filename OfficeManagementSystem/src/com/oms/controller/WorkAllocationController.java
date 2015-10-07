package com.oms.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.oms.bo.WorkAllocationBO;
import com.oms.constants.Constants;
import com.oms.constants.ErrorConstants;
import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.DisplayWorkAllocationTO;
import com.oms.model.WorkAllocationTO;

/**
 * Servlet implementation class WorkAllocationController
 */
public class WorkAllocationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Logger LOG = Logger.getLogger("WorkAllocationController");
	  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkAllocationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(false);
    	if(session!=null){
    		if(session.getAttribute("role")==null){
    			response.sendRedirect("login.jsp");
    		
    		}
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LOG.info("Inside - method doPost in WorkAllocationController class");
		int employeeId= Integer.parseInt(request.getParameter("empId"));
		int managerId=0;
		String manager=new String();
		manager=request.getParameter("managerId");
		if(manager.length()>0)
		{
			managerId=Integer.parseInt(manager);
		}
		String projectId=(request.getParameter("projectId"));
		String projectType=request.getParameter("projectType");
		Date assignDate=new Date();	
        String shift=request.getParameter("shift");
	
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat modFormat=new SimpleDateFormat("dd-MMM-yyyy");
		try {
				if(request.getParameter("date")!="")	
				{
					assignDate=modFormat.parse(modFormat.format(format.parse(request.getParameter("date"))));
				}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WorkAllocationTO objectTO=new WorkAllocationTO();
		objectTO.setEmployeeId(employeeId);
		objectTO.setManagerId(managerId);
		objectTO.setProjectId(projectId);
		objectTO.setProjectType(projectType);
		objectTO.setShift(shift);
		objectTO.setAssignDate(assignDate);
		
		WorkAllocationBO objectBO=new WorkAllocationBO();
		DisplayWorkAllocationTO objectDTO=new DisplayWorkAllocationTO();
		boolean result=true;
		try {
			result=objectBO.employeeIDValidation(objectTO);
			if(result==false)
			{
				request.setAttribute("boObject", objectTO);
				RequestDispatcher rd=request.getRequestDispatcher("workAllocation.jsp");
				rd.forward(request, response);
			}
			else
			{
				objectDTO=objectBO.validateUser(objectTO);
				if(objectDTO.getEmployeeId()!=0)
				{
					request.setAttribute("daoObject", objectDTO);
					RequestDispatcher rd=request.getRequestDispatcher("WorkAllocationDisplay.jsp");
					rd.forward(request, response);
				}
				else
				{
					request.setAttribute("boObject", objectTO);
					RequestDispatcher rd=request.getRequestDispatcher("workAllocation.jsp");
					rd.forward(request, response);
				}
			}
		} catch (DatabaseOperationException dbException) {
			LOG.info("WorkAllocationController class - DatabaseOperationException"
					+ dbException.getMessage());
			request.setAttribute("message", Constants.EXCEPTION);
			final RequestDispatcher dispatcher = request
					.getRequestDispatcher(ErrorConstants.ERRORPAGE);
			dispatcher.forward(request, response);
		}  catch (ApplicationException appException) {
			LOG.info("WorkAllocationController class - ApplicationException"
					+ appException.getMessage());
			request.setAttribute("message", Constants.EXCEPTION);
			final RequestDispatcher dispatcher = request
					.getRequestDispatcher(ErrorConstants.ERRORPAGE);
			dispatcher.forward(request, response);
		}
		
		
	}

}
