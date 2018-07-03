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
   * Gets a node within the graph.
   *
   * @return The node within the graph
   */
  Node getNode(String key) {
    return this.nodes.get(Integer.parseInt(key));
  }

  /**
   * Return the nodes within the graph
   *
   * @return The nodes within the graph
   */
  ArrayList<Node> getNodes() {
    return nodes;
  }

  /**
   * Returns an ArrayList of Connection objects that are connected to the inputted Node.
   *
   * @param currentNode The node which connections need checked against
   * @return An ArrayList of all the connections
   */
  ArrayList<Connection> getConnections(Node currentNode) {
    return currentNode.getConnections();
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
