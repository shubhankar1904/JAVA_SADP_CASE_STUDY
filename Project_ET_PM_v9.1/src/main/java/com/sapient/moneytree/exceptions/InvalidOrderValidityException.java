package com.sapient.moneytree.exceptions;

public class InvalidOrderValidityException extends Exception{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
	public String toString() {
		
		return "Invalid Order Validity Provided. Only GTD/GTC allowed";
	}
	
}
