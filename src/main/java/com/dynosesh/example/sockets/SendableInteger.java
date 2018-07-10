package com.dynosesh.example.sockets;

import com.dynosesh.Sendable;
import java.io.Serializable;

public class SendableInteger extends Sendable implements Serializable {

  public SendableInteger(int payload, String target) {
    super(payload, target);
  }
}
