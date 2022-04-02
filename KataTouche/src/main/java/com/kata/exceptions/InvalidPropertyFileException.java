package com.kata.exceptions;

/** InvalidPropertyFileException. */
public class InvalidPropertyFileException extends RuntimeException {
	
	/** id. */
	private static final long serialVersionUID = 7718828512143293558L;
	
	/**
	 * Something happened while trying to load a property file.
	 */
	public InvalidPropertyFileException(String message) {
		super(message);
	}
}


