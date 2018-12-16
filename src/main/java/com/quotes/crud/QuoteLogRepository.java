package com.quotes.crud;

import org.springframework.data.repository.CrudRepository;
import com.quotes.entities.QuoteLog;

public interface QuoteLogRepository extends CrudRepository<QuoteLog,Integer> {
	

}
