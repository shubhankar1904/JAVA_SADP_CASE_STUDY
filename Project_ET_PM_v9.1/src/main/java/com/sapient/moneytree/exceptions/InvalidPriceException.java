package com.sapient.moneytree.exceptions;

public class InvalidPriceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		
		return "Invalid Price Provided. Only Double values > 0 allowed";
	}

}
