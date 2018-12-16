package com.quotes.error;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.quotes.QuotesApplication;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger logger = LogManager.getLogger(QuotesApplication.class);
	
	@ExceptionHandler(QuoteNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleQuoteNotFoundException(Exception ex){
		logger.error(ex.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(1, "quote not found","error");
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(QuoteAlreadyExistException.class)
	public final ResponseEntity<ErrorDetails> handleQuoteAlreadyExistException(Exception ex){
		logger.error(ex.getMessage()+"asdasd");
		ErrorDetails errorDetails = new ErrorDetails(2, "quote already exist","error");
		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(NameIsNullException.class)
	public final ResponseEntity<ErrorDetails> handleNameIsNullException(Exception ex){
		logger.error(ex.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(4, "name can not be null","error");
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(PriceIsNegativeException.class)
	public final ResponseEntity<ErrorDetails> handlePriceIsNegativeException(Exception ex){
		logger.error(ex.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(5, "price can not be negative","error");
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(Throwable.class)
	public final ResponseEntity<ErrorDetails> handleUnexpectedErrorException(Exception ex){
		logger.error(ex.getClass().getCanonicalName());
		ErrorDetails errorDetails = new ErrorDetails(100, "unexpected error","error");
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
}
