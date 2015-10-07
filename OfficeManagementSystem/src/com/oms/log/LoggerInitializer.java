package com.oms.log;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.oms.constants.SuccessConstants;

/**
 * Servlet implementation class LoggerInitializer
 */
public class LoggerInitializer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoggerInitializer() {
        super();
        // TODO Auto-generated constructor stub
    }

    /** The Constant LOG. */
	private static final Logger LOG = Logger
			.getLogger(LoggerInitializer.class);
    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		final String realPath = config.getServletContext().getRealPath("/");
		final String log4jFile = realPath
									+ SuccessConstants.LOG4J_FILE;
		PropertyConfigurator.configure(log4jFile);
		LOG.info("Application Initialized");

		
	}

}
