package com.sapient.moneytree.executionTrader.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.moneytree.executionTrader.DatabaseInterfaces.ExecutionDatabaseInterface;
import com.sapient.moneytree.executionTrader.domain.Allocations;
import com.sapient.moneytree.executionTrader.domain.Block;
import com.sapient.moneytree.executionTrader.domain.Execution;
import com.sapient.moneytree.executionTrader.service.AllocationService;
import com.sapient.moneytree.executionTrader.service.AutoAllocation;
import com.sapient.moneytree.executionTrader.service.FetchBlock;
import com.sapient.moneytree.executionTrader.service.FetchOrder;
import com.sapient.moneytree.portfolioManager.domain.Orders;

/*@Service*/
/*public class AutoAllocationImpl implements AutoAllocation {
	private ExecutionDatabaseInterface edi;

	private FetchBlock fetchBlock;

	private FetchOrder fetchOrder;
	private AllocationService allocation;


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

	

	@Override
	public void autoAllocate(Execution e) {
		System.out.println("in auto allocate");
		int blockId = e.getBlockId();
		long executedQty = e.getExecutedQuantity();
		Block b = fetchBlock.getBlockById(blockId);
		System.out.println("before orderlist");
		List<Orders> orderList=fetchOrder.listbystatusandId("Send For Execution", b.getBlockId());
		for (Orders o : orderList) {
			System.out.println("$#@" + o);
		}
		System.out.println("After orderlist");
		Collections.sort(orderList, new Comparator<Orders>() {
			public int compare(Orders m1, Orders m2) {
				return m1.getTimeStamp().compareTo(m2.getTimeStamp());
			}
		});
      Allocations alloc;
      System.out.println("before main for each loop");
		for (Orders o : orderList) {
			if (executedQty <= 0)
				break;
			if (o.getOpenQty() < executedQty) {
				executedQty -= o.getOpenQty();
				o.setAllocatedQty(o.getOpenQty());
				o.setOpenQty(0);
				fetchOrder.updateOrders("Executed", o.getOrderId());
				alloc=new Allocations();
				alloc.setSymbol(o.getSymbol());
				alloc.setAllocatedQty(o.getAllocatedQty());
				alloc.setSide(o.getOrderSide());
				alloc.setTransactionPrice(e.getTxnprice());
				allocation.saveAllocation(alloc);
				System.out.println("allocation saved in if");
				
			} else {
				o.setAllocatedQty(executedQty);
				o.setOpenQty(o.getOpenQty() - executedQty);
				executedQty = 0;
				fetchOrder.updateOrders("Partially Executed", o.getOrderId());
				alloc=new Allocations();
				alloc.setSymbol(o.getSymbol());
				alloc.setAllocatedQty(o.getAllocatedQty());
				alloc.setSide(o.getOrderSide());
				alloc.setTransactionPrice(e.getTxnprice());
				allocation.saveAllocation(alloc);
				System.out.println("allocation saved in else");
			}

		}

		System.out.println("after main loop");
		fetchBlock.updateBlockAfterAutoAllocate(e.getExecutedQuantity(), e.getOpenQuantity(), (ArrayList<Orders>) orderList, blockId);
		b = fetchBlock.getBlockById(blockId);
		if (b.getOpenQty() == 0) {
			fetchBlock.updateBlock("Complete", blockId);
		}

		else if (b.getExecutedQty() != b.getTotalQty()) {
			fetchBlock.updateBlock("Partial", blockId);
		}
		// Update Order Status
          System.out.println("done");
	}

}*/

