package com.sapient.moneytree.executionTrader.DatabaseInterfaces;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sapient.moneytree.executionTrader.domain.Execution;

/**
 * 
 * @author aaga50
 *
 */
public interface ExecutionDatabaseInterface extends CrudRepository<Execution, Long> {

	
	
}
