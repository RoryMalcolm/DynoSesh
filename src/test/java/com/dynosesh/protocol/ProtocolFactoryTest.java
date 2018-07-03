package com.dynosesh.protocol;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dynosesh.Sendable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Rory Malcolm on 26/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
class ProtocolFactoryTest {

  private ProtocolFactory protocolFactory;

  @BeforeEach
  void setUp() {
    protocolFactory = new ProtocolFactory();
  }

  @Test
  void addNode() {
    protocolFactory.addNode(new Node(null));
    protocolFactory.addNode(new Node(TestClass.class));
    assertNotNull(protocolFactory.build());
  }

  @Test
  void build() {
    boolean thrown = false;
    try {
      protocolFactory.addNode(new Node(TestClass.class));
      protocolFactory.build();
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }

  @Test
  void twoStartNodesBuild() {
    protocolFactory.addNode(new Node(null));
    protocolFactory.addNode(new Node(null));
    assertThrows(IllegalStateException.class, () -> protocolFactory.build());
  }

  @Test
  void node() {
    assertNotNull(protocolFactory
        .node()
        .payload(null)
        .node()
        .payload(TestClass.class)
        .connection()
        .actor("0")
        .to("1")
        .build());
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