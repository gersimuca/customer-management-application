package com.gersimuca.erp.common.exception;

import com.gersimuca.erp.model.ErrorCode;
import java.net.URI;

public final class ProblemTypes {

  private static final String BASE = "https://api.erp-manager.com/problems/";

  private ProblemTypes() {}

  public static URI of(final ErrorCode code) {
    return URI.create(BASE + code.getValue().toLowerCase());
  }
}
