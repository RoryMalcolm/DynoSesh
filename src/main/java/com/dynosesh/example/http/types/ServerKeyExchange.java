package com.dynosesh.example.http.types;

import com.dynosesh.Sendable;
import java.io.Serializable;

public class ServerKeyExchange extends Sendable implements Serializable {

  public ServerKeyExchange(Object payload, String target) {
    super(payload, target);
  }
}
