package com.dynosesh;

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
    protocolFactory.addNode(new Node(new TestLayer("Hello, world!"), true));
    protocolFactory.addNode(new Node(new TestLayer("Hello, world!")));
    protocol = protocolFactory.build();
  }

  @Test
  public void testLayerInstanceOf() {
    TestLayer testLayer = new TestLayer("Hello, world!");
    assertEquals(testLayer.getClass(), protocol.checkStatusAndProgress(testLayer));
    assertEquals(testLayer.getClass(), protocol.checkStatusAndProgress(testLayer));
    boolean thrown = false;
    try {
      protocol.checkStatusAndProgress(testLayer);
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }
}