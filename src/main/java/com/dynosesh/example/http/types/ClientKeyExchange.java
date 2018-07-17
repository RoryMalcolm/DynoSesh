package com.dynosesh.example.http.types;

import com.dynosesh.Sendable;
import java.io.Serializable;

public class ClientKeyExchange extends Sendable implements Serializable {

  public ClientKeyExchange(Object payload, String target) {
    super(payload, target);
  }
}
