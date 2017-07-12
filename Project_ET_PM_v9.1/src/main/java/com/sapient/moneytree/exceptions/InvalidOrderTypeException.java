package com.sapient.moneytree.exceptions;

public class InvalidOrderTypeException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		
		return "Invalid Order Type Provided. Only MARKET/LIMIT allowed";
	}
	

	
}
