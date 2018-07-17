package com.dynosesh.example.http.types;

import com.dynosesh.Sendable;
import java.io.Serializable;

public class CertificateVerify extends Sendable implements Serializable {

  public CertificateVerify(Object payload, String target) {
    super(payload, target);
  }
}
