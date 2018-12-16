package com.quotes.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Where;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity (name = "quote")
@Where(clause="is_active=1")
public class Quote {
	
	@Id
	@Column(nullable = false, name="quote_name", columnDefinition="NVARCHAR(150)")
	private String name;
	
	@Column 
	private int price;
	
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn(name="quote_name", referencedColumnName="quote_name")
	private List<Item> items;
	
	@JsonIgnore
	@Column(name="is_active")
	private Boolean active = true;
		 
	
	public Quote() {
		super();
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Quotes [name=" + name + ", price=" + price + ", items=" + items + "]";
	}
	
	
	
}
