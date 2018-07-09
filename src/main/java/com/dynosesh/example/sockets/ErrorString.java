package com.dynosesh.example.sockets;

import com.dynosesh.Sendable;
import java.io.Serializable;

class ErrorString extends Sendable implements Serializable {

  ErrorString(String sends, String target) {
    super(sends, target);
  }
}
