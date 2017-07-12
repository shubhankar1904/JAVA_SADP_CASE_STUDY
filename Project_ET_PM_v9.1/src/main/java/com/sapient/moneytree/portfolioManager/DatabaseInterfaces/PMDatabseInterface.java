package com.sapient.moneytree.portfolioManager.DatabaseInterfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.moneytree.portfolioManager.domain.PortfolioManager;

/**
 * 
 * @author Neha Maheshwari
 *
 */
public interface PMDatabseInterface extends CrudRepository<PortfolioManager, Integer> {
	/**
	 * 
	 * @param pmId
	 *            is portfolio manager's id
	 * @return list
	 */

	public List<PortfolioManager> findByPMId(int pMId);

	/**
	 * 
	 * @param role
	 *            is role for a user
	 * @return
	 */
	public List<PortfolioManager> findByRole(int role);

	/**
	 * 
	 * @param id
	 *            is portfolio manager's id
	 * @param password
	 * 
	 * 
	 *            is the password
	 * @return int
	 */

	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	@Modifying(clearAutomatically = true)
	@Query("update PortfolioManager u set u.password = ?2 where u.pMId = ?1")
	/**
	 * 
	 * 
	 * @param id
	 *            is user id
	 * @param password
	 *            is the password
	 * @return int
	 */
	int setPasswordFor(int id, String password);

}
