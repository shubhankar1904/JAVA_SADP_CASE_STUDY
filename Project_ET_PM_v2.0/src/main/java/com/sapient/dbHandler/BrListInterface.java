package com.sapient.dbHandler;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sapient.entities.Broker;

public interface BrListInterface extends CrudRepository<Broker, Integer> {

	public List<Broker> findByBrId(int brId);
	
	

}
