package com.dynosesh.protocol;

import com.dynosesh.Sendable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Rory Malcolm on 25/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class NodeTest {
  private Node startNode;
  private Node nonStartNode;

  @Before
  public void setUp() {
    startNode = new Node(null);
    nonStartNode = new Node(TestClass.class);
  }

  @Test
  public void addConnection() {
    startNode.addConnection(new Connection("1", nonStartNode));
    assertEquals(startNode.getConnections().get(0).getNode(), nonStartNode);
    boolean thrown = false;
    try {
      startNode.addConnection(new Connection("1", nonStartNode));
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }

  @Test
  public void getConnections() {
    assertEquals(0, startNode.getConnections().size());
    startNode.addConnection(new Connection("1", nonStartNode));
    assertEquals(1, startNode.getConnections().size());
  }

  @Test
  public void getValue() {
    assertEquals(TestClass.class, nonStartNode.getValue());
  }

  @Test
  public void isStart() {
    assertTrue(startNode.isStart());
    assertFalse(nonStartNode.isStart());
  }

  @Test
  public void equals() {
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