package com.sapient.moneytree.portfolioManager.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Shubhankar Mayank
 *
 */
@Entity
@Table(name = "Holdings")
public class Holdings {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int holdingId;
	private int pmId;
	private java.sql.Timestamp timeStamp;
	

	private String qty;
	private String buyPrice;

	private String symbol;
	private String type;
	private int tId;


	public Holdings() {

	}

	public Holdings(int pmId, String qty, String buyPrice, String symbol, String type, int tId, Timestamp timeStamp) {

		this.pmId = pmId;
		this.qty = qty;
		this.buyPrice = buyPrice;
		
		this.symbol = symbol;
		this.type = type;
		this.tId = tId;
		this.timeStamp = new java.sql.Timestamp(timeStamp.getTime());

	}
	public Date getTimeStamp() {
		return new Date(timeStamp.getTime());
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = new java.sql.Timestamp(timeStamp.getTime());
	}
	public int getHoldingId() {
		return holdingId;
	}

	public void setHoldingId(int holdingId) {
		this.holdingId = holdingId;
	}

	public int getPmId() {
		return pmId;
	}

	public void setPmId(int pmId) {
		this.pmId = pmId;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}

	

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int gettId() {
		return tId;
	}

	public void settId(int tId) {
		this.tId = tId;
	}
}
