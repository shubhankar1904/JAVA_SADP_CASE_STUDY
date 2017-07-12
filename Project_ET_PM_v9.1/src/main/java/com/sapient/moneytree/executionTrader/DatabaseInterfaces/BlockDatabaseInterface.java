package com.sapient.moneytree.executionTrader.DatabaseInterfaces;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sapient.moneytree.executionTrader.domain.Block;
import com.sapient.moneytree.portfolioManager.domain.Orders;

/**
 * 
 * @author aaga50
 *
 */
public interface BlockDatabaseInterface extends JpaRepository<Block, Long> {

	/**
	 * 
	 * @param status
	 * @return
	 */
	@Query("select u from Block u where u.status = ?1")
	public List<Block> findByStatus(String status);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Block findByBlockId(int id);

	/**
	 * 
	 * @param status
	 * @param blockId
	 */
	@Modifying
	@Transactional
	@Query("Update  Block u set u.status=?1 " + "where u.blockId=?2")
	public void UpdateBlockStatus(String status, int blockId);

	/**
	 * 
	 * @param executedQty
	 * @param openQty
	 * @param orderList
	 * @param blockId
	 */
	@Modifying
	@Transactional
	@Query("Update Block u set u.executedQty = ?1, u.openQty = ?2 " +"where u.blockId=?3")
	public void UpdateBlockAfterAutoAllocation(long executedQty, long openQty, int blockId);
	
	
	
	@Modifying
	@Transactional
	@Query("Update Block u set u.openQty =?1 " + " where u.blockId=?2")
	public void UpdateBlockOpenAfterAutoAllocation(int openQty, int blockId);
	
	@Modifying
	@Transactional
	@Query("Update Block u set u.executedQty =?1 " + " where u.blockId=?2")
	public void UpdateBlockExecAfterAutoAllocation(int executedQty, int blockId);
	
	
	
	@Modifying
	@Transactional
	@Query("Update Block u set u.orderList =?1 " + " where u.blockId=?2")
	public void UpdateBlockOrderAfterAutoAllocation(List<Orders> orderList, int blockId);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * @Modifying
	 * 
	 * @Transactional
	 * 
	 * @Query("Update  Block u set u.executedQty=?1 u.openQty=?2 " +
	 * "where u.blockId=?3") public void UpdateBlock(int executedQty, int
	 * openQty, int blockId);
	 */

	/*
	 * @Modifying
	 * 
	 * @Transactional
	 * 
	 * @Query("Update  Block u set u.executedQty=?1 u.openQty=?2 " +
	 * "where u.blockId=?3") public void UpdateBlockStatusafterexec(int
	 * executedQty, int openQty, int blockId);
	 */

}
