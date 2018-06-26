package com.dynosesh.protocol;

import com.dynosesh.Sendable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Rory Malcolm on 26/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class ProtocolFactoryTest {

  private ProtocolFactory protocolFactory;

  @Before
  public void setUp() {
    protocolFactory = new ProtocolFactory();
  }

  @Test
  public void addNode() {
    protocolFactory.addNode(new Node(null));
    protocolFactory.addNode(new Node(TestClass.class));
    assertNotNull(protocolFactory.build());
  }

  @Test
  public void build() {
    boolean thrown = false;
    try {
      protocolFactory.addNode(new Node(TestClass.class));
      protocolFactory.build();
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }

  class TestClass extends Sendable {

    /**
     * Used to facilitate communication over a protocol.
     * <p>
     * Contains a payload, the type of which is checked to ensure it complies to the protocol
     * implementation.
     * </p>
     *
     * @param payload The payload of the message
     */
    public TestClass(Object payload) {
      super(payload);
    }
  }
}