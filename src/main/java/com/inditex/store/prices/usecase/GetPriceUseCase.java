package com.inditex.store.prices.usecase;

import com.inditex.store.prices.usecase.model.PriceVO;
import java.time.LocalDateTime;

public interface GetPriceUseCase {
  PriceVO getPrice(LocalDateTime applicationDate, Long productId, Long brandId);
}