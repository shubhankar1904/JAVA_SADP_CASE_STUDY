package com.sapient.moneytree.portfolioManager.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.moneytree.portfolioManager.DatabaseInterfaces.PMDatabseInterface;
import com.sapient.moneytree.portfolioManager.domain.PortfolioManager;
import com.sapient.moneytree.portfolioManager.service.PMService;
/**
 * Implements PMService interface
 * @author Shubhankar Mayank
 *
 */
@Service
public class PMImpl implements PMService {

	private PMDatabseInterface pmRepository;//object of PMdatabaseInterface
	/**
	 * 
	 * @param pmRepository object of PMdatabaseInterface
	 */

	@Autowired
	public void setPMRepository(PMDatabseInterface pmRepository) {
		this.pmRepository = pmRepository;
	}
/**
 * Overridden method
 * finds Portfolio Manager by PM id from the db
 */
	@Override
	public PortfolioManager getPMById(Integer pMId) {
		return pmRepository.findOne(pMId);
	}
/**
 * Overridden method
 * saves Portfolio Manager into the db
 */
	@Override
	public PortfolioManager savePM(PortfolioManager portfolio_manager) {
		return pmRepository.save(portfolio_manager);
	}
/**
 * overriden method
 *  deletes Portfolio Manager's id from the db
 */
	@Override
	public void deletePortfolioManager(Integer id) {
		pmRepository.delete(id);
	}
/**
 *  Overridden method
 * finds Portfolio Manager by PM role from the db
 */
	@Override
	public List<PortfolioManager> findByRole(int role) {
		return pmRepository.findByRole(role);
	}

}
