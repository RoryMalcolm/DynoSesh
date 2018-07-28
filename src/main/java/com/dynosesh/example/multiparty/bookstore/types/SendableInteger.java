package com.dynosesh.example.multiparty.bookstore.types;

import com.dynosesh.Sendable;

public class SendableInteger extends Sendable {

  public SendableInteger(Integer payload, String target) {
    super(payload, target);
  }
}
