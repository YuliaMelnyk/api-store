package com.retail.commons.exceptions;

import org.springframework.http.HttpStatus;

public class PriceNotFoundException extends RuntimeException {

  private final HttpStatus httpStatus;

  public PriceNotFoundException(HttpStatus httpStatus, String message) {
    super(message);
    this.httpStatus = httpStatus;
  }

  public PriceNotFoundException(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

  public HttpStatus getHttpStatus() {
    return this.httpStatus;
  }
}