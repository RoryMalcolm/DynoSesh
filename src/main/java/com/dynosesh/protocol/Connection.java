package com.dynosesh.protocol;

/**
 * Created by Rory Malcolm on 21/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class Connection {

  private String nodeAddress;
  private String actorAddress;

  /**
   * Used to represent connections between nodes and restrict access to those that validly can move
   * between them.
   * <p>
   * In effect a connection is a link between two nodes such as:
   * A -> B
   * However with a protocol this connection is only allowed to be traversed by valid actors
   * this class helps to restrict this
   * An example of a Connection object that only allows for the actor with the actorAddress "1"
   * to proceed is as follows:
   * A - (1) -> B
   * </p>
   *
   * @param actorAddress The actorAddress of the valid traversing actor
   * @param nodeAddress    The addNode the connection is pointing to
   */
  public Connection(String actorAddress, String nodeAddress) {
    this.actorAddress = actorAddress;
    this.nodeAddress = nodeAddress;
  }

  /**
   * Used to create a blank connection in the internal DSL.
   */
  public Connection() {
  }

  /**
   * Sets the nodeAddress of a blank connection - mostly used in the DSL.
   *
   * @param nodeAddress The nodeAddress for the connection
   */
  public void setNodeAddress(Node nodeAddress) {
    this.nodeAddress = ProtocolGraph.getNodeAddress(nodeAddress);
  }

  /**
   * Returns the nodeAddress the connection points towards.
   *
   * @return The nodeAddress at the end of the connection
   */
  public Node getNode() {
    return ProtocolGraph.getNode(nodeAddress);
  }

  /**
   * Gets the actorAddress of the actor that can validly traverse the connection.
   *
   * @return The actorAddress of the actor
   */
  public String getActorAddress() {
    return actorAddress;
  }

  /**
   * Sets the actorAddress of a blank connection - mostly used in the DSL.
   *
   * @param actorAddress The actorAddress in the connection
   */
  public void setActorAddress(String actorAddress) {
    this.actorAddress = actorAddress;
  }

  /**
   * Returns true if the sender can make the connection.
   *
   * @param sender The sender's actorAddress
   * @return True if the sender can make the connection
   */
  public boolean hasPermission(String sender) {
    return actorAddress.equals(sender);
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
      return this.actorAddress.equals(parsed.actorAddress) && this.nodeAddress
          .equals(parsed.nodeAddress);
    } catch (Exception e) {
      return false;
    }
  }
}
