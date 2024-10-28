package com.retail.price.adapter.in.mapper;

import com.retail.price.adapter.in.response.GetPriceResponse;
import com.retail.price.application.domain.model.PriceVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Get price response mapper.
 */
@Mapper(componentModel = "spring")
public interface GetPriceResponseMapper {

  /**
   * Map to get price response get price response.
   *
   * @param priceVO the price vo
   * @return the get price response
   */
  @Mapping(target = "brandId", source = "priceVO.brandId.id")
  GetPriceResponse mapToGetPriceResponse(PriceVO priceVO);
}