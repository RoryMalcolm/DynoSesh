package com.dynosesh.protocol;

import com.dynosesh.Sendable;

/**
 * Created by rorymalcolm on 29/06/2018.
 *
 * @author rorymalcolm
 */
public class NodeBuilder {
  private Node node;
  private ProtocolFactory parentProtocolFactory;
  private boolean payloadAdded;

  /**
   * Non-public NodeBuilder constructor called from within the ProtocolFactory which is passed down
   * to this object through a parameter.
   *
   * @param parentProtocolFactory The ProtocolFactory which created this object
   */
  NodeBuilder(ProtocolFactory parentProtocolFactory) {
    node = new Node(null);
    this.parentProtocolFactory = parentProtocolFactory;
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
    ConnectionBuilder connectionBuilder = new ConnectionBuilder(this);
    this.node.addConnection(connectionBuilder.getConnection());
    return connectionBuilder;
  }

  /**
   * Creates a new node on the graph.
   *
   * @return The new node's NodeBuilder object
   */
  public NodeBuilder node() {
    NodeBuilder nodeBuilder = new NodeBuilder(this.parentProtocolFactory);
    this.parentProtocolFactory.addNode(nodeBuilder.getNode());
    return nodeBuilder;
  }
}
