package com.sapient.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.dbHandler.FillRepository;
import com.sapient.entities.Fill;

@Service
public class FillsServiceImpl implements FillServices {

	FillRepository repository;

	@Autowired
	public void setRepository(FillRepository repository) {
		this.repository = repository;
	}

	@Override
	public Iterable<Fill> listAllFills() {

		return repository.listAll();
	}

	@Override
	public List<Fill> filterBySymbol(String symbol) {
		return repository.filterSymbol(symbol);

	}

	@Override
	public List<Fill> filterByBoth(String side, String symbol) {
		return repository.filterBoth(side, symbol);

	}

	@Override
	public List<Fill> filterBySide(String side) {
		return repository.filterSide(side);

	}

	@Override
	public List<Fill> filterByblockID(double blockId) {

		return repository.filterblockID(blockId);
	}

	@Override
	public List<Fill> filterByexecID(long execId) {

		return repository.filterexecID(execId);
	}

	@Override
	public List<Fill> filterByBothID(long execId, double blockId) {

		return repository.filterexecID(execId);
	}

	@Override
	public void save(Fill fill) {
		repository.save(fill);
		
	}

}
