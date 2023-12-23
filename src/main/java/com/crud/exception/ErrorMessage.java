package com.crud.exception;

public class ErrorMessage {

	  private int statusCode;
	  private String message;
	  private String description;
	  
	  
	@Override
	public String toString() {
		return "ErrorMessage [statusCode=" + statusCode + ", message=" + message + ", description=" + description + "]";
	}

	public ErrorMessage(int statusCode, String message, String description) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.description = description;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	  
}
