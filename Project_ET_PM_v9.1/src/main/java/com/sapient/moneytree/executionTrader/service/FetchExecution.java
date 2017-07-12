package com.sapient.moneytree.executionTrader.service;

import com.sapient.moneytree.executionTrader.domain.Block;
import com.sapient.moneytree.executionTrader.domain.Execution;

/**
 * 
 * @author aaga50
 *
 */
public interface FetchExecution {

	public Execution saveExecution(Execution execution);
}
