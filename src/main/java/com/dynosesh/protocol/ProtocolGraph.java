package com.dynosesh.protocol;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rory Malcolm on 21/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class ProtocolGraph {

  private HashMap<String, Node> nodes;
  private Node startNode;
  private int id = 0;

  /**
   * The data structure used to contain the inner graph object.
   */
  ProtocolGraph() {
    this.nodes = new HashMap<>();
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
    this.nodes.put(String.valueOf(id), node);
    id++;
  }

  /**
   * Gets a node within the graph.
   *
   * @return The node within the graph
   */
  Node getNode(String key) {
    return this.nodes.get(key);
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
