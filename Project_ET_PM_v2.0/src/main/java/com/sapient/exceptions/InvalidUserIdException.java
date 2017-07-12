package com.sapient.exceptions;

import org.apache.log4j.Logger;

/**
 * This class throws a custom exception if userId entered is null
 *
 */
@SuppressWarnings("serial")
public class InvalidUserIdException extends Exception{
	final static Logger logger = Logger.getLogger(InvalidUserIdException.class);
	@Override
	public String toString() {
		logger.warn("Invalid UserId provided.");
		return "Invalid UserId provided.UserId cannot be null";
	}
	
}
