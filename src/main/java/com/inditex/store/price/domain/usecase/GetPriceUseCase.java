package com.inditex.store.price.domain.usecase;

import com.inditex.store.price.domain.model.PriceVO;
import java.time.LocalDateTime;

public interface GetPriceUseCase {

  PriceVO getPrice(LocalDateTime applicationDate, Long productId, Long brandId);
}