package com.quotes.dao;

import java.util.List;

import com.quotes.entities.Quote;

public interface QuoteDaoInterface {
	
	public List<Quote> getAllQuotes();
	public Quote getQuote(String name);
	public void addQuote(Quote quote);
	public void updateQuote(Quote quote);
	public void deleteQuote(String name);
	
	
}
