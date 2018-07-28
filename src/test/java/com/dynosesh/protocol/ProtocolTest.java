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

  private Protocol protocol;

  @BeforeEach
  void setUp() {
    ProtocolBuilder protocolBuilder = new ProtocolBuilder();
    Node startNode = new Node(null);
    Node mediumNode = new Node(TestLayer.class);
    Node finishNode = new Node(TestLayer.class);
    startNode.addConnection(new Connection("1", "0", "1"));
    mediumNode.addConnection(new Connection("1",
        "0", "1"));
    protocolBuilder.addNode(startNode);
    protocolBuilder.addNode(mediumNode);
    protocolBuilder.addNode(finishNode);
    protocol = protocolBuilder.build();
  }

  @Test
  void testLayerInstanceOf() {
    TestLayer testLayer = new TestLayer("Hello, world!");
    assertTrue(protocol.checkStatusAndProgress("1", "0",
        testLayer));
    assertTrue(protocol.checkStatusAndProgress("1", "0",
        testLayer));
    assertFalse(protocol.checkStatusAndProgress("0", "1",
        testLayer));
  }

  class TestLayer extends Sendable {

    TestLayer(String payload) {
      super(payload);
    }
  }
}