package com.dynosesh.protocol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dynosesh.Sendable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Rory Malcolm on 25/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
class NodeTest {

  private Node startNode;
  private Node nonStartNode;

  @BeforeEach
  void setUp() {
    startNode = new Node(null);
    nonStartNode = new Node(TestClass.class);
    ProtocolBuilder protocolBuilder = new ProtocolBuilder();
    protocolBuilder.addNode(startNode);
    protocolBuilder.addNode(nonStartNode);
    protocolBuilder.build();
  }

  @Test
  void addConnection() {
    startNode.addConnection(new Connection("0", "1", "1"));
    assertEquals(startNode.getConnections().get(0).getNode(), nonStartNode);
    boolean thrown = false;
    try {
      startNode.addConnection(new Connection("0",
          "1", "1"));
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }

  @Test
  void getConnections() {
    assertEquals(0, startNode.getConnections().size());
    startNode.addConnection(new Connection("0", "1", "1"));
    assertEquals(1, startNode.getConnections().size());
  }

  @Test
  void getValue() {
    assertEquals(TestClass.class, nonStartNode.getValue());
  }

  @Test
  void isStart() {
    assertTrue(startNode.isStart());
    assertFalse(nonStartNode.isStart());
  }

  @Test
  void equals() {
    Node testClass = new Node(TestClass.class);
    Node testClass1 = new Node(TestClass.class);
    assertEquals(testClass, testClass1);
    testClass = new Node(null);
    testClass1 = new Node(null);
    assertEquals(testClass, testClass1);
  }

  class TestClass extends Sendable {

    /**
     * Used to facilitate communication over a protocol.
     * <p>
     * Contains a payload, the type of which is checked to ensure it complies to the protocol
     * implementation.
     * </p>
     *
     * @param payload The payload of the message
     */
    public TestClass(Object payload) {
      super(payload);
    }
  }
}