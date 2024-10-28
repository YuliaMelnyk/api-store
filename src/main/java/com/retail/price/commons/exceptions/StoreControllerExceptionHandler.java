package com.retail.price.commons.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


/**
 * The type Store controller exception handler.
 */
@Slf4j
@ControllerAdvice
public class StoreControllerExceptionHandler {


  /**
   * Handle price not found exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(PriceNotFoundException.class)
  @ResponseStatus(NOT_FOUND)
  public ResponseEntity<ErrorInfo> handlePriceNotFoundException(PriceNotFoundException exception) {
    log.warn("Exception received, message: <{}>", exception.getMessage());
    return createErrorInfoResponseEntity(NOT_FOUND, "Price not found exception",
                                         "Cannot find the price with introduced parameters.");
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(NOT_FOUND)
  public ResponseEntity<ErrorInfo> handleNotFoundException(NotFoundException exception) {
    log.warn("Exception received, message: <{}>", exception.getMessage());
    return createErrorInfoResponseEntity(NOT_FOUND, "Price not found exception",
                                         "Cannot find the price with introduced parameters.");
  }


  /**
   * Handle internal exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(InternalServerError.class)
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  public ResponseEntity<ErrorInfo> handleInternalException(Exception exception) {
    log.warn("Exception received, message: <{}>", exception.getMessage());
    return createErrorInfoResponseEntity(INTERNAL_SERVER_ERROR, "Internal server error",
                                         "Internal server error.");
  }

  /**
   * Handle missing servlet request parameter exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ErrorInfo> handleMissingServletRequestParameterException(
      MissingServletRequestParameterException exception) {
    log.warn("Exception received, message: <{}>", exception.getMessage());
    return createErrorInfoResponseEntity(BAD_REQUEST, "Missing parameter exception",
                                         "Incorrect or missing parameter: " +
                                             exception.getParameterName());
  }

  /**
   * Handle method argument type mismatch exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorInfo> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException exception) {
    log.warn("Exception received, message: <{}>", exception.getMessage());
    return createErrorInfoResponseEntity(BAD_REQUEST, "Method Argument Type Mismatch Exception",
                                         "Parameter Mismatch: " +
                                             exception.getName());
  }

  /**
   * Handle exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorInfo> handleException(Exception exception) {
    log.warn("Exception received, message: <{}>", exception.getMessage());

    return createErrorInfoResponseEntity(BAD_REQUEST, "Method Argument Type Mismatch Exception",
                                         "Constraint violation exception ");
  }

  private ResponseEntity<ErrorInfo> createErrorInfoResponseEntity(HttpStatus httpStatus,
                                                                  String errorType,
                                                                  String errorMessage) {
    return ResponseEntity.status(httpStatus)
        .body(new ErrorInfo(errorType, errorMessage));
  }
}