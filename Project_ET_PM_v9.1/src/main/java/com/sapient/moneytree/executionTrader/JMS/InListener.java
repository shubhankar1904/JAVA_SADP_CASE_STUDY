package com.sapient.moneytree.executionTrader.JMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.sapient.entities.Fill;
import com.sapient.moneytree.executionTrader.DatabaseInterfaces.AllocationDatabaseInterface;
import com.sapient.moneytree.executionTrader.DatabaseInterfaces.ExecutionDatabaseInterface;
import com.sapient.moneytree.executionTrader.domain.Execution;
import com.sapient.moneytree.executionTrader.service.AllocationService;
//import com.sapient.moneytree.executionTrader.service.AutoAllocation;
import com.sapient.moneytree.executionTrader.service.FetchBlock;
import com.sapient.moneytree.executionTrader.service.FetchExecution;
//import com.sapient.moneytree.executionTrader.service.FetchExecution;
import com.sapient.moneytree.executionTrader.service.FetchOrder;
import com.sapient.moneytree.executionTrader.serviceImpl.AutoAllocate;
//import com.sapient.moneytree.executionTrader.serviceImpl.AutoAllocationImpl;
import com.sapient.moneytree.portfolioManager.DatabaseInterfaces.HoldingsDatabaseInterface;

/**
 * 
 * @author aaga50
 *
 */


































@Component
public class InListener {
	
	
	private FetchBlock fetchBlock;
	private ExecutionDatabaseInterface edi;
	private FetchOrder fetchOrder;
	private AllocationService allocation;
	private HoldingsDatabaseInterface holdingDBInterface;

	
	@Autowired
	public void setHoldingRepository(HoldingsDatabaseInterface holdingRepository) {
		this.holdingDBInterface = holdingRepository;
	}
   @Autowired
	public void setAllocation(AllocationService allocation) {
		this.allocation = allocation;
	}

	@Autowired
	public void setFetchOrder(FetchOrder fetchOrder) {
		this.fetchOrder = fetchOrder;
	}

	@Autowired
	public void setFetchBlock(FetchBlock fetchBlock) {
		this.fetchBlock = fetchBlock;
	}

	@Autowired
	public void setEdi(ExecutionDatabaseInterface edi) {
		this.edi = edi;
	}


	AutoAllocate a = new AutoAllocate();

	// private AutoAllocation alloc;
	private FetchExecution exec;
	
	

	/**
	 * 
	 * @param storeService
	 */




    @Autowired
	public void setExec(FetchExecution exec) {
		this.exec = exec;
	}



	/**
	 * 
	 * @param fill
	 * @return
	 */
	@JmsListener(destination = "Fills")
	public void receiveOrder(Fill fill) {

		System.out.println("yayy" + fill.getBlock_id());
		Execution e = new Execution();
		e.setBlockId((int) fill.getBlockId());
		e.setSide(fill.getSide());
		e.setStatus("New");
		e.setSymbol(fill.getSymbol());
		e.setTxnprice(fill.getExecutionPrice());
		e.setExecutedQuantity( (int) fill.getExecutedQuantity());
		e.setOpenQuantity((long) fill.getOpenQuantity());
		exec.saveExecution(e);
		System.out.println("starting autoallocate");
		//a.checkMultipleExecution();
		a.autoAllocate1(e, fetchBlock, fetchOrder, allocation, edi,holdingDBInterface);
		// sends acknowledgement
		// trader.send_Ack(fill.getExecutionId());

	}

	/**
	 * 
	 * @param trader
	 */

	/**
	 * 
	 * @param ack
	 * @return
	 */
	// @JmsListener(destination = "Ack_Broker")

}