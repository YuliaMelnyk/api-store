package com.retail.price.domain.usecase;

import com.retail.price.domain.model.PriceVO;
import java.time.LocalDateTime;

/**
 * The interface Get price use case.
 */
public interface GetPriceUseCase {

  /**
   * Gets price.
   *
   * @param applicationDate the application date
   * @param productId       the product id
   * @param brandId         the brand id
   * @return the price
   */
  PriceVO getPrice(LocalDateTime applicationDate, Long productId, Long brandId);
}