package com.inditex.store.prices.controller.mapper;

import com.inditex.store.prices.usecase.model.PriceVO;
import com.inditex.store.prices.controller.model.GetPricesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class GetPricesResponseMapper {
  @Mapping(target = "brandId", source = "priceVO.brandId.id")
  public abstract GetPricesResponse mapToGetPricesResponse(PriceVO priceVO);
}