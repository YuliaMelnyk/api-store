package com.retail.price.domain.usecase;

import com.retail.price.domain.model.PriceVO;
import java.time.LocalDateTime;

public interface GetPriceUseCase {

  PriceVO getPrice(LocalDateTime applicationDate, Long productId, Long brandId);
}