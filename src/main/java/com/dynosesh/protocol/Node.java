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

  public Node(Class<? extends Sendable> value, boolean start) {
    this.value = value;
    this.connections = new ArrayList<>();
    this.start = start;
  }

  public Node(Class<? extends Sendable> value) {
    this.value = value;
    this.connections = new ArrayList<>();
    this.start = false;
  }

  public Node(Class<? extends Sendable> value, ArrayList<Connection> connections, boolean start) {
    this.value = value;
    this.connections = connections;
    this.start = start;
  }

  public void addConnection(Connection connection) {
    this.connections.add(connection);
  }

  public ArrayList<Connection> getConnections() {
    return connections;
  }

  public Class<? extends Sendable> getValue() {
    return value;
  }

  public boolean isStart() {
    return start;
  }
}
