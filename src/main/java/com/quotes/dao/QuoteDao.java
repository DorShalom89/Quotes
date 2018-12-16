package com.quotes.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.quotes.crud.QuoteLogRepository;
import com.quotes.crud.QuoteRepository;
import com.quotes.entities.Quote;
import com.quotes.entities.QuoteLog;
import com.quotes.error.QuoteAlreadyExistException;
import com.quotes.error.QuoteNotFoundException;

@Component
@Scope("prototype")
public class QuoteDao implements QuoteDaoInterface {
	
	@Autowired
	private QuoteRepository quoteRepository;
	@Autowired
	private QuoteLogRepository quoteLogRepository;
	public QuoteLog log;
	
	@Override
	public List<Quote> getAllQuotes() {
		return quoteRepository.findAll();
	}
	
	@Override
	public Quote getQuote(String name) {
		if (quoteRepository.findById(name).orElse(null) == null) {
			this.log.setErrorCode(1);
			throw new QuoteNotFoundException("Quote name: " + name + " was not found");
		}
		return quoteRepository.findById(name).orElse(null);
	}

	@Override
	public void addQuote(Quote quote) {
		if (quoteRepository.findById(quote.getName()).orElse(null) != null) {
			this.log.setErrorCode(2);
			throw new QuoteAlreadyExistException("Quote name: " + quote.getName() + " already exist");
		}
		this.quoteRepository.save(quote);
	}

	@Override
	public void updateQuote(Quote quote) {
		if (quoteRepository.findById(quote.getName()).orElse(null) == null) {
			this.log.setErrorCode(1);
			throw new QuoteNotFoundException("Quote name: " + quote.getName() + " was not found");
		}
		this.quoteRepository.save(quote);
	}

	@Override
	public void deleteQuote(String name) {
		if (quoteRepository.findById(name).orElse(null) == null) {
			this.log.setErrorCode(1);
			throw new QuoteNotFoundException("Quote name: " + name + " was not found");
		}
		Quote quote = this.quoteRepository.findById(name).orElse(null);
		quote.setActive(false);
		this.quoteRepository.save(quote);
	}
	
	public void persistQuoteLog() {
		this.quoteLogRepository.save(this.log);
	}

		
}
