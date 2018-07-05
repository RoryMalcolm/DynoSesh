package com.dynosesh;

import com.dynosesh.actor.QueueActor;
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

  private Map<String, QueueActor> actorMap;
  private Protocol protocol;
  private int actorCount;

  /**
   * Used to ensure that communication complies to the protocol implementation.
   *
   * <p>
   * Allows for communication over a protocol and throws an error if communication does
   * not comply to the standard.
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
   * Adds and actor to the protocol's dictionary.
   *
   * @param actor The actor reference
   */
  public void addActor(QueueActor actor) {
    this.actorMap.put(String.valueOf(actorCount), actor);
    actorCount++;
  }

  /**
   * Returns the actor with the corresponding key.
   *
   * @param key The key for searching the map with
   * @return The actor object
   */
  public QueueActor getActor(String key) {
    return this.actorMap.get(key);
  }

  /**
   * Sends a message, and ensures it complies to the protocol.
   *
   * @param senderAddress The address of the actor sending the request
   * @param receiverAddress The address of the actor to send the message to
   * @param payload The message to send to an actor
   * @throws InvalidSessionException Thrown when there is a session type error
   */
  public void send(String senderAddress,
                   String receiverAddress, Sendable payload) throws InvalidSessionException {
    if (this.actorMap.size() == 0) {
      throw new IllegalArgumentException("The protocol does not currently contain actors");
    }
    if (this.protocol.checkStatusAndProgress(senderAddress, payload)) {
      try {
        this.actorMap.get(receiverAddress).sendTask(payload);
      } catch (NullPointerException e) {
        throw new IllegalArgumentException("The key did not exist");
      }
    } else {
      throw new InvalidSessionException("Attempted an invalid protocol movement");
    }
  }
}
