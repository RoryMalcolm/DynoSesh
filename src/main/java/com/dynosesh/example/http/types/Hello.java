package com.dynosesh.example.http.types;

import com.dynosesh.Sendable;
import java.io.Serializable;

public class Hello extends Sendable implements Serializable {

  public Hello(Object payload, String target) {
    super(payload, target);
  }
}
