package com.sapient.moneytree.exceptions;

public class InvalidOrderSideException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		
		return "Invalid Order Side Provided. Only BUY/SELL allowed";
	}
}
