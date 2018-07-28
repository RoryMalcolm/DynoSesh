package com.dynosesh;

import com.dynosesh.actor.Actor;
import com.dynosesh.exceptions.InvalidSessionException;
import com.dynosesh.protocol.Protocol;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rory Malcolm on 19/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class ProtocolMonitor {

  private Map<String, Actor> actorMap;
  private Protocol protocol;
  private int actorCount;

  /**
   * Used to ensure that communication complies to the protocol implementation.
   *
   * <p>
   * Allows for communication over a protocol and throws an error if communication does not comply
   * to the standard.
   * </p>
   *
   * @param protocol The protocol that will be checked against
   */
  public ProtocolMonitor(Protocol protocol) {
    this.actorCount = 0;
    this.protocol = protocol;
    this.actorMap = new HashMap<>();
  }

  /**
   * Adds and toActor to the protocol's dictionary.
   *
   * @param actor The toActor reference
   */
  public void addActor(Actor actor) {
    this.actorMap.put(String.valueOf(actorCount), actor);
    actorCount++;
  }

  /**
   * Returns the toActor with the corresponding key.
   *
   * @param key The key for searching the map with
   * @return The toActor object
   */
  public Actor getActor(String key) {
    return this.actorMap.get(key);
  }

  /**
   * Sends a message, and ensures it complies to the protocol.
   *
   * @param receiverAddress The address of the toActor to send the message to
   * @param senderAddress The address of the toActor sending the request
   * @param payload The message to send to an toActor
   * @throws InvalidSessionException Thrown when there is a session type error
   */
  public void send(String receiverAddress, String senderAddress,
      Sendable payload) throws InvalidSessionException {
    if (this.actorMap.size() == 0) {
      throw new IllegalArgumentException("The protocol does not currently contain actors");
    }
    if (this.protocol.checkStatusAndProgress(receiverAddress, senderAddress, payload)) {
      try {
        this.actorMap.get(receiverAddress).sendTask(payload);
      } catch (NullPointerException e) {
        throw new IllegalArgumentException("The key did not exist");
      }
    } else {
      throw new InvalidSessionException("Attempted an invalid protocol movement");
    }
  }

  /**
   * Returns true if the current node has no onward connections.
   *
   * @return True if the node is a terminus node
   */
  public boolean isTerminus() {
    return this.protocol.isTerminus();
  }
}
