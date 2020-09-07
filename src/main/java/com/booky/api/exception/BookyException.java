package com.booky.api.exception;

public class BookyException extends Exception {

	private static final long serialVersionUID = 1L;

	public BookyException() {
		super();
	}

	public BookyException(String message) {
		super(message);
	}

	public BookyException(Exception exception) {
		super(exception);
	}

}
