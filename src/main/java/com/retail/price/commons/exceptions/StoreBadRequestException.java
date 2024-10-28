package com.retail.price.commons.exceptions;

import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Store bad request exception.
 */
@RequiredArgsConstructor
@Getter
public class StoreBadRequestException {

  private final Map<String, String[]> parameters;

  private final String error;
}
