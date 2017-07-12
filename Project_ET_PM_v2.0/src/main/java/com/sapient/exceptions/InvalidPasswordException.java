package com.sapient.exceptions;

import org.apache.log4j.Logger;

import com.sapient.controller.FillController;

/**
 * This class throws a custom exception if password entered is null
 *
 */
@SuppressWarnings("serial")
public class InvalidPasswordException extends Exception{
	final static Logger logger = Logger.getLogger(InvalidPasswordException.class);
	/**
	 * @return error message displayed when password is null
	 */
	@Override
	public String toString() {
		logger.warn("Invalid password provided.");
		return "Invalid password provided. Null string will not be accepted";
	}
	
}
