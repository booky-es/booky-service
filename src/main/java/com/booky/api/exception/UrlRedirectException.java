package com.booky.api.exception;

public class UrlRedirectException extends Exception {

	private static final long serialVersionUID = 1L;

	public UrlRedirectException() {
		super();
	}

	public UrlRedirectException(String message) {
		super(message);
	}

	public UrlRedirectException(Exception exception) {
		super(exception);
	}

}
