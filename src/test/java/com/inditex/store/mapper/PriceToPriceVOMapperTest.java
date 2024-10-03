package com.inditex.store.mapper;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.openMocks;

import com.inditex.store.price.adapter.repository.model.Brand;
import com.inditex.store.price.adapter.repository.model.Price;
import com.inditex.store.price.domain.mapper.PriceToPriceVOMapper;
import com.inditex.store.price.domain.model.BrandVO;
import com.inditex.store.price.domain.model.PriceVO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PriceToPriceVOMapperTest {

  private static PriceVO priceVO;
  @InjectMocks
  private PriceToPriceVOMapper priceVOMapper =
      Mappers.getMapper(PriceToPriceVOMapper.class);

  @BeforeEach
  public void setUp() {
    openMocks(this);

    priceVO = PriceVO.builder()
        .priceList(1L)
        .price(BigDecimal.valueOf(30.35))
        .brandId(BrandVO.builder().id(1L).name("ZARA").build())
        .curr("EUR")
        .endDate(LocalDateTime.MAX)
        .startDate(LocalDateTime.MIN)
        .priority(1)
        .productId(124L)
        .build();
  }

  @Test
  public void ok_GetPriceResponse() {

    PriceVO response =
        priceVOMapper.mapToPriceVO(
            Price.builder()
                .priceList(1L)
                .price(BigDecimal.valueOf(30.35))
                .brandId(Brand.builder().id(1L).name("ZARA").build())
                .curr("EUR")
                .endDate(LocalDateTime.MAX)
                .startDate(LocalDateTime.MIN)
                .priority(1)
                .productId(124L)
                .build());

    assertThat(response).usingRecursiveComparison().isEqualTo(response);
  }
}
