package com.dynosesh;

import com.dynosesh.protocol.Connection;
import com.dynosesh.protocol.Node;
import com.dynosesh.protocol.Protocol;
import com.dynosesh.protocol.ProtocolFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
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

  private Protocol protocol;

  @Before
  public void setUp() {
    ProtocolFactory protocolFactory = new ProtocolFactory();
    Node startNode = new Node(null);
    Node mediumNode = new Node(TestLayer.class);
    Node finishNode = new Node(TestLayer.class);
    startNode.addConnection(new Connection("1", mediumNode));
    mediumNode.addConnection(new Connection("1", finishNode));
    protocolFactory.addNode(startNode);
    protocolFactory.addNode(mediumNode);
    protocolFactory.addNode(finishNode);
    this.protocol = protocolFactory.build();
  }

  @Test
  public void testLayerInstanceOf() {
    TestLayer testLayer = new TestLayer("Hello, world!");
    assertTrue(protocol.checkStatusAndProgress("1",
        testLayer));
    assertTrue(protocol.checkStatusAndProgress("1",
        testLayer));
    assertFalse(protocol.checkStatusAndProgress("1",
        testLayer));
  }
}