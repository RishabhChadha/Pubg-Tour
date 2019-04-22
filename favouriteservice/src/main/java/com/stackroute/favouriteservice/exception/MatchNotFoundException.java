package com.stackroute.favouriteservice.exception;

public class MatchNotFoundException extends Exception {

	@SuppressWarnings("serial")
	
	String message;
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MatchNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public MatchNotFoundException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public MatchNotFoundException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public MatchNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public MatchNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
