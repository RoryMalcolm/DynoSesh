package com.dynosesh.example.http.types;

import com.dynosesh.Sendable;
import java.io.Serializable;

public class Finished extends Sendable implements Serializable {

  public Finished(Object payload, String target) {
    super(payload, target);
  }
}
