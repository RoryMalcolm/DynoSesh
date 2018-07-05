package com.dynosesh.actor;

import com.dynosesh.Sendable;

public interface Actor {

  void sendTask(Sendable sendable);

  Sendable receiveTask();
}
