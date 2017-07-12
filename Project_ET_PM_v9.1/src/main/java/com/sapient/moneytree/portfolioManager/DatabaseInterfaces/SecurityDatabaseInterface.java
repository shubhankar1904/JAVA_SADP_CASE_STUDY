package com.sapient.moneytree.portfolioManager.DatabaseInterfaces;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.moneytree.portfolioManager.domain.Orders;
import com.sapient.moneytree.portfolioManager.domain.PortfolioManager;
import com.sapient.moneytree.portfolioManager.domain.Securities;

/**
 * interface for securities
 * @author Neha Maheshwari
 *
 */


public interface SecurityDatabaseInterface extends JpaRepository<Securities, String>{
	/**
	 * 
	 * @param symbol is stock symbol
	 * @return Securities
	 */
	public Securities findBySymbol(String symbol);
}
