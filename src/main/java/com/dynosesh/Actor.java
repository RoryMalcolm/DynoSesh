package com.dynosesh;

import java.util.Stack;

/**
 * Created by Rory Malcolm on 19/06/2018.
 */
class Actor {
  private Stack<Sendable> tasks;

  Actor() {
    this.tasks = new Stack<>();
  }

  void addTask(Sendable task) {
    this.tasks.push(task);
  }

  Sendable getTask() {
    return this.tasks.pop();
  }
}
