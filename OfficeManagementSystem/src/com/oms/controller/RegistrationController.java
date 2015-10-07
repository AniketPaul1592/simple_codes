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

import com.oms.bo.RegistrationBO;
import com.oms.constants.Constants;
import com.oms.constants.ErrorConstants;
import com.oms.cryptography.AESEncryption;
import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.RegistrationTO;

/**
 * Servlet implementation class RegistrationController
 */
public class RegistrationController extends HttpServlet {
	
	/** The Constant LOG. */
	public static final Logger LOG = Logger.getLogger("RegistrationController");
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationController() {
        super();
        // TODO Auto-generated constructor stub
    }
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOG.info("Inside - method doPost in RegistrationController class");
		RegistrationTO registartionTo=new RegistrationTO();
		registartionTo.setEmployeeName(request.getParameter("name"));
		registartionTo.setEmailId(request.getParameter("email"));
		registartionTo.setContact(Long.parseLong(request.getParameter("contact")));
		SimpleDateFormat sd=new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
		Date birthDate =null;
		Date joinDate=null;
			try {
				birthDate = sdf.parse(sdf.format(sd.parse(request.getParameter("birth_date"))));
				joinDate=sdf.parse(sdf.format(sd.parse(request.getParameter("join_date"))));
			} catch (ParseException e) {
				LOG.info("RegistrationController class - ParseException"
						+ e.getMessage());
				request.setAttribute("message", Constants.EXCEPTION);
				final RequestDispatcher dispatcher = request
						.getRequestDispatcher(ErrorConstants.ERRORPAGE);
				dispatcher.forward(request, response);
			}	
		registartionTo.setEmployeeExist("Y");
		registartionTo.setBirthDate(birthDate);
		String encryptedPassword = null;
		try {
			encryptedPassword = AESEncryption.encrypt(request.getParameter("password"));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		registartionTo.setPassword(encryptedPassword);
		registartionTo.setLocation(request.getParameter("location"));
		registartionTo.setGender(request.getParameter("gender"));
		registartionTo.setExperience(Integer.parseInt(request.getParameter("experience")));
		registartionTo.setHighestQualification(request.getParameter("highest_qualification"));
		registartionTo.setEmployeeType(request.getParameter("employee_type"));
		registartionTo.setJoinDate(joinDate);
		RegistrationBO registrationBo=new RegistrationBO();
		
		int flag = 0;
			try {
				registartionTo=registrationBo.checkEmailId(registartionTo);
				if(registartionTo.getMessage()!=null)
				{
					request.setAttribute("messageRegistration", registartionTo.getMessage());
					RequestDispatcher rd=request.getRequestDispatcher("EmployeeRegistration.jsp");
					rd.forward(request, response);
				}
				flag = registrationBo.employeeEntry(registartionTo);
				if(flag==1)
				{  
					request.setAttribute("designation",registartionTo.getDesignation());
					request.setAttribute("retirementDate",registartionTo.getRetirementDate());
					request.setAttribute("empid",registartionTo.getEmpId());
					RequestDispatcher rd=request.getRequestDispatcher("SuccessRegister.jsp");
					rd.forward(request, response);
				}
			} catch (DatabaseOperationException dbException) {
				LOG.info("RegistrationController class - DatabaseOperationException"
						+ dbException.getMessage());
				request.setAttribute("message", Constants.EXCEPTION);
				final RequestDispatcher dispatcher = request
						.getRequestDispatcher(ErrorConstants.ERRORPAGE);
				dispatcher.forward(request, response);
			}  catch (ApplicationException appException) {
				LOG.info("RegistrationController class - ApplicationException"
						+ appException.getMessage());
				request.setAttribute("message", Constants.EXCEPTION);
				final RequestDispatcher dispatcher = request
						.getRequestDispatcher(ErrorConstants.ERRORPAGE);
				dispatcher.forward(request, response);
			}
		LOG.info("Exit - method doPost in RegistrationController class");
	}
	
}