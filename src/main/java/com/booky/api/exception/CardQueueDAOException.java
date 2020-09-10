package com.booky.api.exception;

public class CardQueueDAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public CardQueueDAOException() {
		super();
	}

	public CardQueueDAOException(String message) {
		super(message);
	}

	public CardQueueDAOException(Exception exception) {
		super(exception);
	}

}
