package com.dynosesh.actor;

import com.dynosesh.Sendable;
import com.dynosesh.exceptions.InvalidSessionException;

public interface Actor {

  /**
   * Sends a task toActor the actor specified
   *
   * @param sendable The task toActor send toActor the actor
   */
  void sendTask(Sendable sendable);

  /**
   * Receives a task from the actor specified
   *
   * @return The task from the actor
   */
  Sendable receiveTask() throws InvalidSessionException;
}
