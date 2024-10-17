package com.retail.commons.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Price not found exception.
 */
public class PriceNotFoundException extends RuntimeException {

  private final HttpStatus httpStatus;

  /**
   * Instantiates a new Price not found exception.
   *
   * @param httpStatus the http status
   * @param message    the message
   */
  public PriceNotFoundException(HttpStatus httpStatus, String message) {
    super(message);
    this.httpStatus = httpStatus;
  }

  /**
   * Instantiates a new Price not found exception.
   *
   * @param httpStatus the http status
   */
  public PriceNotFoundException(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

  /**
   * Gets http status.
   *
   * @return the http status
   */
  public HttpStatus getHttpStatus() {
    return this.httpStatus;
  }
}