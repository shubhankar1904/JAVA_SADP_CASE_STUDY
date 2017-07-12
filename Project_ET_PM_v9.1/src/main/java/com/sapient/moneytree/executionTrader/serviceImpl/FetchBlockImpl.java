package com.sapient.moneytree.executionTrader.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.moneytree.executionTrader.DatabaseInterfaces.BlockDatabaseInterface;
import com.sapient.moneytree.executionTrader.domain.Block;
import com.sapient.moneytree.executionTrader.service.FetchBlock;
import com.sapient.moneytree.portfolioManager.domain.Orders;

/**
 * 
 * @author aaga50
 *
 */
@Service
public class FetchBlockImpl implements FetchBlock {

	BlockDatabaseInterface repository;

	/**
	 * 
	 * @param repository
	 */
	@Autowired
	public void setRepository(BlockDatabaseInterface repository) {
		this.repository = repository;
	}

	@Override
	public Iterable<Block> listByStatus(String status) {
		return repository.findByStatus(status);
	}

	@Override
	public Block saveBlock(Block block) {
		// TODO Auto-generated method stub
		return repository.save(block);
	}
	
	@Override
	public Block getBlockById(Integer id) {
		return repository.findByBlockId(id);
	}

	@Override
	public void updateBlock(String status, int blockId) {
		 repository.UpdateBlockStatus(status, blockId);
		 return;
	}

	@Override
	public void updateBlockAfterAutoAllocate(long executedQty, long openQty,  int blockId) {
		repository.UpdateBlockAfterAutoAllocation(executedQty, openQty, blockId);
	}

	@Override
	public void UpdateBlockOpenAfterAutoAllocation(int openQty, int blockId) {
		repository.UpdateBlockOpenAfterAutoAllocation(openQty, blockId);
		
	}

	@Override
	public void UpdateBlockExecAfterAutoAllocation(int executedQty, int blockId) {
		// TODO Auto-generated method stub
		repository.UpdateBlockExecAfterAutoAllocation(executedQty, blockId);
	}

	@Override
	public void UpdateBlockOrderAfterAutoAllocation(List<Orders> orderList, int blockId) {
		// TODO Auto-generated method stub
		repository.UpdateBlockOrderAfterAutoAllocation(orderList, blockId);
	}

	/*@Override
	public void updateBlockExec(int executedQty, int openQty, int blockId) {
		repository.UpdateBlock(executedQty, openQty, blockId);
	}*/
}
