package com.dynosesh.example.http.types;

import com.dynosesh.Sendable;
import java.io.Serializable;

public class CertificateRequest extends Sendable implements Serializable {

  public CertificateRequest(Object payload, String target) {
    super(payload, target);
  }
}
