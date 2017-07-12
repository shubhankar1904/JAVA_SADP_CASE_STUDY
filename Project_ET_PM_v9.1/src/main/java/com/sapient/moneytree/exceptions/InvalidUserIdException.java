package com.sapient.moneytree.exceptions;

public class InvalidUserIdException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		
		return "Invalid UserId provided. Only Integer accepted";
	}
	
}
