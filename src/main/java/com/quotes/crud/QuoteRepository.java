package com.quotes.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.quotes.entities.Quote;

public interface QuoteRepository extends CrudRepository<Quote,String> {
	
	public List<Quote> findAll();

}
