package com.dynosesh.example.multiparty.bookstore.types;

import com.dynosesh.Sendable;

public class SendableString extends Sendable {

  public SendableString(String payload, String target) {
    super(payload, target);
  }
}
