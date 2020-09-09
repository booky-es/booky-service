package com.booky.api.exception;

public class CardServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public CardServiceException() {
		super();
	}

	public CardServiceException(String message) {
		super(message);
	}

	public CardServiceException(Exception exception) {
		super(exception);
	}

}
