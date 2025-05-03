package com.gersimuca.erp.common.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

  private Map<String, Object> params;
  private String errorDetailMessage;
  private HttpStatus status;
  private boolean trace;

  public ApiException() {
    super();
  }

  public ApiException(String message, Throwable cause) {
    super(message, cause);
  }

  public ApiException(String message) {
    super(message);
  }

  public ApiException(String message, HttpStatus status) {
    this(message, null, status, new HashMap<>(), null, false);
  }

  public ApiException(String message, HttpStatus status, Map<String, Object> params) {
    this(message, null, status, params, null, false);
  }

  public ApiException(Throwable cause) {
    super(cause);
  }

  public ApiException(
      String message,
      Throwable cause,
      HttpStatus status,
      Map<String, Object> params,
      String errorDetailMessage,
      boolean trace) {
    super(message, cause);
    this.status = status;
    this.params = params;
    this.errorDetailMessage = errorDetailMessage;
    this.trace = trace;
  }
}
