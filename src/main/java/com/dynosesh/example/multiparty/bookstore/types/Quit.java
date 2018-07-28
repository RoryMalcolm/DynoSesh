package com.dynosesh.example.multiparty.bookstore.types;

import com.dynosesh.Sendable;

public class Quit extends Sendable {

  public Quit(Object payload, String target) {
    super(payload, target);
  }
}
