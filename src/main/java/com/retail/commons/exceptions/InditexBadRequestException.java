package com.retail.commons.exceptions;

import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class InditexBadRequestException {

  private final Map<String, String[]> parameters;

  private final String error;
}
