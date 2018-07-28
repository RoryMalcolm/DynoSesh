package com.dynosesh.protocol;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.dynosesh.Sendable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Rory Malcolm on 26/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
class ConnectionTest {

  private Connection connection1;
  private Connection connection;

  @BeforeEach
  void setUp() {
    Node testNode = new Node(TestClass.class);
    connection = new Connection("0", "1", "0");
    connection1 = new Connection("0", "1", "0");
  }

  @Test
  void equals() {
    assertEquals(connection, connection1);
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