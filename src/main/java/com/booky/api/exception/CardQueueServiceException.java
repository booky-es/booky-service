package com.booky.api.exception;

public class CardQueueServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public CardQueueServiceException() {
		super();
	}

	public CardQueueServiceException(String message) {
		super(message);
	}

	public CardQueueServiceException(Exception exception) {
		super(exception);
	}

}
