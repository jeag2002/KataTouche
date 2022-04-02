package com.kata.exceptions;

/** BusinessListenerException.class */
public class BusinessListenerException extends RuntimeException {
	
	/** id. */
	private static final long serialVersionUID = 7718828512143293558L;
	
	/**
	 * Something happened while trying process listener
	 */
	public BusinessListenerException(String message) {
		super(message);
	}

}
