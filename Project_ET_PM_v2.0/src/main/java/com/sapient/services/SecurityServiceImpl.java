package com.sapient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.dbHandler.SecurityRepository;
import com.sapient.entities.Security;

@Service
public class SecurityServiceImpl implements SecurityService {

	SecurityRepository repository;

	@Autowired
	public void setRepository(SecurityRepository repository) {
		this.repository = repository;
	}

	@Override
	public Iterable<Security> listAllSecurities() {

		return repository.listAll();
	}

	@Override
	public void UpdateSecurities(Double _4, Integer _5, Double _6, Double _7, String _8) {
		repository.UpdateSecurity(_4, _5, _6, _7, _8);

	}

	@Override
	public Security saveSecurity(Security sec) {
		// TODO Auto-generated method stub
		return repository.save(sec);
	}

	@Override
	public Security filterBySymbol(String symbol) {
		// TODO Auto-generated method stub
		return repository.filterSymbol(symbol);
	}

	@Override
	public void UpdateLastPrice(double lastPrice, String Symbol) {
		repository.UpdateLastPrice(lastPrice, Symbol);
		
	}

}
