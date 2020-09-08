package com.booky.api.exception;

public class GroupDAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public GroupDAOException() {
		super();
	}

	public GroupDAOException(String message) {
		super(message);
	}

	public GroupDAOException(Exception exception) {
		super(exception);
	}

}
