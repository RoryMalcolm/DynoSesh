package com.dynosesh.protocol;

import java.util.Objects;

/**
 * Created by rorymalcolm on 29/06/2018.
 *
 * @author rorymalcolm
 */
public class ConnectionBuilder {
  private Connection connection;
  private NodeBuilder nodeBuilder;

  /**
   * Non-public ConnectionBuilder constructor, this is ONLY called from the NodeBuilder object.
   * <p>
   * The NodeBuilder object passes an instance of itself down when .connection() is called on a
   * node that has been declared with the internal DSL.
   * </p>
   *
   * @param nodeBuilder The parent NodeBuilder object
   */
  ConnectionBuilder(NodeBuilder nodeBuilder) {
    connection = new Connection();
    this.nodeBuilder = nodeBuilder;
  }

  /**
   * Returns the value of the built connection.
   *
   * @return The connection
   */
  Connection getConnection() {
    return connection;
  }

  /**
   * Defines the address the connection is going to
   *
   * @param toActorAddress The actor the message is going to
   * @return The current object for chaining methods
   */
  public ConnectionBuilder toActor(String toActorAddress) {
    this.connection.setToActorAddress(toActorAddress);
    return this;
  }

  /**
   * Defines the address the connection is coming from
   *
   * @param fromActorAddress The actor the message is coming from
   * @return The current object for chaining methods
   */
  public ConnectionBuilder fromActor(String fromActorAddress) {
    this.connection.setFromActorAddress(fromActorAddress);
    return this;
  }
  /**
   * The finalisation step of the connection aspect of the DSL - returns the parent nodebuilder
   * after setting the node that it is connecting to.
   *
   * @param nodeKey The node to connect to
   * @return The parent nodebuilder
   */
  public NodeBuilder to(String nodeKey) {
    this.connection.setNodeAddress(nodeKey);
    if (Objects.equals(this.connection.getToActorAddress(), "")) {
      throw new IllegalArgumentException("No toActor address defined");
    }
    return this.nodeBuilder;
  }
}
