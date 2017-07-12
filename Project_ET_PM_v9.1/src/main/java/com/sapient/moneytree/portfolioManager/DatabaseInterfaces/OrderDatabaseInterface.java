package com.sapient.moneytree.portfolioManager.DatabaseInterfaces;

import java.util.List;

import org.neo4j.cypher.internal.compiler.v2_2.ast.conditions.orderByOnlyOnIdentifiers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapient.moneytree.portfolioManager.domain.Orders;
/**
 * 
 * @author Shubhankar Mayank
 *
 */
public interface OrderDatabaseInterface extends JpaRepository<Orders, Integer> {
	/**
	 * 
	 * @param pmId
	 *            is portfolio manager's id
	 * @return returns list
	 */

	public List<Orders> findByPmIdOrderByTimeStampDesc(int pmId);

	public Orders findByOrderId(int orderId);

	/**
	 * 
	 * @param status
	 *            is the status of the order : NEW/TRADING/DELETED
	 * @param id
	 *            is the order id
	 * @return returns status
	 */

	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	@Modifying(clearAutomatically = true)
	@Query("update Orders o set o.orderStatus = ?1 where o.orderId = ?2")
	int setStatusFor(String status, int id);

	/**
	 * 
	 * @param orderId
	 *            order id in the db
	 * @param symbol
	 *            symbol for the stock
	 * @param orderType
	 *            type of order
	 * @param traderName
	 *            is trader name
	 * @param orderSide
	 *            is the side (Buy/Sell)
	 * @param orderValidity
	 *            is the validity of order (GTC/GTD)
	 * @param qty
	 *            is the quantity of order
	 * @param price
	 *            is market price of stock
	 * @param sprice
	 *            is stop price
	 * @param lprice
	 *            limit price
	 * @return int
	 */

	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	@Modifying(clearAutomatically = true)
	@Query("update Orders o set o.symbol = ?2 ,o.orderType = ?3, o.tName=?4, o.orderSide=?5, o.orderValidity=?6, o.qty=?7, o.price=?8,o.sprice=?9,o.lprice=?10   where o.orderId = ?1") // ,
																																														// o.tName=?4,o.orderSide=?5,o.orderValidity=?6,
																																														// o.qty=?7,o.price=?8,o.sprice=?9,o.lprice=?10
																																														// where
																																														// o.orderId
						/**
						   @param orderId
	 *            order id in the db
	 * @param symbol
	 *            symbol for the stock
	 * @param orderType
	 *            type of order
	 * @param traderName
	 *            is trader name
	 * @param orderSide
	 *            is the side (Buy/Sell)
	 * @param orderValidity
	 *            is the validity of order (GTC/GTD)
	 * @param qty
	 *            is the quantity of order
	 * @param price
	 *            is market price of stock
	 * @param sprice
	 *            is stop price
	 * @param lprice
	 *            limit price
	 * @return  int
	 */
						
																																														// ?1")
	int modifyOrderFor(int orderId, String symbol, String orderType, String traderName, String orderSide,
			String orderValidity, Double qty, Double price, Double sprice, Double lprice);// ,
																							// String
																							// orderType,String
																							// traderName,
																							// String
																							// orderSide,String
																							// orderValidity,
																							// Double
																							// qty,Double
																							// price,
																							// Double
																							// sprice,
																							// Double
																							// lprice);

	
	
	/**
	 * 
	 * @param orderId
	 *            order id in the db
	 *
	 * @param qty
	 *            is the quantity of order
	 * 
	 * @return int
	 */
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	@Modifying(clearAutomatically = true)
	@Query("update Orders o set  o.qty=?2  where o.orderId = ?1") // ,
																	// o.tName=?4,o.orderSide=?5,o.orderValidity=?6,
																	// o.qty=?7,o.price=?8,o.sprice=?9,o.lprice=?10
																	// where
																	// o.orderId
																	// = ?1")
	int modifyQtyFor(int orderId, Double qty);// , String orderType,String
												// traderName, String
												// orderSide,String
												// orderValidity, Double
												// qty,Double price, Double
												// sprice, Double lprice);

}
