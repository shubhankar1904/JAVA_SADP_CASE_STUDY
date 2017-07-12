package com.sapient.moneytree.executionTrader.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.sapient.moneytree.executionTrader.DatabaseInterfaces.ExecutionDatabaseInterface;
import com.sapient.moneytree.executionTrader.domain.Allocations;
import com.sapient.moneytree.executionTrader.domain.Block;
import com.sapient.moneytree.executionTrader.domain.Execution;
import com.sapient.moneytree.executionTrader.service.AllocationService;
import com.sapient.moneytree.executionTrader.service.FetchBlock;
import com.sapient.moneytree.executionTrader.service.FetchOrder;
import com.sapient.moneytree.portfolioManager.DatabaseInterfaces.HoldingsDatabaseInterface;
import com.sapient.moneytree.portfolioManager.domain.Holdings;
import com.sapient.moneytree.portfolioManager.domain.Orders;

public class AutoAllocate {

	
	public void autoAllocate1(Execution e,FetchBlock fetchBlock,FetchOrder fetchOrder,AllocationService allocation,ExecutionDatabaseInterface edi, HoldingsDatabaseInterface holdingsDatabaseInterface) {
		System.out.println("in auto allocate for"+e.getBlockId());
		int blockId = e.getBlockId();
		long sum=0;
		long executedQty = e.getExecutedQuantity();
		Block b = fetchBlock.getBlockById(blockId);
		Orders order = new Orders();
		//System.out.println(b.getOrderList());
		System.out.println("before orderlist");
		
		List<Orders> orderList=fetchOrder.listbystatusandId2("Send For Execution", "Partially Executed", b.getBlockId());
		System.out.println("213~~~"+orderList);
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
				order=fetchOrder.fetchAllocatedQty(o.getOrderId());
				o.setAllocatedQty(o.getOpenQty()+order.getAllocatedQty());
				fetchOrder.updateAllocatedQty(o.getAllocatedQty(), o.getOrderId());
				o.setOpenQty(0);
				fetchOrder.updateOrders("Executed", o.getOrderId());
				Holdings holding=new Holdings();
				holding.setBuyPrice(String.valueOf(o.getPrice()));
			System.out.println("up price");
				holding.setQty(String.valueOf(o.getAllocatedQty()));
				System.out.println("up qty");
				holding.setSymbol(o.getSymbol());
				System.out.println("up symbol");
				holding.setType(o.getOrderType());
				System.out.println("up type");
				holding.settId(o.gettId());
				System.out.println("up tid");
				Date d=new Date();
				holding.setTimeStamp(d);
				holding.setPmId(o.getPmId());
				holdingsDatabaseInterface.save(holding);
				System.out.println("up holdings");
				alloc=new Allocations();
				alloc.setSymbol(o.getSymbol());
				alloc.setAllocatedQty(o.getAllocatedQty());
				alloc.setSide(o.getOrderSide());
				alloc.setTransactionPrice(e.getTxnprice());
				allocation.saveAllocation(alloc);
				System.out.println("allocation saved in if");
				
			} else {
				sum=executedQty;
				System.out.println("Sum is:"+executedQty);
				order=fetchOrder.fetchAllocatedQty(o.getOrderId());
				o.setAllocatedQty(executedQty+order.getAllocatedQty());
				o.setOpenQty(o.getOpenQty() - executedQty);
				executedQty = 0;
				fetchOrder.updateAllocatedQty(o.getAllocatedQty(), o.getOrderId());
				fetchOrder.updateOrders("Partially Executed", o.getOrderId());
				Holdings holding=new Holdings();
				holding.setBuyPrice(String.valueOf(o.getPrice()));
			    System.out.println("up price");
				holding.setQty(String.valueOf(o.getAllocatedQty()));
				System.out.println("up qty");
				holding.setSymbol(o.getSymbol());
				System.out.println("up symbol");
				holding.setType(o.getOrderType());
				System.out.println("up type");
				holding.settId(o.gettId());
				System.out.println("up tid");
				Date d=new Date();
				holding.setTimeStamp(d);
				holding.setPmId(o.getPmId());
				holdingsDatabaseInterface.save(holding);
				System.out.println("up holdings");
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
		System.out.println(e.getOpenQuantity()+"  "+e.getExecutedQuantity()+"  "+e.getBlockId());
	
		
		fetchBlock.UpdateBlockOpenAfterAutoAllocation((int)e.getOpenQuantity(),  blockId);
		fetchBlock.UpdateBlockExecAfterAutoAllocation((int)e.getExecutedQuantity(), blockId);
		//fetchBlock.UpdateBlockOrderAfterAutoAllocation(orderList, blockId);
		
		b = fetchBlock.getBlockById(blockId);
		if (b.getOpenQty() == 0) {
			fetchBlock.updateBlock("Complete", blockId);
		}

		else if (b.getExecutedQty() != b.getTotalQty()) {
			fetchBlock.updateBlock("Partially Executed", blockId);
		}
		// Update Order Status
          System.out.println("done");
          
	}
	
	
	public void checkMultipleExecution(){
		 
		System.out.println("Hathi Phenyl");
		
		
	}
	
	
}
