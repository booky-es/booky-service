package com.booky.api.exception;

public class GroupServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public GroupServiceException() {
		super();
	}

	public GroupServiceException(String message) {
		super(message);
	}

	public GroupServiceException(Exception exception) {
		super(exception);
	}

}
