package com.sapient.exceptions;

import org.apache.log4j.Logger;

import com.sapient.controller.FillController;

/**
 * This class throws a custom exception if no execution id or block id is entered
 *
 */
@SuppressWarnings("serial")
public class InvalidFilterException extends Exception {
	final static Logger logger = Logger.getLogger(InvalidFilterException.class);
/**
 * @return error message displayed when no execution id and block id entered
 */
@Override
public String toString() {
	logger.warn("No execution id and block id entered.");
		return "no execution id and block id entered";
	}
}
