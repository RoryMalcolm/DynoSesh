package com.dynosesh.protocol;

import com.dynosesh.Sendable;

import java.util.ArrayList;

/**
 * Created by Rory Malcolm on 21/06/2018.
 */
public class Node {
  private ArrayList<Connection> connections;
  private Class<? extends Sendable> value;
  private boolean start = false;

  public Node(Sendable value, boolean start) {
    this.value = value.getClass();
    this.connections = new ArrayList<>();
    this.start = start;
  }

  public Node(Sendable value, ArrayList<Connection> connections, boolean start) {
    this.value = value.getClass();
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
