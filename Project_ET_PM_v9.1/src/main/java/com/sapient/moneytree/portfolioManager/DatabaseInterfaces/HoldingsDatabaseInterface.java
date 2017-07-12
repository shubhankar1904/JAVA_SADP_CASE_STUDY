package com.sapient.moneytree.portfolioManager.DatabaseInterfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.moneytree.portfolioManager.domain.Holdings;
/**
* interface for holdings 
 * @author Neha Maheshwari
*
*/

public interface HoldingsDatabaseInterface extends JpaRepository<Holdings, Long> {

	List<Holdings> findByPmId(int pmId);

}
