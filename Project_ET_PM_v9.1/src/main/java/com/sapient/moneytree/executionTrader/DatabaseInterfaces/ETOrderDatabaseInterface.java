package com.sapient.moneytree.executionTrader.DatabaseInterfaces;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;

import com.sapient.moneytree.portfolioManager.domain.Orders;
import com.sapient.moneytree.portfolioManager.domain.Securities;

/**
 * 
 * @author aaga50
 *
 */
public interface ETOrderDatabaseInterface extends CrudRepository<Orders, Long> {

	/**
	 * 
	 * @param status
	 * @return
	 */
	// public List<Orders> findByorderId(String productId);
	@Query("select u from Orders u where u.orderStatus = ?1")
	public List<Orders> findByStatus(String status);
	
	@Query("select u from Orders u where u.orderId = ?1")
	public Orders fetchQty(int orderId);
	
	@Transactional
	@Query("select u from Orders u where u.orderStatus = ?1 AND u.blockId = ?2 ")
	public List<Orders> findByStatusandId(String status,int blockId);
	
	@Transactional
	@Query("select u from Orders u where u.orderStatus = ?1 OR u.orderStatus = ?2 AND u.blockId = ?3 ")
	public List<Orders> findByStatusandId2(String status,String status2,int blockId);

	/**
	 * 
	 * @param status
	 * @param orderId
	 */
	@Modifying
	@Transactional
	@Query("Update  Orders u set u.orderStatus=?1 " + "where u.orderId=?2")
	public void UpdateOrderStatus(String status, Integer orderId);

	/**
	 * 
	 * @param side
	 * @param type
	 * @param symbol
	 * @return
	 */
	// Added by Saurav - start
	@Transactional
	@Query("select u from Orders u where u.orderSide=?1 AND u.orderType=?2 AND u.symbol=?3")
	public List<Orders> orderFilter(String side, String type, String symbol);

	/**
	 * 
	 * @return
	 */
	@Transactional
	@Query("select u from Securities u")
	public List<Securities> findSymbol();

	/**
	 * 
	 * @param BlkId
	 * @return
	 */
	@Transactional
	@Query("select u from Orders u where u.blockId=?1")
	public List<Orders> findOrders(Integer BlkId);
	// Added by Saurav - end

	/**
	 * 
	 * @param orderId
	 * @return
	 */
	public Orders findByorderId(int orderId);

	/**
	 * 
	 * @param blockId
	 * @param orderId
	 */
	@Modifying
	@Transactional
	@Query("Update  Orders u set u.blockId=?1 " + "where u.orderId=?2")
	public void UpdateBlockIdStatus(Integer blockId, Integer orderId);
	
	
	@Modifying
	@Transactional
	@Query("Update  Orders u set u.allocatedQty=?1 " + "where u.orderId=?2")
	public void UpdateAllocatedQuantity(double allocatedQty, Integer orderId);
	
}
