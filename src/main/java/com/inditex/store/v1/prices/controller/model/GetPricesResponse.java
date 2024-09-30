package com.inditex.store.v1.prices.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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
public class GetPricesResponse {

  @Schema(description = "List of product prices")
  private List<Price> prices;

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Price {
    @Schema(description = "Product code identifier", example = "35455")
    private Long productId;
    @Schema(description = "Brand identifier", example = "1")
    private Long brandId;
    @Schema(description = "Start date on which the indicated rate price applies",
        example = "2020-06-14-15.00.00")
    private LocalDateTime startDate;
    @Schema(description = "End date on which the indicated rate price applies",
        example = "2020-06-14-18.30.00")
    private LocalDateTime endDate;
    @Schema(description = "Applicable pricing rate identifier", example = "4")
    private Long priceList;
    @Schema(description = "Final sale price", example = "38.95")
    private BigDecimal price;
  }
}