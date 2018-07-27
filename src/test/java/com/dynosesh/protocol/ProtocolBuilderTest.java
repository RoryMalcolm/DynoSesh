package com.dynosesh.protocol;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.dynosesh.ProtocolMonitor;
import com.dynosesh.Sendable;
import com.dynosesh.actor.QueueActor;
import com.dynosesh.exceptions.InvalidSessionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Rory Malcolm on 26/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
class ProtocolBuilderTest {

  private ProtocolBuilder protocolBuilder;

  @BeforeEach
  void setUp() {
    protocolBuilder = new ProtocolBuilder();
  }

  @Test
  void addNode() {
    protocolBuilder.addNode(new Node(null));
    protocolBuilder.addNode(new Node(TestClass.class));
    assertNotNull(protocolBuilder.build());
  }

  @Test
  void build() {
    boolean thrown = false;
    try {
      protocolBuilder.addNode(new Node(TestClass.class));
      protocolBuilder.build();
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }

  @Test
  void twoStartNodesBuild() {
    protocolBuilder.addNode(new Node(null));
    protocolBuilder.addNode(new Node(null));
    assertThrows(IllegalStateException.class, () -> protocolBuilder.build());
  }

  @Test
  void node() {
    Protocol protocol = protocolBuilder
        .node()
        .payload(null)
        .connection()
        .toActor("0")
        .to("1")
        .node()
        .payload(TestClass.class)
        .connection()
        .toActor("0")
        .to("1")
        .build();
    ProtocolMonitor monitor = new ProtocolMonitor(protocol);
    monitor.addActor(new QueueActor());
    monitor.addActor(new QueueActor());
    try {
      monitor.send("0", "1", new TestClass("Hello!"));
    } catch (InvalidSessionException e) {
      e.printStackTrace();
      fail("Errored on transmission");
    }
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