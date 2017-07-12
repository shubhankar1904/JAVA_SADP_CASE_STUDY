package com.sapient.moneytree.portfolioManager.serviceImpl;

import org.springframework.stereotype.Service;

import com.sapient.moneytree.portfolioManager.DatabaseInterfaces.OrderDatabaseInterface;
import com.sapient.moneytree.portfolioManager.domain.Orders;
import com.sapient.moneytree.portfolioManager.service.OrderService;
/**
 * implements OrderService interface
 * @author Neha Maheshwari
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	private OrderDatabaseInterface oRepository;// object of OrderdatabaseInterface
/**
 * Overridden method
 * deletes an order
 */
	@Override
	public void deleteOrder(Integer orderId) {
		oRepository.delete(orderId);

	}


	@Override
	public void modifyOrder(Integer orderId) {
		// add code here

	}
	/**
	 * Overridden method
	 * lists all orders in the db
	 */
	@Override
	public Iterable<Orders> listAllOrders() {
		return oRepository.findAll();
	}
	/**
	 * Overridden method
	 * ammends an order
	 */
	@Override
	public void ammendOrder(Integer orderId) {
		// TODO Auto-generated method stub

	}
	/**
	 * Overridden method
	 * saves an order
	 */
	@Override
	public Orders saveOrder(Orders order) {

		return oRepository.save(order);
	}

}
