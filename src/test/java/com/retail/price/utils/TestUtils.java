package com.retail.price.utils;

import com.retail.price.adapter.in.response.GetPriceResponse;
import com.retail.price.adapter.out.persistence.model.Brand;
import com.retail.price.adapter.out.persistence.model.Price;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Base64;
import org.springframework.http.HttpHeaders;

public class TestUtils {

  public static final Long BRAND_ID = 1L;
  public static final String BRAND_NAME = "ZARA";
  public static final Long PRICE_LIST = 1L;
  public static final Long PRODUCT_ID = 1L;
  public static final Integer PRIORITY = 1;
  public static final String CURR = "EUR";
  public static final BigDecimal PRICE = BigDecimal.valueOf(50.5);

  public static final LocalDateTime START_DATE = LocalDateTime.MIN;
  public static final LocalDateTime END_DATE = LocalDateTime.MAX;

  public static Price getPrice() {

    Brand brand = Brand.builder().id(BRAND_ID).name(BRAND_NAME).build();

    return Price.builder().brandId(brand).priceList(PRICE_LIST).curr(CURR).price(PRICE)
        .startDate(START_DATE).productId(PRODUCT_ID).endDate(END_DATE).priority(PRIORITY).build();
  }

  public static GetPriceResponse getPriceResponse() {
    return GetPriceResponse.builder().price(PRICE).brandId(BRAND_ID).endDate(END_DATE)
        .priceList(PRICE_LIST).productId(PRODUCT_ID).startDate(START_DATE).build();
  }

  public static GetPriceResponse createCustomResponse(Long brandId, LocalDateTime startDate,
                                                      LocalDateTime endDate, Long priceList,
                                                      Long productId, Double price) {

    return GetPriceResponse.builder().price(BigDecimal.valueOf(price)).brandId(brandId)
        .endDate(endDate)
        .priceList(priceList).productId(productId).startDate(startDate).build();
  }

  public static Price createCustomPrice(Long brandId, LocalDateTime startDate,
                                        LocalDateTime endDate, Long priceList,
                                        Long productId, Double price, Integer priority) {

    return Price.builder().price(BigDecimal.valueOf(price))
        .brandId(Brand.builder().name("ZARA").id(brandId).build())
        .endDate(endDate).priority(priority)
        .priceList(priceList).productId(productId).startDate(startDate).build();
  }

  public static HttpHeaders getHeaders() {
    HttpHeaders authHeaders = new HttpHeaders();
    String token = new String(Base64.getEncoder().encode(
        ("user1" + ":" + "password").getBytes()));
    authHeaders.set("Authorization", "Basic " + token);
    return authHeaders;
  }
}