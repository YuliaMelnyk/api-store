package com.inditex.store.price.api.mapper;

import com.inditex.store.price.api.response.GetPriceResponse;
import com.inditex.store.price.domain.model.PriceVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class GetPriceResponseMapper {

  @Mapping(target = "brandId", source = "priceVO.brandId.id")
  public abstract GetPriceResponse mapToGetPriceResponse(PriceVO priceVO);
}