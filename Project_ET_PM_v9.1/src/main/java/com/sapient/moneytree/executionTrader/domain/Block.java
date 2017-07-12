package com.sapient.moneytree.executionTrader.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sapient.moneytree.portfolioManager.domain.Orders;

/**
 * 
 * @author aaga50
 *
 */
@Entity
@Table(name = "BlockDetails")
public class Block implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int blockId;
	@OneToMany
	@JoinTable(name = "Block_Order", joinColumns = @JoinColumn(name = "Block_Id") , inverseJoinColumns = @JoinColumn(name = "Order_Id") )
	private List<Orders> orderList = new ArrayList<>();

	@OneToMany
	@JoinTable(name = "Block_Execution", joinColumns = @JoinColumn(name = "Block_Id") , inverseJoinColumns = @JoinColumn(name = "Execution_Id") )
	private List<Execution> execList = new ArrayList<>();

	private String side;
	private String symbol;
	private String status;
	private String blockOrderType;
	private double limitPrice;
	private double stopPrice;
	private int totalQty;
	private int executedQty;
	private int openQty;
	private int Traderid;
	private String blockQualifier;
	//private Date date;

	@Override
	public String toString() {
		return "Block [blockId=" + blockId + "]";
	}

	public List<Orders> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}

	public List<Execution> getExecList() {
		return execList;
	}

	public void setExecList(List<Execution> execList) {
		this.execList = execList;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
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

	public String getBlockOrderType() {
		return blockOrderType;
	}

	public void setBlockOrderType(String blockOrderType) {
		this.blockOrderType = blockOrderType;
	}

	public double getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(double limitPrice) {
		this.limitPrice = limitPrice;
	}

	public double getStopPrice() {
		return stopPrice;
	}

	public void setStopPrice(double stopPrice) {
		this.stopPrice = stopPrice;
	}

	public int getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}

	public int getExecutedQty() {
		return executedQty;
	}

	public void setExecutedQty(int executedQty) {
		this.executedQty = executedQty;
	}

	public int getOpenQty() {
		return openQty;
	}

	public void setOpenQty(int openQty) {
		this.openQty = openQty;
	}

	public int getTraderid() {
		return Traderid;
	}

	public void setTraderid(int traderid) {
		Traderid = traderid;
	}

	public String getBlockQualifier() {
		return blockQualifier;
	}

	public void setBlockQualifier(String blockQualifier) {
		this.blockQualifier = blockQualifier;
	}

	/*public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}*/

	public int getBlockId() {
		return blockId;
	}
}
