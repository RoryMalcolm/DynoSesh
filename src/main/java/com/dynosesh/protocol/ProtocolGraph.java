package com.dynosesh.protocol;

import java.util.ArrayList;

/**
 * Created by Rory Malcolm on 21/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class ProtocolGraph {
  private ArrayList<Node> nodes;
  private Node startNode;

  /**
   * The data structure used to contain the inner graph object.
   */
  ProtocolGraph() {
    this.nodes = new ArrayList<>();
  }

  /**
   * Adds a node to the graph.
   *
   * @param node The node for adding to the graph
   */
  void addNode(Node node) {
    if (node.isStart()) {
      startNode = node;
    }
    this.nodes.add(node);
  }

  /**
   * Gets all the nodes within the graph.
   *
   * @return The nodes within the graph
   */
  public ArrayList<Node> getNodes() {
    return this.nodes;
  }

  /**
   * Returns an ArrayList of Node objects that are connected to the inputted Node.
   * <p>
   * TODO: Rework this, potentially just return the Connection objects instead of excavating
   * TODO: the Nodes, this means you are doing twice the work when the Node can just be
   * TODO: accessed through the connection when the object is returned, also the connection object
   * TODO: API methods are needed to check validity based on which actor is calling
   * </p>
   *
   * @param currentNode The node which connections need checked against
   * @return An ArrayList of all the connected nodes
   */
  ArrayList<Node> getConnections(Node currentNode) {
    ArrayList<Node> returnable = new ArrayList<>();
    for (Connection connection : nodes.get(nodes.indexOf(currentNode)).getConnections()) {
      returnable.add(connection.getNode());
    }
    return returnable;
  }

  /**
   * Returns the starting node of the graph.
   *
   * @return The starting node of the graph
   */
  Node getStartNode() {
    return startNode;
  }
}
