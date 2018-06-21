package com.dynosesh.protocol;

/**
 * Created by Rory Malcolm on 21/06/2018.
 */
public class Connection {
  private Node node;
  private String address;

  public Connection(String address, Node node) {
    this.address = address;
    this.node = node;
  }

  public Node getNode() {
    return node;
  }

  public String getAddress() {
    return address;
  }

  public boolean hasPermission(String sender) {
    return address.equals(sender);
  }
}
