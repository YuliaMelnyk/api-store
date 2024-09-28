package com.inditex.store.v1.prices.controller;

import com.inditex.store.usecase.GetPriceUseCase;
import com.inditex.store.v1.prices.controller.model.GetPriceResponse;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetPriceControllerImpl implements GetPriceController {

  private final GetPriceUseCase getPriceUseCase;

  @Override
  @GetMapping("/prices")
  public ResponseEntity<GetPriceResponse> getCurrentPrice(LocalDateTime startDate,
                                                          Long productId, Long brandId) {
    return ResponseEntity.ok(getPriceUseCase.getPrice(startDate, productId, brandId));
  }
}