package com.dynosesh;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Rory Malcolm on 25/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class ActorTest {

  private Actor actor;

  @Before
  public void setUp() {
    actor = new Actor();
  }

  @Test
  public void addTask() {
    testTaskQueue();
  }

  @Test
  public void getTask() {
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