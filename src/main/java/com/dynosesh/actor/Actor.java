package com.dynosesh.actor;

import com.dynosesh.Sendable;
import com.dynosesh.exceptions.InvalidSessionException;

public interface Actor {

  /**
   * Sends a task to the toActor specified
   *
   * @param sendable The task to send to the toActor
   */
  void sendTask(Sendable sendable);

  /**
   * Receives a task from the toActor specified
   *
   * @return The task from the toActor
   */
  Sendable receiveTask() throws InvalidSessionException;
}
