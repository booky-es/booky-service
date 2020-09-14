package com.booky.api.exception;

public class UrlServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public UrlServiceException() {
		super();
	}

	public UrlServiceException(String message) {
		super(message);
	}

	public UrlServiceException(Exception exception) {
		super(exception);
	}

}
