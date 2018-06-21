package com.dynosesh;

import com.dynosesh.protocol.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Rory Malcolm on 19/06/2018.
 */
public class ProtocolTest {

  class TestLayer extends Sendable {
    TestLayer(String payload) {
      super(payload);
    }
  }

  private Protocol protocol = new Protocol();

  @Before
  public void setUp() {
    protocol.addNode(new Node(new TestLayer("Hello, world!"), true));
    protocol.addNode(new Node(new TestLayer("Hello, world!"), false));
  }

  @Test
  public void testLayerInstanceOf() {
    TestLayer testLayer = new TestLayer("Hello, world!");
    assertEquals(testLayer.getClass(), protocol.canProgress(testLayer));
    assertEquals(testLayer.getClass(), protocol.canProgress(testLayer));
    boolean thrown = false;
    try {
      protocol.canProgress(testLayer);
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }
}