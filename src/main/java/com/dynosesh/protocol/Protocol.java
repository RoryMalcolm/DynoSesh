package com.dynosesh.protocol;

import com.dynosesh.Sendable;
import sun.plugin.dom.exception.InvalidStateException;

import java.util.ArrayList;

/**
 * Created by Rory Malcolm on 19/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 * TODO: Work out how to express more complicated protocols - potentially a graph representation of
 * TODO: finite state machine?
 */
public class Protocol {
  private ProtocolGraph protocol;
  private Node currentNode;

  /**
   * The default constructor for the Protocol class invoked by the build method in ProtocolFactory
   * <p>
   * Used to traverse a graph representation of a protocol finite state machine
   * </p>
   */
  Protocol() {
    this.protocol = new ProtocolGraph();
    this.currentNode = protocol.getStartNode();
  }

  /**
   * Adds a layer to the protocol.
   *
   * @param forAdding The layer for adding.
   */
  public void addNode(Node forAdding) {
    if (forAdding.isStart() && currentNode == null) {
      currentNode = forAdding;
    } else if (forAdding.isStart() && currentNode != null) {
      throw new InvalidStateException("There cannot be two start nodes in a graph");
    }
    this.protocol.addNode(forAdding);
  }

  /**
   * Checks to see if it there is a valid state that can be moved to, and does so if possible.
   *
   * @param payload The desired next state
   * @return True if the graph traversal was possible
   */
  public boolean checkStatusAndProgress(String senderAddress, Sendable payload) {
    for (Connection connection : getConnectingNodes()) {
      if (connection.getNode().getValue() == payload.getClass()
          && connection.hasPermission(senderAddress)) {
        currentNode = connection.getNode();
        return true;
      }
    }
    return false;
  }

  private ArrayList<Connection> getConnectingNodes() {
    return this.protocol.getConnections(currentNode);
  }
}
