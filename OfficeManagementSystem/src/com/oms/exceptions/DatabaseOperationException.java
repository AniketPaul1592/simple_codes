package com.oms.exceptions;

import java.sql.SQLException;

public class DatabaseOperationException extends Exception {

	public DatabaseOperationException(SQLException sqlException) {
		// TODO Auto-generated constructor stub
	}

	public DatabaseOperationException(String string, SQLException e) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
