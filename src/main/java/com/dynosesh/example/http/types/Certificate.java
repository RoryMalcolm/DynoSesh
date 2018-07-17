package com.dynosesh.example.http.types;

import com.dynosesh.Sendable;
import java.io.Serializable;

public class Certificate extends Sendable implements Serializable {

  public Certificate(Object payload, String target) {
    super(payload, target);
  }
}
