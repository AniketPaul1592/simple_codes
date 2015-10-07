package com.oms.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.oms.bo.LeaveBO;
import com.oms.constants.Constants;
import com.oms.constants.ErrorConstants;
import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.RegistrationTO;
import com.oms.model.LeaveTO;

/**
 * Servlet implementation class LeaveController
 */
public class LeaveController extends HttpServlet {
	
	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger("LeaveController");
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaveController() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	HttpSession session=req.getSession(false);
    	if(session!=null){
    		if(session.getAttribute("role")==null){
    			resp.sendRedirect("login.jsp");
    		
    		}
    	}
    		
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LOG.info("Inside - method doPost in LeaveController class");
		HttpSession session = request.getSession(false);
		LeaveTO leaveTo=new LeaveTO();
		LeaveBO logic=new LeaveBO();
		final RegistrationTO employee=(RegistrationTO) session.getAttribute("employees");
		
		final String leaveType=request.getParameter("leaveType");
		final String startDate=request.getParameter("startDate");
		final String endDate=request.getParameter("endDate");
		String know=null;
		long kt=0L;
		know=request.getParameter("kt");
		if(know==null){
			kt=employee.getEmpId();
		}
		else{
			kt=Long.parseLong(know);
		}
		SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
		String reason=request.getParameter("reason");
		if(reason==null||reason==""){
			reason="No reason";
		}
		try {
			Date d1=sdf.parse(startDate);
			Date d2=sdf.parse(endDate);
			leaveTo.setEmpId(employee.getEmpId());
			leaveTo.setEmployeeName(employee.getEmployeeName());
			leaveTo.setEmployeeType(employee.getEmployeeType());
			leaveTo.setManagerId(employee.getManagerId());
			leaveTo.setLeaveType(leaveType);
			leaveTo.setStartDate(d1);
			leaveTo.setEndDate(d2);
			leaveTo.setReason(reason);
			leaveTo.setKnowledgeTransition(kt);
			boolean flag=logic.validateLeave(leaveTo);
			if(flag==true){
				RequestDispatcher dispatcher = request.getRequestDispatcher("Success.jsp");
				request.setAttribute("successMessage", "Your leave is successfully Applied");
				dispatcher.forward(request, response);
			}
			else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("leave.jsp");
				request.setAttribute("errorMessage", leaveTo.getErrorMessage());
				dispatcher.forward(request, response);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseOperationException dbException) {
			LOG.info("PayrollController class - DatabaseOperationException"
					+ dbException.getMessage());
			request.setAttribute("message", Constants.EXCEPTION);
			final RequestDispatcher dispatcher = request
					.getRequestDispatcher(ErrorConstants.ERRORPAGE);
			dispatcher.forward(request, response);
		}  catch (ApplicationException appException) {
			LOG.info("PayrollController class - ApplicationException"
					+ appException.getMessage());
			request.setAttribute("message", Constants.EXCEPTION);
			final RequestDispatcher dispatcher = request
					.getRequestDispatcher(ErrorConstants.ERRORPAGE);
			dispatcher.forward(request, response);
		}
		
		
		
	}

}
