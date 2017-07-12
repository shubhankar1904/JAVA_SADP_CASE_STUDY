package com.sapient.moneytree.executionTrader.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.moneytree.executionTrader.DatabaseInterfaces.ETOrderDatabaseInterface;
import com.sapient.moneytree.executionTrader.service.FetchOrder;
import com.sapient.moneytree.portfolioManager.domain.Orders;
import com.sapient.moneytree.portfolioManager.domain.Securities;

/**
 * 
 * @author aaga50
 *
 */
@Service
public class FetchOrderImpl implements FetchOrder {

	ETOrderDatabaseInterface repository;

	/**
	 * 
	 * @param repository
	 */
	@Autowired
	public void setRepository(ETOrderDatabaseInterface repository) {
		this.repository = repository;
	}

	
	@Override
	public Iterable<Orders> listByStatus(String status) {
		return repository.findByStatus(status);
	}

	
	@Override
	public Iterable<Orders> listAllProducts() {
		return repository.findAll();
	}

	@Override
	public void updateOrders(String status, Integer orderId) {
		repository.UpdateOrderStatus(status, orderId);
		return;
	}

	@Override
	public Orders getOrdersById(Integer id) {
		return repository.findByorderId(id);
	}

	// Added by Saurav - Start
	@Override
	public Iterable<Orders> FilterOrders(String side, String Type, String symbol) {
		return repository.orderFilter(side, Type, symbol);

	}

	@Override
	public Iterable<Securities> symbolFind() {
		return repository.findSymbol();
	}

	@Override
	public Iterable<Orders> OrdersFind(Integer BlkId) {
		return repository.findOrders(BlkId);
	}
	// Added by Saurav - End

	@Override
	public void updateBlockId(Integer blockId, Integer orderId) {

		repository.UpdateBlockIdStatus(blockId, orderId);
		return;
	}


	@Override
	public List<Orders> listbystatusandId(String status, int blockId) {
		// TODO Auto-generated method stub
		return repository.findByStatusandId(status, blockId);
	}


	@Override
	public List<Orders> listbystatusandId2(String status, String status2, int blockId) {
		// TODO Auto-generated method stub
		return repository.findByStatusandId2(status, status2, blockId);
	}


	@Override
	public void updateAllocatedQty(double allocatedQty, Integer orderId) {
		// TODO Auto-generated method stub
		repository.UpdateAllocatedQuantity(allocatedQty, orderId);
	}


	@Override
	public Orders fetchAllocatedQty(int orderId) {
		// TODO Auto-generated method stub
		return repository.fetchQty(orderId);
		
	}

}
