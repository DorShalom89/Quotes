package com.quotes.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name="quote_log")
public class QuoteLog {

	
	@Id @GeneratedValue (strategy=GenerationType.IDENTITY) 
	private int id;
	@Column
	private Date createdDate;
	@Column
	private String quoteId;
	@Enumerated(EnumType.STRING)
	@Column
	private Operation operation;
	@Column
	private int errorCode;
	@Column
	private String message;
	
	
	public QuoteLog() {
		super();
	}
	
	public QuoteLog(String quoteId, Operation operation) {
		this.createdDate = new Date();
		this.quoteId = quoteId;
		this.operation = operation;
		this.message = "Sucess";
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "QuoteLog [id=" + id + ", createdDate=" + createdDate + ", quoteId=" + quoteId + ", operation="
				+ operation + ", errorCode=" + errorCode + ", message=" + message + "]";
	}




	
	
	


	
	
	

}
