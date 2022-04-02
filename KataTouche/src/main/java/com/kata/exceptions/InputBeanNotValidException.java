package com.kata.exceptions;

/** InputBeanNotValidException class.  */
public class InputBeanNotValidException extends RuntimeException {
	
	/** id. */
	private static final long serialVersionUID = 7718828512143293558L;
	
	/**
	 * Something happened while trying process listener
	 */
	public InputBeanNotValidException(String message) {
		super(message);
	}

}
