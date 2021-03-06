package com.dynosesh.protocol;

import static java.util.stream.Collectors.toList;

/**
 * Created by Rory Malcolm on 22/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class ProtocolBuilder {

  private Protocol protocol;
  private boolean hasStart = false;

  /**
   * Used to build a valid protocol graph.
   * <p>
   * Ensures that the protocol graph has a starting addNode somewhere within it, will except if this
   * is not the case
   * </p>
   */
  public ProtocolBuilder() {
    protocol = new Protocol();
  }

  /**
   * Adds a node to the protocol.
   *
   * @param node The node for adding
   */
  public void addNode(Node node) {
    if (node.isStart()) {
      hasStart = true;
    }
    protocol.addNode(node);
  }

  /**
   * Gets the node at the entered key
   *
   * @param key The key for the node
   * @return The node
   */
  Node getNode(String key) {
    return this.protocol.getNode(key);
  }

  /**
   * Starts node construction through the internal DSL.
   *
   * @return The nodebuilder object
   */
  public NodeBuilder node() {
    NodeBuilder nodeBuilder = new NodeBuilder(this);
    this.addNode(nodeBuilder.getNode());
    return nodeBuilder;
  }

  /**
   * Returns the completed protocol object if it is valid, throws an IllegalStateException if not
   *
   * @return The protocol object
   */
  public Protocol build() {
    //ensure there are not two start nodes within the graph
    if (this.protocol.getNodes()
        .parallelStream()
        .filter(t -> t.getValue() == null)
        .collect(toList())
        .size() > 1) {
      throw new IllegalStateException("Two start nodes within graph");
    }
    if (hasStart) {
      return protocol;
    } else {
      throw new IllegalArgumentException("The protocol does not have a starting addNode");
    }
  }
}
