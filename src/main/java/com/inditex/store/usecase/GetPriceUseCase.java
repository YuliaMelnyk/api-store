package com.inditex.store.usecase;

import com.inditex.store.usecase.model.PriceVO;
import java.time.LocalDateTime;
import java.util.List;

public interface GetPriceUseCase {
  List<PriceVO> getPrice(LocalDateTime applicationDate,
                         Long productId, Long brandId);
}