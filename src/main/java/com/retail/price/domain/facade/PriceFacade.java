package com.retail.price.domain.facade;

import com.retail.price.adapter.repository.PriceRepository;
import com.retail.price.adapter.repository.model.Price;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Price facade.
 */
@Component
@RequiredArgsConstructor
public class PriceFacade {

  private final PriceRepository priceRepository;

  /**
   * Gets price vo.
   *
   * @param applicationDate the application date
   * @param productId       the product id
   * @param brandId         the brand id
   * @return the price vo
   */
  public Optional<Price> getPriceVO(LocalDateTime applicationDate, Long productId, Long brandId) {
    return Optional.ofNullable(
        priceRepository.getCurrentPrice(brandId, productId, applicationDate));
  }
}