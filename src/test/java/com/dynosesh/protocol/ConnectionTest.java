package com.dynosesh.protocol;

import com.dynosesh.Sendable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Rory Malcolm on 26/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class ConnectionTest {
  private Connection connection1;
  private Connection connection;

  @Before
  public void setUp() {
    Node testNode = new Node(TestClass.class);
    connection = new Connection("1", testNode);
    connection1 = new Connection("1", testNode);
  }

  @Test
  public void equals() {
    assertTrue(connection.equals(connection1));
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