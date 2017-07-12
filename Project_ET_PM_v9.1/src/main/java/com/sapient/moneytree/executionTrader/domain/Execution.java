
package com.sapient.moneytree.executionTrader.domain;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "ExecutionDetails")
public class Execution implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int execId;
	/*public static long getSerialversionuid() {
		return serialVersionUID;
	}*/

	public int getExecId() {
		return execId;
	}

	private double txnprice;
	private String symbol;
	private int executedQuantity;
	private long openQuantity;
	private String side;
	private String status;
	private Date txnTime;
    private int blockId;
	public double getTxnprice() {
		return txnprice;
	}

	public void setTxnprice(double txnprice) {
		this.txnprice = txnprice;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(java.util.Date date) {
		this.txnTime = (Date) date;
	}

	public int getBlockId() {
		return blockId;
	}

	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}

	public int getExecutedQuantity() {
		return executedQuantity;
	}

	public void setExecutedQuantity(int executedQuantity) {
		this.executedQuantity = executedQuantity;
	}

	public long getOpenQuantity() {
		return openQuantity;
	}

	public void setOpenQuantity(long openQuantity) {
		this.openQuantity = openQuantity;
	}

}