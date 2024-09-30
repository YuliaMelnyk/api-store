package com.inditex.store.v1.prices.controller.mapper;

import com.inditex.store.usecase.model.PriceVO;
import com.inditex.store.v1.prices.controller.model.GetPricesResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class GetPriceResponseMapper {

  @Mapping(target = "prices", source = "pricesVO")
  public GetPricesResponse priceVOToGetPriceResponse(List<PriceVO> pricesVO) {
    return GetPricesResponse.builder()
        .prices(map(pricesVO))
        .build();
  }

  protected List<GetPricesResponse.Price> map(
      List<PriceVO> pricesVO) {
   return pricesVO.stream().map(this::mapToPriceResponse).collect(Collectors.toList());
  }

  protected GetPricesResponse.Price mapToPriceResponse(
      PriceVO priceVO) {
   return GetPricesResponse.Price.builder()
        .priceList(priceVO.getPriceList())
        .price(priceVO.getPrice())
        .endDate(priceVO.getEndDate())
        .brandId(priceVO.getBrandId().getId())
        .productId(priceVO.getProductId())
        .startDate(priceVO.getStartDate())
        .build();
  }
}