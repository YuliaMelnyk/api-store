package com.inditex.store.usecase;

import com.inditex.store.commons.exceptions.PriceNotFoundException;
import com.inditex.store.repository.PriceRepository;
import com.inditex.store.usecase.mapper.PriceMapper;
import com.inditex.store.usecase.model.PriceVO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPriceUseCaseImpl implements GetPriceUseCase {

  private final PriceRepository priceRepository;

  private final PriceMapper priceMapper;

  @Override
  public List<PriceVO> getPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
    return Optional.of(priceRepository.getCurrentPrice(brandId, productId, applicationDate))
        .map(priceMapper::priceToPriceVO)
        .orElseThrow(() -> new PriceNotFoundException(HttpStatus.NOT_FOUND, "Unable to find current price for brandId " + brandId +
            " productId " + brandId + " and applicationDate " + applicationDate));
  }
}