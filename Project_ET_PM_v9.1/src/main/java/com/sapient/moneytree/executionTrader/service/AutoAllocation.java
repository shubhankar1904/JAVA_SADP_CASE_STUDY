package com.sapient.moneytree.executionTrader.service;

import com.sapient.moneytree.executionTrader.domain.Execution;

public interface AutoAllocation {

	
	//public void saveExecution(Execution e);
	
	public void autoAllocate(Execution e);
}
