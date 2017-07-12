package com.sapient.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.moneytree.executionTrader.domain.Block;

public interface BlockService{

	public void saveBlock(Block block);
	
	
}
