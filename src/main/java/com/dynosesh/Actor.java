package com.dynosesh;

import java.util.LinkedList;

/**
 * Created by Rory Malcolm on 19/06/2018.
 */
public class Actor {
  private LinkedList<Sendable> tasks;

  /**
   * Actor constructor
   *
   * <p>
   *   Used to send and receive tasks across the protocol monitor for processing
   * </p>
   */
  public Actor() {
    this.tasks = new LinkedList<>();
  }

  /**
   * Adds a task to the actor's task queue
   * @param task The task for adding to the queue
   */
  public void addTask(Sendable task) {
    this.tasks.push(task);
  }

  /**
   * Gets the task on the top of the actor's task queue
   * @return The task at the top of the queue
   */
  public Sendable getTask() {
    return this.tasks.pop();
  }
}