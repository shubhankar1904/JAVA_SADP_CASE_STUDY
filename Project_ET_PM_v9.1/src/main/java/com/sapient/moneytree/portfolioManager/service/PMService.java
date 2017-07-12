package com.sapient.moneytree.portfolioManager.service;

import java.util.List;

import com.sapient.moneytree.portfolioManager.domain.PortfolioManager;
/**
 * interface for PM's services
 * @author Shubhankar Mayank
 *
 */
public interface PMService {
	/**
	 * 
	 * @param id is the PM id
	 * @return
	 */
	PortfolioManager getPMById(Integer id);
/**
 * 
 * @param pm is Portfolio Manager
 * @return
 */
	PortfolioManager savePM(PortfolioManager pm);
	/**
	 * 
	 * @param id is PM id
	 */

	void deletePortfolioManager(Integer id);
/**
 * 
 * @param role is the role of the PM
 * @return
 */
	public List<PortfolioManager> findByRole(int role);

}
