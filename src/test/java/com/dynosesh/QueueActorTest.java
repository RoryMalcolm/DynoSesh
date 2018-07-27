package com.dynosesh;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dynosesh.actor.QueueActor;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Rory Malcolm on 25/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
class QueueActorTest {

  private QueueActor actor;

  @BeforeEach
  void setUp() {
    actor = new QueueActor();
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
      actor.receiveTask();
    } catch (NoSuchElementException e) {
      thrown = true;
    }
    assertTrue(thrown);
    TestClass testClass = new TestClass("Test test");
    actor.sendTask(testClass);
    assertEquals(testClass, actor.receiveTask());
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