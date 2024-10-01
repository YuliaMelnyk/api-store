package com.inditex.store.prices.usecase;

import com.inditex.store.commons.exceptions.PriceNotFoundException;
import com.inditex.store.prices.usecase.mapper.PriceToPriceVOMapper;
import com.inditex.store.prices.usecase.model.PriceVO;
import com.inditex.store.repository.PriceRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPriceUseCaseImpl implements GetPriceUseCase {
  private final PriceRepository priceRepository;

  private final PriceToPriceVOMapper priceMapper;

  @Override
  public PriceVO getPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
    return Optional.of(priceRepository.getCurrentPrice(brandId, productId, applicationDate))
        .map(priceMapper::mapToPriceVO)
        .orElseThrow(() -> new PriceNotFoundException(HttpStatus.NOT_FOUND, "Unable to find current price for brandId " + brandId +
            " productId " + brandId + " and applicationDate " + applicationDate));
  }
}