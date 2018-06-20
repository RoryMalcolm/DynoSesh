package com.dynosesh.exceptions;

/**
 * Created by Rory Malcolm on 19/06/2018.
 */
public class InvalidSessionException extends Exception {
  /**
   * Thrown when protocol communication does not match the expected standard.
   *
   * <p>
   *   When a session type error occurs, this exception is used to ensure that the system
   *   cannot progress further.
   * </p>
   */
  public InvalidSessionException() {
    super();
  }
}
