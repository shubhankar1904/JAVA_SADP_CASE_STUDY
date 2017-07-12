package com.sapient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.dbHandler.BrListInterface;
import com.sapient.entities.Broker;

@Service
public class BrImpl implements BrService {

	private BrListInterface brRep;

	@Autowired
	public void setBrRepository(BrListInterface brRep) {
		this.brRep = brRep;
	}

	


	@Override
	public Broker getByBrId(Integer integer) {
		// TODO Auto-generated method stub
		return brRep.findOne(integer);
	}




	
}
