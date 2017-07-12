package com.sapient.moneytree.portfolioManager.service;

import com.sapient.moneytree.portfolioManager.domain.Orders;
/**
 * interface for order services
 * @author Neha Maheshwari
 *
 */
public interface OrderService {
/**
 * 
 * @param orderId  order id for a selected order
 */
	void deleteOrder(Integer orderId);
/**
 * 
 * @param orderId order id for a selected order
 */
	void modifyOrder(Integer orderId);
	/**
	 * 
	 * @return list of all orders
	 */

	Iterable<Orders> listAllOrders();
	/**
	 * 
	 * @param orderId  order id for a selected order
	 */

	void ammendOrder(Integer orderId);
/**
 * 
 * @param order  orders created
 * @return
 * orders
 */
	Orders saveOrder(Orders order);

}
