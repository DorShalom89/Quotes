package com.quotes.error;

public class NameIsNullException extends RuntimeException {
	
	public NameIsNullException (String message) {
		super(message);
	}
}
