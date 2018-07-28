package com.dynosesh.example.multiparty.bookstore.types;

import com.dynosesh.Sendable;
import java.util.Date;

public class SendableDateTime extends Sendable {

  public SendableDateTime(Date payload, String target) {
    super(payload, target);
  }
}
