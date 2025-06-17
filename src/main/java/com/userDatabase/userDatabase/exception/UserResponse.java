package com.userDatabase.userDatabase.exception;

public class UserResponse {
	boolean success; 
	String message;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public UserResponse(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}
	
	
	
}
