package com.kata.exceptions;

/** InvalidDateException class. */
public class InvalidDateException extends RuntimeException {
	
	/** id. */
	private static final long serialVersionUID = 7718828512143293558L;
	
	/**
	 * Something happened while trying process input date
	 */
	public InvalidDateException(String message) {
		super(message);
	}

}
