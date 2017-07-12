package com.sapient.moneytree.exceptions;

public class InvalidTraderNameException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		
		return "Invalid Trader Name Provided. Please provide a valid trader name";
	}
}
