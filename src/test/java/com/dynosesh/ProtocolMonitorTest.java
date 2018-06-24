package com.dynosesh;

import com.dynosesh.exceptions.InvalidSessionException;
import com.dynosesh.protocol.Connection;
import com.dynosesh.protocol.Node;
import com.dynosesh.protocol.Protocol;
import com.dynosesh.protocol.ProtocolFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Rory Malcolm on 19/06/2018.
 */
public class ProtocolMonitorTest {

  class TestLayer extends Sendable {
    TestLayer(String payload) {
      super(payload);
    }
  }

  private ProtocolMonitor protocolMonitor;

  @Before
  public void setUp() {
    ProtocolFactory protocolFactory = new ProtocolFactory();
    Node startNode = new Node(TestLayer.class, true);
    Node meiumNode = new Node(TestLayer.class);
    Node finishNode = new Node(TestLayer.class);
    startNode.addConnection(new Connection("1", meiumNode));
    meiumNode.addConnection(new Connection("1", finishNode));
    protocolFactory.addNode(startNode);
    protocolFactory.addNode(meiumNode);
    protocolFactory.addNode(finishNode);
    Protocol protocol = protocolFactory.build();
    this.protocolMonitor = new ProtocolMonitor(protocol);
    for (int i = 0; i < 10; i++) {
      this.protocolMonitor.addActor(String.valueOf(i), new Actor());
    }
  }

  @Test
  public void send() {
    try {
      this.protocolMonitor.send("1", new TestLayer("Hello world!"));
      this.protocolMonitor.send("1", new TestLayer("Alright mate!"));
    } catch (InvalidSessionException e) {
      e.printStackTrace();
    }
  }
}