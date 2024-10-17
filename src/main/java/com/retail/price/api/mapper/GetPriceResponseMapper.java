package com.retail.price.api.mapper;

import com.retail.price.api.response.GetPriceResponse;
import com.retail.price.domain.model.PriceVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class GetPriceResponseMapper {

  @Mapping(target = "brandId", source = "priceVO.brandId.id")
  public abstract GetPriceResponse mapToGetPriceResponse(PriceVO priceVO);
}