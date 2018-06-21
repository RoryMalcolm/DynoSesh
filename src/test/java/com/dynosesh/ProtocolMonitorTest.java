package com.dynosesh;

import com.dynosesh.exceptions.InvalidSessionException;
import com.dynosesh.protocol.Node;
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
    Protocol protocol = new Protocol();
    protocol.addNode(new Node(new TestLayer("Hello, world!"), true));
    protocol.addNode(new Node(new TestLayer("Hello, world!"), false));
    this.protocolMonitor = new ProtocolMonitor(protocol);
    for (int i = 0; i < 10; i++) {
      this.protocolMonitor.addActor(String.valueOf(i), new Actor());
    }
  }

  @Test
  public void send() {
    try {
      this.protocolMonitor.send(protocolMonitor.getActor("1"),
          "0", new TestLayer("Hello world!"));
      this.protocolMonitor.send(this.protocolMonitor.getActor("1"),
          "0", new TestLayer("Hello world!"));
    } catch (InvalidSessionException e) {
      e.printStackTrace();
    }
  }
}