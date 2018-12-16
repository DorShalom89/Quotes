package com.quotes.error;

public class QuoteNotFoundException extends RuntimeException {
	
	public QuoteNotFoundException (String message) {
		super(message);
	}
	
}
