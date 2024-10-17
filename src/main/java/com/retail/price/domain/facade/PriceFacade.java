package com.retail.price.domain.facade;

import com.retail.price.adapter.repository.PriceRepository;
import com.retail.price.adapter.repository.model.Price;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceFacade {

  private final PriceRepository priceRepository;

  public Optional<Price> getPriceVO(LocalDateTime applicationDate, Long productId, Long brandId) {
    return Optional.ofNullable(
        priceRepository.getCurrentPrice(brandId, productId, applicationDate));
  }
}