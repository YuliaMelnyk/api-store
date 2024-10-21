package com.retail.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.retail.price.adapter.repository.BrandRepository;
import com.retail.price.adapter.repository.PriceRepository;
import com.retail.price.adapter.repository.model.Brand;
import com.retail.price.adapter.repository.model.Price;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PriceRepositoryTest {

  @Autowired
  private PriceRepository priceRepository;

  @Autowired
  private BrandRepository brandRepository;


  @Test
  void givenPriceEntity_whenSavePrice_thenPriceIsPersisted() {

    Brand brand = Brand.builder().name("ZARA").id(1L).build();
    // given
    Price price = Price.builder()
        .priority(1)
        .priceList(1L)
        .endDate(LocalDateTime.MAX)
        .price(BigDecimal.valueOf(30.35))
        .curr("EUR")
        .brandId(brand)
        .productId(3244L)
        .startDate(LocalDateTime.MIN)
        .build();

    // when
    brandRepository.save(brand);
    priceRepository.save(price);

    // then
    Optional<Price> retrievedPrice = priceRepository.findById(1L);

    assertTrue(retrievedPrice.isPresent());
    assertEquals(BigDecimal.valueOf(30.35), retrievedPrice.get().getPrice());
  }
}