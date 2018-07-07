package com.dynosesh;

import java.io.Serializable;

/**
 * Created by Rory Malcolm on 19/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public abstract class Sendable<T> implements Serializable {

  private T payload;
  private String target;

  /**
   * Used to facilitate communication over a protocol.
   * <p>
   * Contains a payload, the type of which is checked to ensure it complies to the protocol
   * implementation.
   * </p>
   *
   * @param payload The payload of the message
   */
  public Sendable(T payload) {
    this.payload = payload;
    this.target = "";
  }

  public Sendable(T payload, String target) {
    this.payload = payload;
    this.target = target;
  }

  /**
   * Returns the payload of the message that was sent over the protocol.
   *
   * @return The message payload
   */
  public T getPayload() {
    return payload;
  }

  public String getTarget() {
    return this.target;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
