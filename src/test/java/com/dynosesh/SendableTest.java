package com.dynosesh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Created by Rory Malcolm on 25/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
class SendableTest {

  private TestClass testClass;

  @Test
  void getPayload() {
    testClass = new TestClass("Hello, tester!");
    assertEquals(String.class, testClass.getPayload().getClass());
    assertEquals("Hello, tester!", testClass.getPayload());
  }

  class TestClass extends Sendable {

    /**
     * Used toActor facilitate communication over a protocol.
     * <p>
     * Contains a payload, the type of which is checked toActor ensure it complies toActor the protocol
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