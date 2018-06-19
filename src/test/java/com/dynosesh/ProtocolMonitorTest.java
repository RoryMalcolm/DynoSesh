package com.dynosesh;

import com.dynosesh.exceptions.InvalidSessionException;
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
    protocol.addLayer(TestLayer.class);
    protocol.addLayer(TestLayer.class);
    this.protocolMonitor = new ProtocolMonitor(protocol);
    for (int i = 0; i < 10; i++) {
      this.protocolMonitor.addActor(String.valueOf(i), new Actor());
    }
  }

  @Test
  public void send() {
    try {
      this.protocolMonitor.send("0", new TestLayer("Hello world!"));
      this.protocolMonitor.send("0", new TestLayer("Hello world!"));
    } catch (InvalidSessionException e) {
      e.printStackTrace();
    }
  }
}