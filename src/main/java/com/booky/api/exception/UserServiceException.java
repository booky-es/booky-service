package com.booky.api.exception;

public class UserServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserServiceException() {
		super();
	}

	public UserServiceException(String message) {
		super(message);
	}

	public UserServiceException(Exception exception) {
		super(exception);
	}

}
