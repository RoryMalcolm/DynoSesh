package com.dynosesh.protocol;

/**
 * Created by Rory Malcolm on 22/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class ProtocolFactory {
  private Protocol protocol;
  private boolean hasStart = false;

  /**
   * Used to build a valid protocol graph.
   * <p>
   * Ensures that the protocol graph has a starting node somewhere within it, will except
   * if this is not the case
   * </p>
   */
  public ProtocolFactory() {
    protocol = new Protocol();
  }

  /**
   * Adds a node the protocol.
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
   * Returns the completed protocol object if it is valid, throws an IllegalStateException if not
   *
   * @return The protocol object
   */
  public Protocol build() {
    if (hasStart) {
      return protocol;
    } else {
      throw new IllegalStateException("The protocol does not have a starting node");
    }
  }
}
