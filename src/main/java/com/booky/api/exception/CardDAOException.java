package com.booky.api.exception;

public class CardDAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public CardDAOException() {
		super();
	}

	public CardDAOException(String message) {
		super(message);
	}

	public CardDAOException(Exception exception) {
		super(exception);
	}

}
