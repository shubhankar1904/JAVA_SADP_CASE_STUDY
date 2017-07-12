package com.sapient.moneytree.portfolioManager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Table for securities
 * @author Shubhankar Mayank
 *
 */
@Entity
@Table(name="Securities")
public class Securities {

	
	@Id
	private String symbol;
	private String company;
	private String curPrice;
	
	public Securities()
    {
    	
    	
    }
	public Securities(String symbol, String company,String curPrice) {
		
		this.symbol = symbol;
		this.company = company;
		this.curPrice = curPrice;
		this.symbol = symbol;
	
		
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCurPrice() {
		return curPrice;
	}
	public void setCurPrice(String curPrice) {
		this.curPrice = curPrice;
	}
	
	
	
}
