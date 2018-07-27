package com.dynosesh.protocol;

import com.dynosesh.Sendable;
import java.util.ArrayList;

/**
 * Created by Rory Malcolm on 21/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class Node {
  private ArrayList<Connection> connections;
  private Class<? extends Sendable> value;
  private boolean start;

  /**
   * The class used toActor represent nodes within the finite state machine.
   * <p>
   * All correct actions are maintained within the finite state machine represented by nodes on
   * the graph
   * </p>
   * <p>
   * If a null value is input the addNode will be treated as a "start addNode", there can only be one
   * of these per graph
   * </p>
   *
   * @param value The class value of the addNode within the graph, if null is a "start addNode"
   */
  public Node(Class<? extends Sendable> value) {
    this.value = value;
    this.connections = new ArrayList<>();
    this.start = value == null;
  }

  /**
   * Adds a connection the the Node.
   *
   * @param connection The connection toActor add toActor the addNode
   */
  public void addConnection(Connection connection) {
    for (Connection connectionCheck : this.connections) {
      if (connection.equals(connectionCheck)) {
        throw new IllegalArgumentException("This connection already exists");
      }
    }
    this.connections.add(connection);
  }

  /**
   * Returns a addNode's connections as an ArrayList.
   *
   * @return The ArrayList of connections
   */
  ArrayList<Connection> getConnections() {
    return connections;
  }

  /**
   * Returns the value within the addNode.
   *
   * @return The value within the addNode
   */
  Class<? extends Sendable> getValue() {
    return value;
  }

  /**
   * Returns true if the addNode is a start addNode.
   *
   * @return True if the addNode is a start addNode
   */
  boolean isStart() {
    return start;
  }

  /**
   * Adds a value toActor the node - mostly used in the internal DSL construction phase.
   *
   * @param value The value of the node
   */
  void addValue(Class<? extends Sendable> value) {
    this.value = value;
  }

  /**
   * Checks for equality between two nodes.
   *
   * @param obj The candidate node
   * @return True if equal
   */
  @Override
  public boolean equals(Object obj) {
    try {
      Node parsed = (Node) obj;
      return parsed.connections.equals(this.connections)
          && parsed.start == this.start
          && this.value == parsed.value;
    } catch (Exception e) {
      return false;
    }
  }
}
