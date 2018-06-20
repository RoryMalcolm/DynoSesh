package com.dynosesh;

import com.dynosesh.exceptions.InvalidSessionException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rory Malcolm on 19/06/2018.
 */
public class ProtocolMonitor {

  private Map<String, Actor> actorMap;
  private Protocol protocol;

  /**
   * Used to ensure that communication complies to the protocol implementation.
   *
   * <p>
   *   Allows for communication over a protocol and throws an error if communication does
   *   not comply to the standard.
   * </p>
   * @param protocol The protocol that will be checked against
   */
  public ProtocolMonitor(Protocol protocol) {
    this.protocol = protocol;
    this.actorMap = new HashMap<>();
  }

  /**
   * Adds and actor to the protocol's dictionary.
   * @param key The address of the new actor
   * @param actor The actor reference
   */
  public void addActor(String key, Actor actor) {
    this.actorMap.put(key, actor);
  }

  /**
   * Sends a message, and ensures it complies to the protocol.
   * @param address The address of the actor to send the message to
   * @param payload The message to send to an actor
   * @throws InvalidSessionException Thrown when there is a session type error
   */
  public void send(String address, Sendable payload) throws InvalidSessionException {
    if (protocol.getLayer() == payload.getClass()) {
      this.actorMap.get(address).addTask(payload);
    } else {
      throw new InvalidSessionException();
    }
  }
}
