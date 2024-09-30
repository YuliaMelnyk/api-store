package com.inditex.store.usecase.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceVO {

  private BrandVO brandId;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Long priceList;
  private Long productId;
  private Integer priority;
  private BigDecimal price;
  private String curr;
}