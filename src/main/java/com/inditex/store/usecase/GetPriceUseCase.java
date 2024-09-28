package com.inditex.store.usecase;

import com.inditex.store.v1.prices.controller.model.GetPriceResponse;
import java.time.LocalDateTime;

public interface GetPriceUseCase {
  GetPriceResponse getPrice(LocalDateTime startDate,
                            Long productId, Long brandId);
}