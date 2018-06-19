package com.dynosesh;

import java.util.Stack;

/**
 * Created by Rory Malcolm on 19/06/2018.]
 * TODO: Work out how to express more complicated protocols - potentially a tree ?
 */
class Protocol {
  private Stack<Class<? extends Sendable>> protocol;

  Protocol() {
    this.protocol = new Stack<>();
  }

  Protocol(Stack<Class<? extends Sendable>> protocol) {
    this.protocol = protocol;
  }

  void addLayer(Class<? extends Sendable> forAdding) {
    this.protocol.push(forAdding);
  }

  Class<? extends Sendable> getLayer() {
    return this.protocol.pop();
  }
}
