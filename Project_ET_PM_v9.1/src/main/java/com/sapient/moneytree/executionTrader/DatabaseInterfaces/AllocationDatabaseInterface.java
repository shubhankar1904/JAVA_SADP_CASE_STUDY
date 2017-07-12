package com.sapient.moneytree.executionTrader.DatabaseInterfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.moneytree.executionTrader.domain.Allocations;
import com.sapient.moneytree.executionTrader.domain.Execution;

/**
 * 
 * @author aaga50
 *
 */
public interface AllocationDatabaseInterface extends JpaRepository<Allocations, Long> {

	

}
