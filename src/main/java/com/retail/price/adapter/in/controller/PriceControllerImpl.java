package com.retail.price.adapter.in.controller;

import com.retail.price.adapter.in.mapper.GetPriceResponseMapper;
import com.retail.price.adapter.in.response.GetPriceResponse;
import com.retail.price.application.port.in.GetPriceUseCase;
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
public class PriceControllerImpl implements PriceController {

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