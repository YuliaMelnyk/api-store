package com.retail.price.adapter.out.persistence;

import com.retail.price.adapter.out.persistence.mapper.PriceToPriceVOMapper;
import com.retail.price.adapter.out.persistence.repository.PriceRepository;
import com.retail.price.application.domain.model.PriceVO;
import com.retail.price.application.port.out.ExternalStorage;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PricePersistenceAdapter implements ExternalStorage {

  private final PriceRepository priceRepository;
  private final PriceToPriceVOMapper priceVOMapper;

  @Override
  public Optional<PriceVO> getCurrentPrice(Long brandId, Long productId,
                                           LocalDateTime applicationDate) {
    return priceRepository.getCurrentPrice(brandId, productId, applicationDate)
        .map(priceVOMapper::mapToPriceVO);
  }
}
