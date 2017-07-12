package com.sapient.moneytree.portfolioManager.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.sapient.moneytree.executionTrader.domain.Allocations;

/**
 * Orders entity
 * 
 * @author Shubhankar Mayank
 *
 */
@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	private String tName; // traderName
	private int tId; // traderId
	java.sql.Timestamp timeStamp;

	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "Order_Allocation", joinColumns = @JoinColumn(name = "Order_Id") , inverseJoinColumns = @JoinColumn(name = "Allocation_Id") )
	private Collection<Allocations> allocList = new ArrayList<>();
	private String orderSide; // side
	private String symbol;
	int pfId; // portfolioId
	private Double sprice; // stopPrice
	double qty;
	private Double lprice;
	private double allocatedQty;
	private String orderStatus;
	private String accountType;
	private String orderQualifier;
	private double openQty;
	private String orderType;
	private int managerid;
	double price;
	int pmId;
	private String securityName;
	private String orderValidity;

	private int blockId; // Added by saurav (getter/setter/toString)

	public Orders() {

	}

	public Orders(int orderId, int tId, String tname, Timestamp timeStamp, Collection<Allocations> allocList,
			String orderSide, String symbol, int pfId, Double sprice, double qty, Double lprice, double allocatedQty,
			String orderStatus, String accountType, String orderQualifier, double openQty, String orderType,
			int managerid, double price, int pmId, String securityName, String orderValidity) {
		super();
		this.orderId = orderId;
		this.tId = tId;
		this.tName = tName;
		this.timeStamp = new java.sql.Timestamp(timeStamp.getTime());
		this.allocList = allocList;
		this.orderSide = orderSide;
		this.symbol = symbol;
		this.pfId = pfId;
		this.sprice = sprice;
		this.qty = qty;
		this.lprice = lprice;
		this.allocatedQty = allocatedQty;
		this.orderStatus = orderStatus;
		this.accountType = accountType;
		this.orderQualifier = orderQualifier;
		this.openQty = openQty;
		this.orderType = orderType;
		this.managerid = managerid;
		this.price = price;
		this.pmId = pmId;
		this.securityName = securityName;
		this.orderValidity = orderValidity;

	}

	public String gettName() {
		return tName;
	}

	public void settName(String traderName) {
		this.tName = traderName;
	}

	public int getOrderId() {
		return orderId;
	}

	public int gettId() {
		return tId;
	}

	public void settId(int tId) {
		this.tId = tId;
	}

	public Date getTimeStamp() {
		return new Date(timeStamp.getTime());
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = new java.sql.Timestamp(timeStamp.getTime());
	}

	public Collection<Allocations> getAllocList() {
		return allocList;
	}

	public void setAllocList(Collection<Allocations> allocList) {
		this.allocList = allocList;
	}

	public String getOrderSide() {
		return orderSide;
	}

	public void setOrderSide(String orderSide) {
		this.orderSide = orderSide;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getPfId() {
		return pfId;
	}

	public void setPfId(int pfId) {
		this.pfId = pfId;
	}

	public Double getSprice() {
		return sprice;
	}

	public void setSprice(Double sprice) {
		this.sprice = sprice;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public Double getLprice() {
		return lprice;
	}

	public void setLprice(Double lprice) {
		this.lprice = lprice;
	}

	public double getAllocatedQty() {
		return allocatedQty;
	}

	public void setAllocatedQty(double allocatedQty) {
		this.allocatedQty = allocatedQty;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getOrderQualifier() {
		return orderQualifier;
	}

	public void setOrderQualifier(String orderQualifier) {
		this.orderQualifier = orderQualifier;
	}

	public double getOpenQty() {
		return openQty;
	}

	public void setOpenQty(double openQty) {
		this.openQty = openQty;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public int getManagerid() {
		return managerid;
	}

	public void setManagerid(int managerid) {
		this.managerid = managerid;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getPmId() {
		return pmId;
	}

	public void setPmId(int pmId) {
		this.pmId = pmId;
	}

	public String getSecurityName() {
		return securityName;
	}

	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}

	public String getOrderValidity() {
		return orderValidity;
	}

	public void setOrderValidity(String orderValidity) {
		this.orderValidity = orderValidity;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", tName=" + tName + ", tId=" + tId + ", timeStamp=" + timeStamp
				+ ", allocList=" + allocList + ", orderSide=" + orderSide + ", symbol=" + symbol + ", pfId=" + pfId
				+ ", sprice=" + sprice + ", qty=" + qty + ", lprice=" + lprice + ", allocatedQty=" + allocatedQty
				+ ", orderStatus=" + orderStatus + ", accountType=" + accountType + ", orderQualifier=" + orderQualifier
				+ ", openQty=" + openQty + ", orderType=" + orderType + ", managerid=" + managerid + ", price=" + price
				+ ", pmId=" + pmId + ", securityName=" + securityName + ", orderValidity=" + orderValidity
				+ ", blockId=" + blockId + "]";
	}

	public int getBlockId() {
		return blockId;
	}

	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}
}
