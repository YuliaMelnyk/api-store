package com.retail.price.domain.usecase;

import com.retail.commons.exceptions.PriceNotFoundException;
import com.retail.price.domain.mapper.PriceToPriceVOMapper;
import com.retail.price.domain.model.PriceVO;
import com.retail.price.domain.facade.PriceFacade;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPriceUseCaseImpl implements GetPriceUseCase {

  private final PriceFacade priceFacade;

  private final PriceToPriceVOMapper priceMapper;

  @Override
  public PriceVO getPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
    return priceFacade.getPriceVO(applicationDate, productId, brandId)
        .map(priceMapper::mapToPriceVO)
        .orElseThrow(() -> new PriceNotFoundException(HttpStatus.NOT_FOUND,
                                                      "Unable to find current price for brandId "
                                                          + brandId +
                                                          " productId " + productId
                                                          + " and applicationDate "
                                                          + applicationDate));
  }
}