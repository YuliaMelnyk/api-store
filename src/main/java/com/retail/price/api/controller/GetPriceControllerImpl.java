package com.retail.price.api.controller;

import com.retail.price.api.mapper.GetPriceResponseMapper;
import com.retail.price.api.response.GetPriceResponse;
import com.retail.price.domain.usecase.GetPriceUseCase;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Get price controller.
 */
@RestController
@RequiredArgsConstructor
public class GetPriceControllerImpl implements GetPriceController {

  private final GetPriceUseCase getPriceUseCase;

  private final GetPriceResponseMapper getPriceResponseMapper;

  @Override
  @GetMapping("/prices")
  public ResponseEntity<GetPriceResponse> getCurrentPrice(
      LocalDateTime applicationDate,
      Long productId, Long brandId) {

    return ResponseEntity.ok(getPriceResponseMapper.mapToGetPriceResponse(
        getPriceUseCase.getPrice(applicationDate, productId, brandId)));
  }
}