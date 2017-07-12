package com.sapient.moneytree.executionTrader.service;

import java.util.List;

import com.sapient.moneytree.portfolioManager.domain.Orders;
import com.sapient.moneytree.portfolioManager.domain.Securities;

/**
 * 
 * @author aaga50
 *
 */
public interface FetchOrder {
	public Iterable<Orders> listByStatus(String status);

	public Iterable<Orders> listAllProducts();

	public Orders getOrdersById(Integer id);

	public void updateOrders(String status ,Integer orderId);
	public Orders fetchAllocatedQty(int orderId);

	// Added by Saurav
	public Iterable<Orders> FilterOrders(String side, String Type, String symbol);

	public Iterable<Securities> symbolFind();
	
	public Iterable<Orders> OrdersFind(Integer BlkId);
	public List<Orders> listbystatusandId(String status,int blockId);
	public List<Orders> listbystatusandId2(String status,String status2,int blockId);
	//End
	
	public void updateBlockId(Integer blockId, Integer orderId);
	
	public void updateAllocatedQty(double allocatedQty, Integer orderId);
}