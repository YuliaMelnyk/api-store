package com.retail.price.application.domain.service;

import com.retail.price.application.domain.model.PriceVO;
import com.retail.price.application.port.in.GetPriceUseCase;
import com.retail.price.application.port.out.ExternalStorage;
import com.retail.price.commons.exceptions.PriceNotFoundException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Get price service.
 */
@Service
@RequiredArgsConstructor
public class GetPriceService implements GetPriceUseCase {

  private final ExternalStorage externalStorage;

  @Override
  public PriceVO getPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
    return externalStorage.getCurrentPrice(brandId, productId, applicationDate)
        .orElseThrow(() -> new PriceNotFoundException(HttpStatus.NOT_FOUND,
                                                      "Unable to find current price for brandId "
                                                          + brandId +
                                                          " productId " + productId
                                                          + " and applicationDate "
                                                          + applicationDate));
  }
}