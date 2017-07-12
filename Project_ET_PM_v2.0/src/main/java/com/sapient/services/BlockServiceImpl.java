package com.sapient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.dbHandler.BlockRepository;
import com.sapient.moneytree.executionTrader.domain.Block;
@Service
public class BlockServiceImpl implements BlockService{

	private BlockRepository repository;

	
	

    @Autowired
	public void setRepository(BlockRepository repository) {
		this.repository = repository;
	}


	@Override
	public void saveBlock(Block block) {
		// TODO Auto-generated method stub
		repository.save(block);
	}

}
