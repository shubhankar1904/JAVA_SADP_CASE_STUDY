package com.sapient.moneytree.executionTrader.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.moneytree.executionTrader.DatabaseInterfaces.AllocationDatabaseInterface;
import com.sapient.moneytree.executionTrader.domain.Allocations;

@Service
public class AllocationServiceImpl implements com.sapient.moneytree.executionTrader.service.AllocationService {

	private AllocationDatabaseInterface repository;

	@Autowired
	public void setRepository(AllocationDatabaseInterface repository) {
		this.repository = repository;
	}

	@Override
	public void saveAllocation(Allocations allocation) {
		// TODO Auto-generated method stub
		repository.save(allocation);
	}

}
