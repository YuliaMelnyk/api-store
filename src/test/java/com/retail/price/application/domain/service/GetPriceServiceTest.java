package com.retail.price.application.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.retail.price.adapter.out.persistence.model.Brand;
import com.retail.price.adapter.out.persistence.model.Price;
import com.retail.price.application.domain.model.BrandVO;
import com.retail.price.application.domain.model.PriceVO;
import com.retail.price.application.port.out.ExternalStorage;
import com.retail.price.commons.exceptions.PriceNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetPriceServiceTest {

  @Mock
  private ExternalStorage externalStorage;

  @InjectMocks
  private GetPriceService getPriceUseCase;

  private PriceVO priceVO;

  private Price price;

  @BeforeEach
  public void setUp() {
    price = Price.builder().priceList(1L).price(BigDecimal.valueOf(30.35))
        .brandId(Brand.builder().id(1L).name("ZARA").build()).curr("EUR")
        .endDate(LocalDateTime.parse("2020-09-15T16:00:00"))
        .startDate(LocalDateTime.parse("2020-06-15T16:00:00")).priority(1).productId(35455L)
        .build();
  }

  @Test
  public void get_Price_OK() {
    priceVO = PriceVO.builder().priceList(1L).price(BigDecimal.valueOf(30.35))
        .brandId(BrandVO.builder().id(1L).name("ZARA").build()).curr("EUR")
        .endDate(LocalDateTime.parse("2020-09-15T16:00:00"))
        .startDate(LocalDateTime.parse("2020-06-15T16:00:00")).priority(1).productId(35455L)
        .build();
    when(externalStorage.getCurrentPrice(1L, 35455L,
                                         LocalDateTime.parse("2020-06-15T16:00:00"))).thenReturn(
        Optional.of(priceVO));
    PriceVO result = getPriceUseCase.getPrice(LocalDateTime.parse("2020-06-15T16:00:00"), 35455L,
                                              1L);

    assertThat(result).usingRecursiveComparison().isEqualTo(priceVO);
  }

  @Test
  public void get_Price_Throw_Not_Found_Exception() {
    priceVO = PriceVO.builder().priceList(1L).price(BigDecimal.valueOf(30.35))
        .brandId(BrandVO.builder().id(1L).name("ZARA").build()).curr("EUR")
        .endDate(LocalDateTime.parse("2020-09-15T16:00:00"))
        .startDate(LocalDateTime.parse("2020-06-15T16:00:00")).priority(1).productId(35455L)
        .build();
    when(externalStorage.getCurrentPrice(1L, 35455L,
                                         LocalDateTime.parse("2020-06-15T16:00:00"))).thenReturn(
        Optional.empty());
    Assertions.assertThrows((PriceNotFoundException.class), () -> getPriceUseCase.getPrice(
        LocalDateTime.parse("2020-06-15T16:00:00"), 35455L, 1L));
  }
}