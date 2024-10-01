package com.inditex.store.prices.controller;

import com.inditex.store.prices.controller.mapper.GetPricesResponseMapper;
import com.inditex.store.prices.controller.model.GetPricesResponse;
import com.inditex.store.prices.usecase.GetPriceUseCase;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetPriceControllerImpl implements GetPriceController {

  private final GetPriceUseCase getPriceUseCase;

  private final GetPricesResponseMapper getPricesResponseMapper;

  @Override
  @GetMapping("/prices")
  public ResponseEntity<GetPricesResponse> getCurrentPrice(LocalDateTime applicationDate,
                                                           Long productId, Long brandId) {
    return ResponseEntity.ok(getPricesResponseMapper.mapToGetPricesResponse(
        getPriceUseCase.getPrice(applicationDate, productId, brandId)));
  }
}