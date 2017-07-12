package com.sapient.moneytree.exceptions;

public class InvalidSymbolException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		
		return "Invalid Symbol Provided. Provide a valid symbol";
	}
}
