package com.inditex.store.v1.prices.controller;

import com.inditex.store.usecase.GetPriceUseCase;
import com.inditex.store.v1.prices.controller.mapper.GetPriceResponseMapper;
import com.inditex.store.v1.prices.controller.model.GetPricesResponse;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetPriceControllerImpl implements GetPriceController {

  private final GetPriceUseCase getPriceUseCase;

  private final GetPriceResponseMapper getPriceResponseMapper;

  @Override
  @GetMapping("/prices")
  public ResponseEntity<GetPricesResponse> getCurrentPrice(LocalDateTime applicationDate,
                                                           Long productId, Long brandId) {
    return ResponseEntity.ok(getPriceResponseMapper.priceVOToGetPriceResponse(
        getPriceUseCase.getPrice(applicationDate, productId, brandId)));
  }
}