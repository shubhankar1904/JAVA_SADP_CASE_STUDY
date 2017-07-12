package com.sapient.moneytree.controller.exceptions;

import javax.jms.JMSException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 * @author aaga50
 *
 */
@ControllerAdvice
public class GlobalExceptions {

	/**
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = NullPointerException.class)
	public String HandleNullPointerexception(Exception e) {

		System.out.println("NullPointer Exception" + e);
		return "NullPointerpage";

	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = JMSException.class)
	public String HandleJmsException(Exception e) {
		System.out.println("Jms Exception" + e);
		return "JmsExceptionPage";
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public String Unknownexception(Exception e) {

		System.out.println("Unknown Exception" + e);
		return "UnknownException";

	}

}
