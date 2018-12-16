package com.quotes.error;

public class PriceIsNegativeException extends RuntimeException {
	
	public PriceIsNegativeException (String message) {
		super(message);
	}
}
