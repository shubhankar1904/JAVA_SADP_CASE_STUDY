package com.sapient.services;

import java.util.List;

import com.sapient.entities.Fill;

public interface FillServices {
	public Iterable<Fill> listAllFills();

	public List<Fill> filterBySymbol(String symbol);

	public List<Fill> filterByBoth(String side, String symbol);

	public List<Fill> filterBySide(String side);

	public  List<Fill> filterByblockID(double blockId);

	public   List<Fill>	filterByexecID(long execId);

	public  List<Fill> filterByBothID(long execId, double blockId);

	public void save(Fill fill);

}
