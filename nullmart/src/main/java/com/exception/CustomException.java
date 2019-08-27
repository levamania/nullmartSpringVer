package com.exception;

public class CustomException extends RuntimeException {
	private String mesg;

	public CustomException(String mesg) {
		super();
		this.mesg = mesg;
	}

	@Override
	public String getMessage() {
		System.out.println("경고: "+mesg);
		return super.getMessage();
	}
}
