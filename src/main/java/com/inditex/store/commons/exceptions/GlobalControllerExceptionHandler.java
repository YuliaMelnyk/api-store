package com.inditex.store.commons.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler {

  @ExceptionHandler(PriceNotFoundException.class)
  public ResponseEntity<?> handlePriceNotFoundException(PriceNotFoundException exception, WebRequest request) {
    return new ResponseEntity(new InditexInternalErrorResponse(request.getParameterMap(), "error"), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleException(Exception exception, WebRequest request) {
    log.error("Exception received, message: <{}>", exception.getMessage());
    return new ResponseEntity(new InditexInternalErrorResponse(request.getParameterMap(), "error"), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
