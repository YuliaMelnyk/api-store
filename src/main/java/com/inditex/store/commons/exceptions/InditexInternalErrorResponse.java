package com.inditex.store.commons.exceptions;

import java.util.Map;

public record InditexInternalErrorResponse(Map<String, String[]> parameters, String error) {

}
