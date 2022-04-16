package com.kata.exceptions;

/** InitListenerException class. */
public class InitListenerException extends RuntimeException {

  /** id. */
  private static final long serialVersionUID = 7718828512143293558L;
  
  /**
  * Something happened while trying initialize listener.
  */
  public InitListenerException(String message) {
    super(message);
  }

}
