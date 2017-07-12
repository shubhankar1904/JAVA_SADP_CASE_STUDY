package com.sapient.services;

import com.sapient.entities.Security;

public interface SecurityService {

	public Iterable<Security> listAllSecurities();

	public void UpdateSecurities(Double _4, Integer _5, Double _6, Double _7, String _8);

	public Security saveSecurity(Security sec);

	public Security filterBySymbol(String symbol);
	public void UpdateLastPrice(double lastPrice,String Symbol);
}
