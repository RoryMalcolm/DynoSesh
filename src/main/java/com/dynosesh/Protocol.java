package com.dynosesh;

import com.dynosesh.protocol.Node;
import com.dynosesh.protocol.ProtocolGraph;

import java.util.ArrayList;

/**
 * Created by Rory Malcolm on 19/06/2018.]
 * TODO: Work out how to express more complicated protocols - potentially a graph representation of
 * TODO: finite state machine?
 */
public class Protocol {
  private ProtocolGraph protocol;
  private Node currentNode;
  /**
   * The default constructor for the Protocol class.
   * <p>
   *   Protocol will eventually move to using a graph representation of a finite state machine
   *
   *   Currently however it uses a stack to represent "layers" of communication
   * </p>
   */
  public Protocol() {
    this.protocol = new ProtocolGraph();
    this.currentNode = protocol.getStartNode();
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
  public Protocol(ProtocolGraph protocol) {
    this.protocol = protocol;
    this.currentNode = protocol.getStartNode();
  }

  /**
   * Adds a layer to the protocol.
   * @param forAdding The layer for adding.
   */
  public void addNode(Node forAdding) {
    this.protocol.addNode(forAdding);
  }

  public boolean canProgress(Sendable payload) {
    for (Node node : getConnectingNodes()) {
      if (node.getValue() == payload.getPayload().getClass()) {
        currentNode = node;
        return true;
      }
    }
    return false;
  }

  private ArrayList<Node> getConnectingNodes() {
    return this.protocol.getConnections(currentNode);
  }
}
