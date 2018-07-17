package com.dynosesh.example.http.types;

import com.dynosesh.Sendable;
import java.io.Serializable;

public class HelloDone extends Sendable implements Serializable {

  public HelloDone(Object payload, String target) {
    super(payload, target);
  }
}
