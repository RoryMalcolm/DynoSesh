package com.dynosesh.protocol;

import com.dynosesh.Sendable;

/**
 * Created by rorymalcolm on 29/06/2018.
 *
 * @author rorymalcolm
 */
public class NodeBuilder {

  private Node node;
  private ProtocolBuilder parentProtocolBuilder;
  private boolean payloadAdded;

  /**
   * Non-public NodeBuilder constructor called from within the ProtocolBuilder which is passed down
   * to this object through a parameter.
   *
   * @param parentProtocolBuilder The ProtocolBuilder which created this object
   */
  NodeBuilder(ProtocolBuilder parentProtocolBuilder) {
    node = new Node(null);
    this.parentProtocolBuilder = parentProtocolBuilder;
    payloadAdded = false;
  }

  /**
   * Returns the value of the internal node.
   *
   * @return The internal node
   */
  public Node getNode() {
    return node;
  }

  /**
   * Adds a payload to the internal node.
   *
   * @param payload The payload object
   * @return The instance of nodebuilder for further operations
   */
  public NodeBuilder payload(Class<? extends Sendable> payload) {
    if (payloadAdded) {
      throw new IllegalStateException("Cannot have two payloads!");
    }
    payloadAdded = true;
    node.addValue(payload);
    return this;
  }

  /**
   * Adds a connection to the node.
   *
   * @return The connection builder object
   */
  public ConnectionBuilder connection() {
    if (!payloadAdded) {
      throw new IllegalArgumentException("No payload on node");
    }
    ConnectionBuilder connectionBuilder = new ConnectionBuilder(parentProtocolBuilder, this);
    this.node.addConnection(connectionBuilder.getConnection());
    return connectionBuilder;
  }

  /**
   * Creates a new node on the graph.
   *
   * @return The new node's NodeBuilder object
   */
  public NodeBuilder node() {
    NodeBuilder nodeBuilder = new NodeBuilder(this.parentProtocolBuilder);
    this.parentProtocolBuilder.addNode(nodeBuilder.getNode());
    return nodeBuilder;
  }

  /**
   * Returns the completed protocol object if it is valid, throws an IllegalStateException if not
   *
   * @return The protocol object
   */
  public Protocol build() {
    return this.parentProtocolBuilder.build();
  }
}
