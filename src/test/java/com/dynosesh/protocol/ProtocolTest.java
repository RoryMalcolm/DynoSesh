package com.dynosesh.protocol;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dynosesh.Sendable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Rory Malcolm on 19/06/2018.
 */
class ProtocolTest {

  @BeforeEach
  void setUp() {
    ProtocolFactory protocolFactory = new ProtocolFactory();
    Node startNode = new Node(null);
    Node mediumNode = new Node(TestLayer.class);
    Node finishNode = new Node(TestLayer.class);
    startNode.addConnection(new Connection("1", "1"));
    mediumNode.addConnection(new Connection("1", "2"));
    protocolFactory.addNode(startNode);
    protocolFactory.addNode(mediumNode);
    protocolFactory.addNode(finishNode);
    protocol = protocolFactory.build();
  }

  private Protocol protocol;

  @Test
  void testLayerInstanceOf() {
    TestLayer testLayer = new TestLayer("Hello, world!");
    assertTrue(protocol.checkStatusAndProgress("1",
        testLayer));
    assertTrue(protocol.checkStatusAndProgress("1",
        testLayer));
    assertFalse(protocol.checkStatusAndProgress("1",
        testLayer));
  }

  class TestLayer extends Sendable {

    TestLayer(String payload) {
      super(payload);
    }
  }
}