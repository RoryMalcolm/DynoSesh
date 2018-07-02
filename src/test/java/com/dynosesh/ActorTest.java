package com.dynosesh;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Rory Malcolm on 25/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
class ActorTest {

  private Actor actor;

  @BeforeEach
  void setUp() {
    actor = new Actor();
  }

  @Test
  void addTask() {
    testTaskQueue();
  }

  @Test
  void getTask() {
    testTaskQueue();
  }

  private void testTaskQueue() {
    boolean thrown = false;
    try {
      actor.getTask();
    } catch (NoSuchElementException e) {
      thrown = true;
    }
    assertTrue(thrown);
    TestClass testClass = new TestClass("Test test");
    actor.addTask(testClass);
    assertEquals(testClass, actor.getTask());
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