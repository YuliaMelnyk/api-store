package com.retail.commons.exceptions;

import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Inditex internal error response.
 */
@RequiredArgsConstructor
@Getter
public class InditexInternalErrorResponse {

  private final Map<String, String[]> parameters;

  private final String error;
}
