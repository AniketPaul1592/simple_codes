package com.oms.exceptions;

import java.io.IOException;
import java.text.ParseException;

public class ApplicationException  extends Exception {

	public ApplicationException(IOException e) {
		// TODO Auto-generated constructor stub
	}

	public ApplicationException(ClassNotFoundException e) {
		// TODO Auto-generated constructor stub
	}

	public ApplicationException(ParseException e) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
