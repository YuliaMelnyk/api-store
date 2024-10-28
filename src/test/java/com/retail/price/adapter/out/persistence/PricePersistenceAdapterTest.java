package com.retail.price.adapter.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.retail.price.adapter.out.persistence.mapper.PriceToPriceVOMapper;
import com.retail.price.adapter.out.persistence.model.Brand;
import com.retail.price.adapter.out.persistence.model.Price;
import com.retail.price.adapter.out.persistence.repository.PriceRepository;
import com.retail.price.application.domain.model.BrandVO;
import com.retail.price.application.domain.model.PriceVO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PricePersistenceAdapterTest {

  @Mock
  private PriceRepository priceRepository;

  @Mock
  private PriceToPriceVOMapper priceMapper = Mappers.getMapper(PriceToPriceVOMapper.class);

  @InjectMocks
  private PricePersistenceAdapter pricePersistenceAdapter;

  private Price price;

  private PriceVO priceVO;

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

    priceVO = PriceVO.builder().priceList(1L)
        .price(BigDecimal.valueOf(30.35))
        .brandId(BrandVO.builder().id(1L).name("ZARA").build())
        .curr("EUR")
        .endDate(LocalDateTime.parse("2020-09-15T16:00:00"))
        .startDate(LocalDateTime.parse("2020-06-15T16:00:00"))
        .priority(1)
        .productId(35455L).build();
    when(priceRepository.getCurrentPrice(1L, 35455L, LocalDateTime.parse("2020-06-15T16:00:00")))
        .thenReturn(Optional.ofNullable(price));
    when(priceMapper.mapToPriceVO(price)).thenReturn(priceVO);

    Optional<PriceVO> priceOptional = pricePersistenceAdapter.getCurrentPrice(
        1L, 35455L, LocalDateTime.parse("2020-06-15T16:00:00"));

    assertThat(priceOptional.get()).usingRecursiveComparison().isEqualTo(priceVO);
  }

}



