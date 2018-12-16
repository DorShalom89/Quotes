package com.quotes.error;

public class ErrorDetails {
	
	private int errorCode;
	private String decription;
	private String level;
	
		
	public ErrorDetails(int errorCode, String decription, String level) {
		super();
		this.errorCode = errorCode;
		this.decription = decription;
		this.level = level;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
}
