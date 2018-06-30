package com.dynosesh.protocol;

/**
 * Created by Rory Malcolm on 21/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class Connection {
  private Node node;
  private String address;

  /**
   * Used to represent connections between nodes and restrict access to those that validly can move
   * between them.
   * <p>
   * In effect a connection is a link between two nodes such as:
   * A -> B
   * However with a protocol this connection is only allowed to be traversed by valid actors
   * this class helps to restrict this
   * An example of a Connection object that only allows for the actor with the address "1"
   * to proceed is as follows:
   * A - (1) -> B
   * </p>
   *
   * @param address The address of the valid traversing actor
   * @param node    The addNode the connection is pointing to
   */
  public Connection(String address, Node node) {
    this.address = address;
    this.node = node;
  }

  /**
   * Used to create a blank connection in the internal DSL.
   */
  public Connection() {
  }

  /**
   * Sets the address of a blank connection - mostly used in the DSL.
   *
   * @param address The address in the connection
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Sets the node of a blank connection - mostly used in the DSL.
   *
   * @param node The node for the connection
   */
  public void setNode(Node node) {
    this.node = node;
  }

  /**
   * Returns the node the connection points towards.
   *
   * @return The node at the end of the connection
   */
  public Node getNode() {
    return node;
  }

  /**
   * Gets the address of the actor that can validly traverse the connection.
   *
   * @return The address of the actor
   */
  public String getAddress() {
    return address;
  }

  /**
   * Returns true if the sender can make the connection.
   *
   * @param sender The sender's address
   * @return True if the sender can make the connection
   */
  public boolean hasPermission(String sender) {
    return address.equals(sender);
  }

  /**
   * Used to assess equality between connections.
   *
   * @param obj The object that is checked against
   * @return True if the objects are equal
   */
  @Override
  public boolean equals(Object obj) {
    try {
      Connection parsed = (Connection) obj;
      return this.address.equals(parsed.address) && this.node.equals(parsed.node);
    } catch (Exception e) {
      return false;
    }
  }
}
