package com.sapient.moneytree.exceptions;

public class InvalidQuantityException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		
		return "Invalid Quantity Provided. Only Integer values > 0 allowed";
	}

	@Override
	public String getMessage() {
		return "Invalid Quantity Provided. Only Integer values > 0 allowed";
	}
	
}
