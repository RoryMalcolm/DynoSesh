package com.dynosesh.protocol;

/**
 * Created by Rory Malcolm on 21/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class Connection {

  private String nodeAddress;
  private String toActorAddress;
  private String fromActorAddress;

  /**
   * Used to represent connections between nodes and restrict access to those that validly can move
   * between them.
   * <p>
   * In effect a connection is a link between two nodes such as: A -> B However with a protocol this
   * connection is only allowed to be traversed by valid actors this class helps to restrict this An
   * example of a Connection object that only allows for the toActor with the toActorAddress "1" to
   * proceed is as follows: A - (1) -> B
   * </p>
   *
   * @param toActorAddress The toActorAddress of the valid traversing toActor
   * @param nodeAddress The addNode the connection is pointing to
   */
  public Connection(String toActorAddress, String nodeAddress) {
    this.toActorAddress = toActorAddress;
    this.nodeAddress = nodeAddress;
  }

  /**
   * Used to represent connections between nodes and restrict access to those that validly can move
   * between them.
   * <p>
   * In effect a connection is a link between two nodes such as: A -> B However with a protocol this
   * connection is only allowed to be traversed by valid actors this class helps to restrict this An
   * example of a Connection object that only allows for the toActor with the toActorAddress "1" to
   * proceed is as follows: A - (1) -> B
   * </p>
   *
   * @param toActorAddress The toActorAddress of the valid traversing toActor
   * @param nodeAddress The addNode the connection is pointing to
   * @param fromActorAddress The actor address the connection is coming from
   */
  public Connection(String toActorAddress, String fromActorAddress, String nodeAddress) {
    this.toActorAddress = toActorAddress;
    this.nodeAddress = nodeAddress;
    this.fromActorAddress = fromActorAddress;
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
  public void setNodeAddress(String nodeAddress) {
    this.nodeAddress = nodeAddress;
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
   * Gets the toActorAddress of the toActor that can validly traverse the connection.
   *
   * @return The toActorAddress of the toActor
   */
  public String getToActorAddress() {
    return toActorAddress;
  }

  /**
   * Sets the toActorAddress of a blank connection - mostly used in the DSL.
   *
   * @param toActorAddress The toActorAddress in the connection
   */
  public void setToActorAddress(String toActorAddress) {
    this.toActorAddress = toActorAddress;
  }

  /**
   * Gets the toActorAddress of the toActor that can validly traverse the connection.
   *
   * @return The toActorAddress of the toActor
   */
  public String getFromActorAddress() {
    return fromActorAddress;
  }

  /**
   * Sets the toActorAddress of a blank connection - mostly used in the DSL.
   *
   * @param fromActorAddress The toActorAddress in the connection
   */
  public void setFromActorAddress(String fromActorAddress) {
    this.fromActorAddress = fromActorAddress;
  }


  /**
   * Returns true if the sender can make the connection.
   *
   * @param sender The sender's toActorAddress
   * @return True if the sender can make the connection
   */
  public boolean hasPermission(String sender) {
    return toActorAddress.equals(sender);
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
      return this.toActorAddress.equals(parsed.toActorAddress) && this.nodeAddress
          .equals(parsed.nodeAddress);
    } catch (Exception e) {
      return false;
    }
  }
}
