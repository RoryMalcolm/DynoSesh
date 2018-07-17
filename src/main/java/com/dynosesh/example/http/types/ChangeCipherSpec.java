package com.dynosesh.example.http.types;

import com.dynosesh.Sendable;
import java.io.Serializable;

public class ChangeCipherSpec extends Sendable implements Serializable {

  public ChangeCipherSpec(Object payload, String target) {
    super(payload, target);
  }
}
