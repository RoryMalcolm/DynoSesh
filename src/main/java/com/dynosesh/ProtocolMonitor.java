package com.dynosesh;

import com.dynosesh.exceptions.InvalidSessionException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rory Malcolm on 19/06/2018.
 */
class ProtocolMonitor {

  private Map<String, Actor> actorMap;
  private Protocol protocol;

  ProtocolMonitor(Protocol protocol) {
    this.protocol = protocol;
    this.actorMap = new HashMap<>();
  }

  void addActor(String key, Actor actor) {
    this.actorMap.put(key, actor);
  }

  void send(String address, Sendable payload) throws InvalidSessionException {
    if (protocol.getLayer() == payload.getClass()) {
      this.actorMap.get(address).addTask(payload);
    } else {
      throw new InvalidSessionException();
    }
  }
}
