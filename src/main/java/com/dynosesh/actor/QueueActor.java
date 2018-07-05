package com.dynosesh.actor;

import com.dynosesh.Sendable;
import java.util.ArrayDeque;

/**
 * Created by Rory Malcolm on 19/06/2018.
 *
 * @author Rory Malcolm (rorymckenziemalcolm@gmail.com)
 */
public class QueueActor implements Actor {
  private ArrayDeque<Sendable> tasks;

  /**
   * Actor constructor
   *
   * <p>
   * Used to send and receive tasks across the protocol monitor for processing
   * </p>
   */
  public QueueActor() {
    this.tasks = new ArrayDeque<>();
  }

  /**
   * Adds a task to the actor's task queue
   *
   * @param sendable The task for adding to the queue
   */
  @Override
  public void sendTask(Sendable sendable) {
    this.tasks.push(sendable);
  }

  /**
   * Gets the task on the top of the actor's task queue
   *
   * @return The task at the top of the queue
   */
  @Override
  public Sendable receiveTask() {
    return this.tasks.pop();
  }
}
