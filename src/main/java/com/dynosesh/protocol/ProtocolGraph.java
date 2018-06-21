package com.dynosesh.protocol;

import java.util.ArrayList;

/**
 * Created by Rory Malcolm on 21/06/2018.
 */
public class ProtocolGraph {
  private ArrayList<Node> nodes;
  private Node startNode;

  public ProtocolGraph() {
    this.nodes = new ArrayList<>();
  }

  public void addNode(Node node) {
    if (node.isStart()) {
      startNode = node;
    }
    this.nodes.add(node);
  }

  public ArrayList<Node> getNodes() {
    return this.nodes;
  }

  public ArrayList<Node>
  getConnections(Node currentNode) {
    ArrayList<Node> returnable = new ArrayList<>();
    for (Connection connection : nodes.get(nodes.indexOf(currentNode)).getConnections()) {
      returnable.add(connection.getNode());
    }
    return returnable;
  }

  public Node getStartNode() {
    return startNode;
  }
}
