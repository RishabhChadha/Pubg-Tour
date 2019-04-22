package com.stackroute.favouriteservice.exception;

@SuppressWarnings("serial")
public class MatchAlreadyExistsException extends Exception {

	String message;
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MatchAlreadyExistsException() {
		// TODO Auto-generated constructor stub
	}

	public MatchAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public MatchAlreadyExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public MatchAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MatchAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
