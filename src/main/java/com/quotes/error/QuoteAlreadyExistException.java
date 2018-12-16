package com.quotes.error;

public class QuoteAlreadyExistException extends RuntimeException {
	
	public QuoteAlreadyExistException (String message) {
		super(message);
	}
	
}
