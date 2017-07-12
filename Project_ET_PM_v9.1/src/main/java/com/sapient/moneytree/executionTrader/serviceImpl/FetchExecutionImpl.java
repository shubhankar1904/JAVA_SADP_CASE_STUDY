package com.sapient.moneytree.executionTrader.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.moneytree.executionTrader.DatabaseInterfaces.ExecutionDatabaseInterface;
import com.sapient.moneytree.executionTrader.domain.Execution;
import com.sapient.moneytree.executionTrader.service.FetchExecution;

@Service
public class FetchExecutionImpl implements FetchExecution {

	private ExecutionDatabaseInterface exDb;

	/**
	 * 
	 * @param exDb
	 */
	@Autowired
	public void setExDb(ExecutionDatabaseInterface exDb) {
		this.exDb = exDb;
	}

	/**
	 * 
	 */
	@Override
	public Execution saveExecution(Execution execution) {
		return exDb.save(execution);
	}

}
