package com.dynosesh;

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
    protocol.addLayer(TestLayer.class);
    protocol.addLayer(TestLayer.class);
  }

  @Test
  public void testLayerInstanceOf() {
    TestLayer testLayer = new TestLayer("Hello, world!");
    assertEquals(testLayer.getClass(), protocol.getLayer());
    assertEquals(testLayer.getClass(), protocol.getLayer());
    boolean thrown = false;
    try {
      protocol.getLayer();
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }
}