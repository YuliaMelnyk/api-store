package com.inditex.store.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.inditex.store.price.adapter.repository.PriceRepository;
import com.inditex.store.price.adapter.repository.model.Brand;
import com.inditex.store.price.adapter.repository.model.Price;
import com.inditex.store.price.domain.facade.PriceFacade;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PriceFacadeTest {

  @Mock
  private PriceRepository priceRepository;

  @InjectMocks
  private PriceFacade priceFacade;

  private Price price;

  @Test
  public void ok_Price() {
    price = Price.builder().priceList(1L)
        .price(BigDecimal.valueOf(30.35))
        .brandId(Brand.builder().id(1L).name("ZARA").build())
        .curr("EUR")
        .endDate(LocalDateTime.MAX)
        .startDate(LocalDateTime.MIN)
        .priority(1)
        .productId(124L).build();
    when(priceRepository.getCurrentPrice(1L, 35455L, LocalDateTime.parse("2020-06-15T16:00:00")))
        .thenReturn(price);

    Optional<Price> priceOptional = priceFacade.getPriceVO(
        LocalDateTime.parse("2020-06-15T16:00:00"), 35455L, 1L);

    assertThat(priceOptional.get()).usingRecursiveComparison().isEqualTo(price);
  }

}



