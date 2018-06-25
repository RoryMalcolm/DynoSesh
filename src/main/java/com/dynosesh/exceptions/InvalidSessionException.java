package com.dynosesh.exceptions;

/**
 * Created by Rory Malcolm on 19/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class InvalidSessionException extends Exception {
  /**
   * Thrown when protocol communication does not match the expected standard.
   *
   * <p>
   * When a session type error occurs, this exception is used to ensure that the system
   * cannot progress further.
   * </p>
   *
   * @param message The message to return in the exception
   */
  public InvalidSessionException(String message) {
    super(message);
  }
}
