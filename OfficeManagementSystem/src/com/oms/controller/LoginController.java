package com.oms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.oms.bo.LoginBO;
import com.oms.bo.ResignationBO;
import com.oms.constants.Constants;
import com.oms.constants.ErrorConstants;
import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.RegistrationTO;
import com.oms.model.ResigantionTO;
import com.oms.model.UserTO;
import com.oms.parser.Parser;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	
	public static final Logger LOG = Logger.getLogger("LoginController");
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LOG.info("Inside - method doPost in LoginController class");

		HttpSession session=request.getSession();
		final LoginBO logic = new LoginBO();
		try {
			final int employeeID = Integer.parseInt(request.getParameter("userName"));
			final String password = request.getParameter("password");
			UserTO user=new UserTO();
			user.setEmployeeID(employeeID);
			user.setPassword(password);
			Parser parser=new Parser();
			user=parser.checkPassword(user);
			//user=logic.validateUser(user);
			LOG.info("Login servlet invoked EmployeeID:" + employeeID + " Password:"
					+ password);
			if(user.isFlag()==true)
			{
				try {
					RegistrationTO registrationto=logic.getEmployeeDetails(user);
					ResigantionTO rto=new ResigantionTO();
					rto.setEmpId(user.getEmployeeID());
					ResignationBO rbo_res=new ResignationBO();
					ResigantionTO rto_res=null;
					rto_res = rbo_res.getDetails(rto);
					
					session.setAttribute("details",rto_res);
					session.setAttribute("employees",registrationto);
					session.setAttribute("role", user.getRole());

					RequestDispatcher requestdispatcher=request.getRequestDispatcher(Constants.HOMEPAGE);
					requestdispatcher.forward(request, response);
					
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
			}
			else
			{
				RequestDispatcher requestdispatcher=request.getRequestDispatcher("login.jsp");
				request.setAttribute("message", ErrorConstants.INVALIDUSERNAMEPASSWORD);
				requestdispatcher.forward(request, response);
			}
		} /*catch (DatabaseOperationException dbException) {	
			request.setAttribute("message", Constants.EXCEPTION);;
			LOG.error("Exception occured when processing validate user:"
					+ dbException.getMessage());
			final RequestDispatcher dispatcher = request
					.getRequestDispatcher(ErrorConstants.ERRORPAGE);
			dispatcher.forward(request, response);
		} catch (ApplicationException applicationException) {
			request.setAttribute("message", Constants.EXCEPTION);
			LOG.error("Exception occured when processing validate user:"
					+ applicationException.getMessage());
			final RequestDispatcher dispatcher = request
					.getRequestDispatcher(ErrorConstants.ERRORPAGE);
			dispatcher.forward(request, response);
		}*/catch (Exception exception) {
			request
			.setAttribute("message", Constants.EXCEPTION);
			LOG.error("Exception occured when processing validate user:"
					+ exception.getMessage());
			final RequestDispatcher dispatcher = request
					.getRequestDispatcher(ErrorConstants.ERRORPAGE);
			dispatcher.forward(request, response);
		}
		
		
		
		
	}

}
