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

import com.oms.bo.ResignationBO;
import com.oms.constants.Constants;
import com.oms.constants.ErrorConstants;
import com.oms.exceptions.ApplicationException;
import com.oms.exceptions.DatabaseOperationException;
import com.oms.model.ResigantionTO;

/**
 * Servlet implementation class ResignationController
 */
public class ResignationController extends HttpServlet {
	public static final Logger LOG = Logger.getLogger("ResignationController");
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResignationController() {
        super();
        // TODO Auto-generated constructor stub
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
		LOG.info("Inside - method doPost in ResigantionController class");
		HttpSession session=request.getSession(false);
		
		try{

		final ResigantionTO rto= (ResigantionTO) session.getAttribute("details");
		final long empId=rto.getEmpId();
		final long EMPID=Long.parseLong(request.getParameter("empid"));
		if(empId==EMPID)
		{
			ResignationBO bo=new ResignationBO();
			boolean empExists=bo.getEmpExists(empId);
			if(!empExists)
			{
				RequestDispatcher dispatcher = null;
				dispatcher = request.getRequestDispatcher("ResignForm.jsp");
				request.setAttribute("msg","Employee doesnot exists");
				dispatcher.forward(request, response);
			}
			
			else
			{
				SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
				final String doa=request.getParameter("doa");
				Date dd = null;
				try {
					dd=sdf.parse(doa);
					
				} catch (ParseException e) {
						// TODO Auto-generated catch block
					
				}
				if(dd==null)
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("ResignForm.jsp");
					request.setAttribute("msg","invalid date format...it should be dd-MMM-yyyy");
					dispatcher.forward(request, response);
				}
				else
				{
					
				Date DOJ=bo.getDOJ(EMPID);
				
				if(dd.after(DOJ))
				{
					String np=request.getParameter("np");
					final int noticePeriod=Integer.parseInt(np);
					final String comments=request.getParameter("comments");
					rto.setEmpId(empId);
					rto.setDateOfApply(dd);
					rto.setNoticePeriod(noticePeriod);
					rto.setComments(comments);
				
					ResigantionTO rtoObject=bo.getAmount(rto);
					session.setAttribute("details",rtoObject);
					bo.updateStatus(empId);
				
				
					RequestDispatcher dispatcher = null;
					dispatcher = request.getRequestDispatcher("Success.jsp");
					request.setAttribute("successMessage", "Your application of resignation submitted successfully and you will get amount="+rto.getAmount());
			
			dispatcher.forward(request, response);
			}
				else
				{
					RequestDispatcher dispatcher = null;
					dispatcher = request.getRequestDispatcher("ResignForm.jsp");
					request.setAttribute("msg","DOA cannot be before DOJ");
					dispatcher.forward(request, response);
				}
			
			}
			}
		}
		
			else
			{
				RequestDispatcher dispatcher = null;
				dispatcher = request.getRequestDispatcher("ResignForm.jsp");
				request.setAttribute("msg","Enter your employee id...its invalid");
				dispatcher.forward(request, response);
			}
		
		
		}catch (DatabaseOperationException dbException) {
			LOG.info("ResignationController class - DatabaseOperationException"
					+ dbException.getMessage());
			request.setAttribute("message", Constants.EXCEPTION);
			final RequestDispatcher dispatcher = request
					.getRequestDispatcher(ErrorConstants.ERRORPAGE);
			dispatcher.forward(request, response);
		}  catch (ApplicationException appException) {
			LOG.info("ResignationController class - ApplicationException"
					+ appException.getMessage());
			request.setAttribute("message", Constants.EXCEPTION);
			final RequestDispatcher dispatcher = request
					.getRequestDispatcher(ErrorConstants.ERRORPAGE);
			dispatcher.forward(request, response);
		} catch (Exception appException) {
			LOG.info("ResignationController class -Exception"
					+ appException.getMessage());
			request.setAttribute("message", Constants.EXCEPTION);
			final RequestDispatcher dispatcher = request
					.getRequestDispatcher(ErrorConstants.ERRORPAGE);
			dispatcher.forward(request, response);
		
			}
	}
	
			
		
	
		

}
