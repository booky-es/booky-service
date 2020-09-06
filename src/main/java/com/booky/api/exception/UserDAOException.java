package com.booky.api.exception;

public class UserDAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserDAOException() {
		super();
	}

	public UserDAOException(String message) {
		super(message);
	}

	public UserDAOException(Exception exception) {
		super(exception);
	}

}
