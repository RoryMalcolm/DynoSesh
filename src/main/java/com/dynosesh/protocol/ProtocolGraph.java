package com.dynosesh.protocol;

import java.util.ArrayList;

/**
 * Created by Rory Malcolm on 21/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class ProtocolGraph {

  static private ArrayList<Node> nodes;
  private Node startNode;

  /**
   * The data structure used toActor contain the inner graph object.
   */
  ProtocolGraph() {
    nodes = new ArrayList<>();
  }

  /**
   * Gets a node within the graph.
   *
   * @return The node within the graph
   */
  static Node getNode(String key) {
    return nodes.get(Integer.parseInt(key));
  }

  static String getNodeAddress(Node node) {
    return String.valueOf(nodes.indexOf(node));
  }

  /**
   * Adds a node toActor the graph.
   *
   * @param node The node for adding toActor the graph
   */
  void addNode(Node node) {
    if (node.isStart()) {
      startNode = node;
    }
    nodes.add(node);
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
   * Returns an ArrayList of Connection objects that are connected toActor the inputted Node.
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
