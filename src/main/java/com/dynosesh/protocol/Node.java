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
   * The class used to represent nodes within the finite state machine.
   * <p>
   * All correct actions are maintained within the finite state machine represented by nodes on
   * the graph
   * </p>
   * <p>
   * If a null value is input the node will be treated as a "start node", there can only be one
   * of these per graph
   * </p>
   *
   * @param value The class value of the node within the graph, if null is a "start node"
   */
  public Node(Class<? extends Sendable> value) {
    this.value = value;
    this.connections = new ArrayList<>();
    this.start = value == null;
  }

  /**
   * Adds a connection the the Node.
   *
   * @param connection The connection to add to the node
   */
  public void addConnection(Connection connection) {
    this.connections.add(connection);
  }

  /**
   * Returns a node's connections as an ArrayList.
   *
   * @return The ArrayList of connections
   */
  public ArrayList<Connection> getConnections() {
    return connections;
  }

  /**
   * Returns the value within the node.
   *
   * @return The value within the node
   */
  public Class<? extends Sendable> getValue() {
    return value;
  }

  /**
   * Returns true if the node is a start node.
   *
   * @return True if the node is a start node
   */
  public boolean isStart() {
    return start;
  }
}
