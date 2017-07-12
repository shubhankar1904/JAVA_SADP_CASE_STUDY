package com.sapient.moneytree.executionTrader.service;

import java.util.ArrayList;
import java.util.List;

import com.sapient.moneytree.executionTrader.domain.Block;
import com.sapient.moneytree.portfolioManager.domain.Orders;

/**
 * 
 * @author aaga50
 *
 */
public interface FetchBlock {
	public Iterable<Block> listByStatus(String status);

	public Block saveBlock(Block block);

	// Product saveProduct(Product product);
	public Block getBlockById(Integer id);

	public void updateBlock(String status, int blockId);
	//public void updateBlockExec(int executedQty, int openQty,int blockId);
	
	public void updateBlockAfterAutoAllocate(long executedQty, long openQty, int blockId );
	public void UpdateBlockOpenAfterAutoAllocation(int openQty, int blockId);
	
	public void UpdateBlockExecAfterAutoAllocation(int executedQty ,int blockId);
	
	public void UpdateBlockOrderAfterAutoAllocation(List<Orders> orderList, int blockId);
}
