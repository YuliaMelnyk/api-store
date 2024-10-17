package com.retail.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.retail.price.adapter.repository.BrandRepository;
import com.retail.price.adapter.repository.PriceRepository;
import com.retail.price.adapter.repository.model.Brand;
import com.retail.price.adapter.repository.model.Price;
import com.retail.price.api.controller.GetPriceControllerImpl;
import com.retail.price.api.response.GetPriceResponse;
import com.retail.price.domain.facade.PriceFacade;
import com.retail.utils.TestUtils;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class GetPriceControllerIT {

  @Autowired
  PriceRepository priceRepository;

  @Autowired
  BrandRepository brandRepository;

  @Autowired
  PriceFacade priceFacade;

  @Autowired
  private GetPriceControllerImpl priceController;

  private static Stream<Arguments> paramsForCurrentPrice() {
    return Stream.of(
        Arguments.of(LocalDateTime.parse("2020-06-14T16:00:00"), 35455L, 1L,
                     TestUtils.createCustomResponse
                         (1L, LocalDateTime.parse("2020-06-14T15:00:00"),
                          LocalDateTime.parse("2020-06-14T18:30:00"), 2L,
                          35455L, 25.45),
                     TestUtils.createCustomPrice(1L, LocalDateTime.parse("2020-06-14T15:00:00"),
                                                 LocalDateTime.parse("2020-06-14T18:30:00"), 2L,
                                                 35455L, 25.45, 1
                     ),

                     Arguments.of(LocalDateTime.parse("2020-06-14T10:00:00"), 35455L, 1L,
                                  TestUtils.createCustomResponse(
                                      1L, LocalDateTime.parse("2020-06-14T00:00:00"),
                                      LocalDateTime.parse("2020-12-31T23:59:59"), 1L,
                                      35455L, 35.50),
                                  TestUtils.createCustomPrice(1L, LocalDateTime.parse(
                                                                  "2020-06-14T00:00:00"),
                                                              LocalDateTime.parse(
                                                                  "2020-12-31T23:59:59"), 1L,
                                                              35455L, 35.50, 0)),

                     Arguments.of(LocalDateTime.parse("2020-06-14T21:00:00"), 35455L, 1L,
                                  TestUtils.createCustomResponse
                                      (1L, LocalDateTime.parse("2020-06-14T00:00:00"),
                                       LocalDateTime.parse("2020-12-31T23:59:59"), 1L,
                                       35455L, 35.50),
                                  TestUtils.createCustomPrice(1L, LocalDateTime.parse(
                                                                  "2020-06-14T00:00:00"),
                                                              LocalDateTime.parse(
                                                                  "2020-12-31T23:59:59"), 1L,
                                                              35455L, 35.50, 1)),

                     Arguments.of(LocalDateTime.parse("2020-06-15T10:00:00"), 35455L, 1L,
                                  TestUtils.createCustomResponse
                                      (1L, LocalDateTime.parse("2020-06-15T00:00:00"),
                                       LocalDateTime.parse("2020-06-15T11:00:00"), 3L,
                                       35455L, 30.50),
                                  TestUtils.createCustomPrice(1L, LocalDateTime.parse(
                                                                  "2020-06-15T00:00:00"),
                                                              LocalDateTime.parse(
                                                                  "2020-06-15T11:00:00"), 3L,
                                                              35455L, 30.50, 1)),

                     Arguments.of(LocalDateTime.parse("2020-06-16T21:00:00"), 35455L, 1L,
                                  TestUtils.createCustomResponse
                                      (1L, LocalDateTime.parse("2020-06-15T16:00:00"),
                                       LocalDateTime.parse("2020-12-31T23:59:59"), 4L,
                                       35455L, 38.95),
                                  TestUtils.createCustomPrice(1L, LocalDateTime.parse(
                                                                  "2020-06-15T16:00:00"),
                                                              LocalDateTime.parse(
                                                                  "2020-12-31T23:59:59"), 4L,
                                                              35455L, 38.95, 1))

        ));
  }

  @ParameterizedTest
  @MethodSource("paramsForCurrentPrice")
  void getCurrentPriceOkTest(LocalDateTime applicationDateTime, Long productId, Long brandId,
                             GetPriceResponse expectedResponse, Price price) {

    Brand brand = Brand.builder().name("ZARA").id(1L).build();
    // given

    // when
    brandRepository.save(brand);
    priceRepository.save(price);

    ResponseEntity<GetPriceResponse> response = priceController.getCurrentPrice(
        applicationDateTime, productId, brandId);

    assertEquals(response.getBody().getBrandId(), expectedResponse.getBrandId());
    assertEquals(response.getBody().getPriceList(), expectedResponse.getPriceList());
    assertEquals(response.getBody().getEndDate(), expectedResponse.getEndDate());
    assertEquals(response.getBody().getProductId(), expectedResponse.getProductId());
    assertEquals(response.getBody().getPrice(), expectedResponse.getPrice());
    assertEquals(response.getBody().getStartDate(), expectedResponse.getStartDate());
  }
}