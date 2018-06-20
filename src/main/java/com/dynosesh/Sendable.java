package com.dynosesh;

/**
 * Created by Rory Malcolm on 19/06/2018.
 */
public abstract class Sendable<T> {

  private T payload;

  /**
   * Used to facilitate communication over a protocol.
   * <p>
   *   Contains a payload, the type of which is checked to ensure it complies to the protocol
   *   implementation.
   * </p>
   * @param payload The payload of the message
   */
  public Sendable(T payload) {
    this.payload = payload;
  }

  /**
   * Returns the payload of the message that was sent over the protocol.
   * @return The message payload
   */
  public T getPayload() {
    return payload;
  }
}
