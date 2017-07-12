
package com.sapient.moneytree.executionTrader.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author aaga50
 *
 */
@Entity
@Table(name = "AllocationDetails")
public class Allocations implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int allocationId;
	private int managerId;
	private String side;
	private String attribute;
	private int portfolioId;
	private String symbol;
	private String status;
	private String accountType;
	private int openQty;
	private double transactionPrice;
	private double pricefee;
	private double allocatedQty;

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public int getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioid(int portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getOpenQty() {
		return openQty;
	}

	public void setOpenQty(int openQty) {
		this.openQty = openQty;
	}

	public double getTransactionPrice() {
		return transactionPrice;
	}

	public void setTransactionPrice(double transactionPrice) {
		this.transactionPrice = transactionPrice;
	}

	public double getPricefee() {
		return pricefee;
	}

	public void setPricefee(double pricefee) {
		this.pricefee = pricefee;
	}

	public double getAllocatedQty() {
		return allocatedQty;
	}

	public void setAllocatedQty(double allocatedQty) {
		this.allocatedQty = allocatedQty;
	}

}