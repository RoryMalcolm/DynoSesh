package com.dynosesh;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Rory Malcolm on 25/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class SendableTest {

  @Test
  public void getPayload() {
    TestClass testClass = new TestClass("Hello, tester!");
    assertEquals(String.class, testClass.getPayload().getClass());
    assertEquals("Hello, tester!", testClass.getPayload());
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
    TestClass(Object payload) {
      super(payload);
    }
  }
}