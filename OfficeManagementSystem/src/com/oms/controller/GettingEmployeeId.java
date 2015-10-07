package com.oms.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oms.bo.CheckID;

/**
 * Servlet implementation class getEmployeeId
 */
public class GettingEmployeeId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GettingEmployeeId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String in=request.getParameter("empId");
		
			long empId=Long.parseLong(in);
			CheckID logic=new CheckID();
			boolean flag=logic.checkID(empId);
			if(flag==true){
				response.getWriter().print(" <span class='glyphicon glyphicon-ok'></span>");
			}
			else{
				//response.getWriter().print("Wrong");
			}
		
		/*String input=request.getParameter("empId").toString();
		System.out.println(input);
		long empId=Long.parseLong(input);
		CheckID logic=new CheckID();
		boolean flag=logic.checkID(empId);
		if(flag==true){
			response.getWriter().print("Correct");
		}
		else{
			response.getWriter().print("Wrong");
		}*/
		
	}

}
