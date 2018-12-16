package com.quotes.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.quotes.dao.QuoteDao;
import com.quotes.entities.Operation;
import com.quotes.entities.Quote;
import com.quotes.entities.QuoteLog;
import com.quotes.error.NameIsNullException;
import com.quotes.error.PriceIsNegativeException;

@CrossOrigin("*")
@RestController
@RequestMapping("/quotes-api")
public class QuotesRestAPI {

	
	
	@Autowired
	private QuoteDao quoteDao;
		
	@RequestMapping (value = "/quote" , method = RequestMethod.GET)
	public List<Quote> getAllQuotes() {	
		return this.quoteDao.getAllQuotes();
	}
	
	@RequestMapping (value = "/quote/{name}" , method = RequestMethod.GET)
	public Quote getQuote(@PathVariable("name") String name) {
		validateName(name);
		return this.quoteDao.getQuote(name);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping (value = "/quote" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createQuote(@RequestBody Quote quote)  {
		try {
			createQuoteLog(quote.getName(), Operation.CREATE);
			validateName(quote.getName());
			validatePrice(quote.getPrice());
			this.quoteDao.addQuote(quote);
		} catch (Exception e) {
			if (e.getMessage() != null) this.quoteDao.log.setMessage(e.getMessage());
			else {
				this.quoteDao.log.setErrorCode(100);
				this.quoteDao.log.setMessage("unexpected error");
			}
			throw e;
		} finally {
			this.quoteDao.persistQuoteLog();
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping (value = "/quote" , method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateQuote(@RequestBody Quote quote) {
		try {
			createQuoteLog(quote.getName(), Operation.CREATE);
			validateName(quote.getName());
			validatePrice(quote.getPrice());
			this.quoteDao.updateQuote(quote);
		} catch (Exception e) {
			if (e.getMessage() != null) this.quoteDao.log.setMessage(e.getMessage());
			else {
				this.quoteDao.log.setErrorCode(100);
				this.quoteDao.log.setMessage("unexpected error");
			}
			throw e;
		} finally {
			this.quoteDao.persistQuoteLog();
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping (value = "/quote/{name}" , method = RequestMethod.DELETE)
	public void deleteQuote(@PathVariable("name") String name) {
		try {
			createQuoteLog(name, Operation.CREATE);
			validateName(name);
			this.quoteDao.deleteQuote(name);
		} catch (Exception e) {
			if (e.getMessage() != null) this.quoteDao.log.setMessage(e.getMessage());
			else {
				this.quoteDao.log.setErrorCode(100);
				this.quoteDao.log.setMessage("unexpected error");
			}
			throw e;
		} finally {
			this.quoteDao.persistQuoteLog();
		}
	}
	
	private void validateName(String name) {
		if (name == null) {
			this.quoteDao.log.setErrorCode(4);
			throw new NameIsNullException("Name is null");
		}
	}
	private void validatePrice(int price) {
		if (price < 0) {
			this.quoteDao.log.setErrorCode(5);
			throw new PriceIsNegativeException("Price is negative");
		}
	}
	private void createQuoteLog(String quoteName, Operation operation) {
		this.quoteDao.log = new QuoteLog(quoteName,operation);
	}
}
