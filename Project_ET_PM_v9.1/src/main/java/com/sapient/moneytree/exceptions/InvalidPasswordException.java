package com.sapient.moneytree.exceptions;

public class InvalidPasswordException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		
		return "Invalid password provided. Null string will not be accepted";
	}
	
}
