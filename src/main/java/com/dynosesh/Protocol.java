package com.dynosesh;

import java.util.Stack;

/**
 * Created by Rory Malcolm on 19/06/2018.]
 * TODO: Work out how to express more complicated protocols - potentially a graph representation of
 * TODO: finite state machine?
 */
public class Protocol {
  private Stack<Class<? extends Sendable>> protocol;

  /**
   * The default constructor for the Protocol class.
   * <p>
   *   Protocol will eventually move to using a graph representation of a finite state machine
   *
   *   Currently however it uses a stack to represent "layers" of communication
   * </p>
   */
  public Protocol() {
    this.protocol = new Stack<>();
  }


  /**
   * The parameterised constructor for the Protocol class.
   * <p>
   *   Protocol will eventually move to using a graph representation of a finite state machine
   *
   *   Currently however it uses a stack to represent "layers" of communication
   * </p>
   * @param protocol The protocol implementation
   */
  public Protocol(Stack<Class<? extends Sendable>> protocol) {
    this.protocol = protocol;
  }

  /**
   * Adds a layer to the protocol.
   * @param forAdding The layer for adding.
   */
  public void addLayer(Class<? extends Sendable> forAdding) {
    this.protocol.push(forAdding);
  }


  /**
   * Gets a layer on the top of the protocol.
   * @return The layer on top of the protocol
   */
  public Class<? extends Sendable> getLayer() {
    return this.protocol.pop();
  }
}
