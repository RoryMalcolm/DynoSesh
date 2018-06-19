package com.dynosesh;

/**
 * Created by Rory Malcolm on 19/06/2018.
 */
abstract class Sendable<T> {

  private T payload;

  Sendable(T payload) {
    this.payload = payload;
  }

  T getPayload() {
    return payload;
  }
}
