package com.retail.mapper;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.openMocks;

import com.retail.price.api.mapper.GetPriceResponseMapper;
import com.retail.price.api.response.GetPriceResponse;
import com.retail.price.domain.model.BrandVO;
import com.retail.price.domain.model.PriceVO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetPriceResponseMapperTest {

  private static GetPriceResponse getPriceResponse;
  @InjectMocks
  private GetPriceResponseMapper getPriceResponseMapper =
      Mappers.getMapper(GetPriceResponseMapper.class);

  @BeforeEach
  public void setUp() {
    openMocks(this);

    getPriceResponse = GetPriceResponse.builder()
        .priceList(1L)
        .price(BigDecimal.valueOf(30.35))
        .brandId(1L)
        .endDate(LocalDateTime.MAX)
        .startDate(LocalDateTime.MIN)
        .productId(124L)
        .build();
  }

  @Test
  public void ok_GetPriceResponse() {

    GetPriceResponse response =
        getPriceResponseMapper.mapToGetPriceResponse(
            PriceVO.builder()
                .priceList(1L)
                .price(BigDecimal.valueOf(30.35))
                .brandId(BrandVO.builder().id(1L).name("ZARA").build())
                .curr("EUR")
                .endDate(LocalDateTime.MAX)
                .startDate(LocalDateTime.MIN)
                .priority(1)
                .productId(124L)
                .build());

    assertThat(response).usingRecursiveComparison().isEqualTo(getPriceResponse);
  }
}
