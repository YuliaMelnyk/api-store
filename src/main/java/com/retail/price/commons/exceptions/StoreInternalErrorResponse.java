package com.retail.price.commons.exceptions;

import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Store internal error response.
 */
@RequiredArgsConstructor
@Getter
public class StoreInternalErrorResponse {

  private final Map<String, String[]> parameters;

  private final String error;
}
