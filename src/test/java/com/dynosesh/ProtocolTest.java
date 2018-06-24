package com.dynosesh;

import com.dynosesh.protocol.Connection;
import com.dynosesh.protocol.Node;
import com.dynosesh.protocol.Protocol;
import com.dynosesh.protocol.ProtocolFactory;
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

  private Protocol protocol;

  @Before
  public void setUp() {
    ProtocolFactory protocolFactory = new ProtocolFactory();
    Node startNode = new Node(ProtocolMonitorTest.TestLayer.class, true);
    Node meiumNode = new Node(ProtocolMonitorTest.TestLayer.class);
    Node finishNode = new Node(ProtocolMonitorTest.TestLayer.class);
    startNode.addConnection(new Connection("1", meiumNode));
    meiumNode.addConnection(new Connection("1", finishNode));
    protocolFactory.addNode(startNode);
    protocolFactory.addNode(meiumNode);
    protocolFactory.addNode(finishNode);
    this.protocol = protocolFactory.build();
  }

  @Test
  public void testLayerInstanceOf() {
    TestLayer testLayer = new TestLayer("Hello, world!");
    assertEquals(true, protocol.checkStatusAndProgress(testLayer));
    assertEquals(true, protocol.checkStatusAndProgress(testLayer));
    boolean thrown = false;
    try {
      protocol.checkStatusAndProgress(testLayer);
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }
}