package com.dynosesh.actor;

import com.dynosesh.Sendable;
import com.dynosesh.exceptions.InvalidSessionException;

public interface Actor {

  /**
   * Sends a task to the actor specified
   *
   * @param sendable The task to send to the actor
   */
  void sendTask(Sendable sendable);

  /**
   * Receives a task from the actor specified
   *
   * @return The task from the actor
   */
  Sendable receiveTask() throws InvalidSessionException;
}
