package com.sapient.moneytree.portfolioManager.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class OrderCommunicator {

	@Id
	int orderId;
	java.sql.Timestamp timeStamp;
	private String symbol;
	double price;
	String orderType;
	int tId;
	private String orderSide;
	double qty;
	private double allocatedQty;

	public double getAllocatedQty() {
		return allocatedQty;
	}

	public void setAllocatedQty(double allocatedQty) {
		this.allocatedQty = allocatedQty;
	}

	public void setTimeStamp(java.sql.Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	private String orderStatus;
	private String tName;
	int pfId;
	int pmId;
	private String securityName;

	private String orderValidity;

	private Double sprice;
	private Double lprice;

	public OrderCommunicator(int orderId, Date timeStamp, String symbol, double price, int tId, String orderSide,
			double qty, String orderStatus, String tName, int pfId, int pmId, String securityName, String orderValidity,
			Double sprice, Double lprice, String orderType, double allocatedQty) {
		super();
		this.orderType = orderType;
		this.orderId = orderId;
		this.timeStamp = new java.sql.Timestamp(timeStamp.getTime());
		this.symbol = symbol;
		this.price = price;
		this.tId = tId;
		this.orderSide = orderSide;
		this.qty = qty;
		this.orderStatus = orderStatus;
		this.tName = tName;
		this.pfId = pfId;
		this.pmId = pmId;
		this.securityName = securityName;
		this.orderValidity = orderValidity;
		this.sprice = sprice;
		this.lprice = lprice;
		this.allocatedQty = allocatedQty;
	}

	public OrderCommunicator(Orders orderObj) {
		super();
		this.orderType = orderObj.getOrderType();
		this.orderId = orderObj.getOrderId();
		this.timeStamp = new java.sql.Timestamp(orderObj.getTimeStamp().getTime());
		this.symbol = orderObj.getSymbol();
		this.price = orderObj.getPrice();
		this.tId = orderObj.gettId();
		this.orderSide = orderObj.getOrderSide();
		this.qty = orderObj.getQty();
		this.orderStatus = orderObj.getOrderStatus();
		// this.tName = orderObj.gettName();
		this.pfId = orderObj.getPfId();
		this.pmId = orderObj.getPmId();
		this.securityName = orderObj.getSecurityName();
		this.orderValidity = orderObj.getOrderValidity();
		this.sprice = orderObj.getSprice();
		this.lprice = orderObj.getLprice();
		this.allocatedQty = orderObj.getAllocatedQty();
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", timeStamp=" + timeStamp + ", symbol=" + symbol + ", price=" + price
				+ ", orderType=" + orderType + ", tId=" + tId + ", orderSide=" + orderSide + ", qty=" + qty
				+ ", orderStatus=" + orderStatus + ", tName=" + tName + ", pfId=" + pfId + ", pmId=" + pmId
				+ ", securityName=" + securityName + ", orderValidity=" + orderValidity + ", sprice=" + sprice
				+ ", lprice=" + lprice + "]";
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderValidity() {
		return orderValidity;
	}

	public void setOrderValidity(String orderValidity) {
		this.orderValidity = orderValidity;
	}

	public Double getSprice() {
		return sprice;
	}

	public void setSprice(Double sprice) {
		this.sprice = sprice;
	}

	public Double getLprice() {
		return lprice;
	}

	public void setLprice(Double lprice) {
		this.lprice = lprice;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSecurityName() {
		return securityName;
	}

	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int gettId() {
		return tId;
	}

	public void settId(int tId) {
		this.tId = tId;
	}

	public String getOrderSide() {
		return orderSide;
	}

	public void setOrderSide(String orderSide) {
		this.orderSide = orderSide;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public Date getTimeStamp() {
		return new Date(timeStamp.getTime());
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = new java.sql.Timestamp(timeStamp.getTime());
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getPfId() {
		return pfId;
	}

	public void setPfId(int pfId) {
		this.pfId = pfId;
	}

	public int getPmId() {
		return pmId;
	}

	public void setPmId(int pmId) {
		this.pmId = pmId;
	}

	public OrderCommunicator() {
	}

}
