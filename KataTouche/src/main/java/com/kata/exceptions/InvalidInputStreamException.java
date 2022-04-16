package com.kata.exceptions;

/** InvalidInputStreamException.class. */
public class InvalidInputStreamException extends RuntimeException {
  /** id. */
  private static final long serialVersionUID = 7718828512143293558L;
  
  /**
  * Something happened while trying to load a property file.
  */
  public InvalidInputStreamException(String message) {
    super(message);
  }
}
