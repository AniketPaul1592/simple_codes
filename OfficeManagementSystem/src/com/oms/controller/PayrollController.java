package com.oms.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.oms.bo.PayrollBO;
import com.oms.constants.Constants;
import com.oms.constants.ErrorConstants;
import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.PayrollTO;

/**
 * Servlet implementation class PayrollController
 */
public class PayrollController extends HttpServlet {
	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger("PayrollController");
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayrollController() {
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
		LOG.info("Inside - method doPost in PayrollController class");
		
		
		try {
			PayrollTO payrolldetails=new PayrollTO();

			payrolldetails.setEmployeeID(Long.parseLong(request.getParameter("employeeid"))); 
			payrolldetails.setBankName(request.getParameter("designation"));
			payrolldetails.setBankName(request.getParameter("bankname"));
			payrolldetails.setAccountNo(Long.parseLong(request.getParameter("accountno")));
			payrolldetails.setPolicyNo(request.getParameter("policyno"));
			payrolldetails.setPolicyName(request.getParameter("policyname"));
			payrolldetails.setDesignation(request.getParameter("designation"));
			
			final PayrollBO payrollLogic=new PayrollBO();
			payrolldetails=payrollLogic.checkEmpId(payrolldetails);
			if(payrolldetails.getMessage()!=null)
			{
				RequestDispatcher requestdispatcher=request.getRequestDispatcher("payrollForm.jsp");
				request.setAttribute("message", payrolldetails.getMessage());
				requestdispatcher.forward(request, response);
			}
			else{	
				payrolldetails=payrollLogic.checkDesignation(payrolldetails);
				if(payrolldetails.getMessage()!=null)
				{
					RequestDispatcher requestdispatcher=request.getRequestDispatcher("payrollForm.jsp");
					request.setAttribute("message", payrolldetails.getMessage());
					requestdispatcher.forward(request, response);
				}
				else{
					payrolldetails=payrollLogic.selectDetails(payrolldetails);
					payrolldetails=payrollLogic.enrollpayroll(payrolldetails);
					if(payrolldetails.isFlag()==true)
					{
						payrolldetails=payrollLogic.calculateNetSalary(payrolldetails);
						RequestDispatcher requestdispatcher=request.getRequestDispatcher("DisplayPayroll.jsp");
						request.setAttribute("payrolldetails", payrolldetails);
						requestdispatcher.forward(request, response);
					}
					else
					{
						RequestDispatcher requestdispatcher=request.getRequestDispatcher(ErrorConstants.ERRORPAGE);
						requestdispatcher.forward(request, response);
					}
				}
			}
			LOG.info("Exit - method doPost in PayrollController class");
		}catch (DatabaseOperationException dbException) {
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
