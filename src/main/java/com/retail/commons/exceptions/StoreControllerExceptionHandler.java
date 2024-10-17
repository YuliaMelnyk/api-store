package com.retail.commons.exceptions;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.retail.price.api.response.GetPriceResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;

/**
 * Store controller exception handler.
 */
@Slf4j
@ControllerAdvice
public class StoreControllerExceptionHandler {

  /**
   * Handle price not found exception response entity.
   *
   * @param exception the exception
   * @param request   the request
   * @return the response entity
   */
  @ExceptionHandler(PriceNotFoundException.class)
  @ResponseStatus(NO_CONTENT)
  public ResponseEntity<?> handlePriceNotFoundException(PriceNotFoundException exception,
                                                        WebRequest request) {
    return new ResponseEntity(new GetPriceResponse(), NO_CONTENT);
  }

  /**
   * Handle internal exception response entity.
   *
   * @param exception the exception
   * @param request   the request
   * @return the response entity
   */
  @ExceptionHandler(InternalServerError.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<?> handleInternalException(Exception exception, WebRequest request) {
    log.error("Exception received, message: <{}>", exception.getMessage());
    return new ResponseEntity(new InditexInternalErrorResponse(request.getParameterMap(), "error"),
                              HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handle exception response entity.
   *
   * @param exception the exception
   * @param request   the request
   * @return the response entity
   */
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<?> handleException(Exception exception, WebRequest request) {
    log.error("Exception received, message: <{}>", exception.getMessage());
    return new ResponseEntity(new InditexBadRequestException(request.getParameterMap(), "error"),
                              HttpStatus.BAD_REQUEST);
  }
}
