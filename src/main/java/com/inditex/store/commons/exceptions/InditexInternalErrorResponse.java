package com.inditex.store.commons.exceptions;

import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class InditexInternalErrorResponse {

  private final Map<String, String[]> parameters;

  private final String error;
}
